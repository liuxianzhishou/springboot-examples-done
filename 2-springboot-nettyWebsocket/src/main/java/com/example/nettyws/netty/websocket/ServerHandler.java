package com.example.nettyws.netty.websocket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

import com.alibaba.fastjson.JSONObject;

/**
 * 助手类，实现具体方法
 */
public class ServerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    ////所有正在连接的channel都会存在这里面
    public static ChannelGroup channelGroup=new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * 接受客户端发送的消息，执行相关函数操作
     * @param ctx 通道相关信息
     * @param msg 前端，也就是客户端发来的消息内容
     * @throws Exception 异常
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg)throws Exception
    {
        System.out.println("前端发来的数据"+msg.text());
        //根据前端发来的消息，实现自定义函数操作
        if(msg.text().equals("task0"))
        {
            Executors.newCachedThreadPool().submit(()->task0(ctx));
        }else if (msg.text().equals("task1"))
        {
            Executors.newCachedThreadPool().submit(()-> {
                try {
                    task1(ctx);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

    }

    /**
     * 自定义task0操作
     * @param ctx -
     */
    private void task0(ChannelHandlerContext ctx)
    {
        while (ctx.channel().isActive())
        {
            JSONObject object = new JSONObject();
            object.put("1","封禅剑雪恨相逢");
            ctx.channel().writeAndFlush(new TextWebSocketFrame(object.toJSONString()));

        }
        System.out.println("task0已执行");
    }

    /**
     * 自定义task1操作
     * @param ctx -
     */
    private void task1(ChannelHandlerContext ctx) throws InterruptedException {
        BlockingQueue<Double> msqQueue=new LinkedBlockingDeque<>();
        msqQueue.add(10.1);
        msqQueue.add(11.2);
        msqQueue.add(12.3);
        while (ctx.channel().isActive())
        {
            ctx.channel().writeAndFlush(new TextWebSocketFrame(msqQueue.take().toString()));
        }
        System.out.println("task1已执行");
    }


    /**
     * 与客户端建立连接
     * @param ctx -
     * @throws Exception 异常
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception
    {
        channelGroup.add(ctx.channel());
        System.out.println(ctx.channel().remoteAddress() + "上线");
    }

    /**
     * 处于运行状态
     * @param ctx -
     * @throws Exception 异常
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception
    {
        System.out.println(ctx.channel().remoteAddress() + "连接");
    }

    /**
     * 关闭连接
     * @param ctx -
     * @throws Exception 异常
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception
    {
        channelGroup.remove(ctx.channel());
        System.out.println(ctx.channel().remoteAddress()+"断开连接");
    }

    /**
     * 出现异常
     * @param ctx -
     * @param cause -
     * @throws Exception 异常
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception
    {
        cause.printStackTrace();
        System.out.println("客户端网络断开，即将关闭异常客户端");
        //发生了异常后关闭连接，同时从channelGroup移除
        ctx.channel().close();
        channelGroup.remove(ctx.channel());
        System.out.println("成功关闭异常客户端");
    }
}
