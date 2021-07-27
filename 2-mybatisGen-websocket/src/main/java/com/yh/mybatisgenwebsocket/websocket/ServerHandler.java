package com.yh.mybatisgenwebsocket.websocket;

import com.alibaba.fastjson.JSONObject;
import com.yh.mybatisgenwebsocket.SpringUtil;
import com.yh.mybatisgenwebsocket.entity.Flow1;
import com.yh.mybatisgenwebsocket.service.Impl.Flow1ServiceImpl;
import com.yh.mybatisgenwebsocket.service.Impl.Flow2ServiceImpl;
import com.yh.mybatisgenwebsocket.utils.DateUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

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
            Executors.newCachedThreadPool().submit(()-> {
                try {
                    task0(ctx);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
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
        else if (msg.text().equals("serial"))
        {
            System.out.println("进入serial操作");
            Executors.newCachedThreadPool().submit(()->
            {
               System.out.println("serial");
                try {
                    task2(ctx);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

    }

    /**
     * 自定义task0操作
     * 读数据库数据，以fastjson形式传输
     * @param ctx -
     */
    private void task0(ChannelHandlerContext ctx) throws InterruptedException {
        Flow1ServiceImpl flow1Service=SpringUtil.getBean(Flow1ServiceImpl.class);
        JSONObject object=new JSONObject();

        for(int i=1;i<4;i++)
        {
            String value=flow1Service.selectByPrimaryKey((long) i).getSpeed();
            object.put(String.valueOf(i),value);
        }
        while (ctx.channel().isActive())
        {
            ctx.channel().writeAndFlush(new TextWebSocketFrame(object.toJSONString()));
            TimeUnit.SECONDS.sleep(1l);//延时1秒
        }
        System.out.println("task0已执行");
    }

    /**
     * 自定义task1操作
     * 读数据库数据，以BlockingQueue形式传输
     * @param ctx -
     */
    private void task1(ChannelHandlerContext ctx) throws InterruptedException {

        Flow2ServiceImpl flow2Service = SpringUtil.getBean(Flow2ServiceImpl.class);
        BlockingQueue<String> tmp = new LinkedBlockingQueue<String>();
        for (int i=1;i<4;i++)
        {
            tmp.add(flow2Service.selectByPrimaryKey((long) i).getSpeed());
        }
        while (ctx.channel().isActive())
        {
            ctx.channel().writeAndFlush(new TextWebSocketFrame(tmp.take()));
            System.out.println("task1已执行");
            TimeUnit.SECONDS.sleep(1l);
        }

    }

    private void task2(ChannelHandlerContext ctx) throws InterruptedException {
//        long tmp=2;
        while (ctx.channel().isActive())
        {
            /**
             * 如果该频道一直在线
             * 就定时发送从数据库读到的新数据
             */

            Flow1ServiceImpl flow1Service=SpringUtil.getBean(Flow1ServiceImpl.class);
            JSONObject object=new JSONObject();
            String ysSDate=DateUtil.getYsSDateAgo(-100);//延时20ms
            System.out.println("Date:"+ysSDate);
            /**
             * TODO: 待修改
             * 如果要添加时间段内的结果，可以添加多个时间参数，采用getYsSDateAgo()获得多个时间点
             * 现在只是保证串口传输0.1s的频率，接收
             */

            List<Flow1> flow1s=flow1Service.selectByTime(ysSDate);
            if(!flow1s.isEmpty())
            {
                int len=flow1s.size();
                for (int i=0;i<len;i++)
                {
                    String value= flow1s.get(i).getSpeed();
                    System.out.println("speed:"+value);
                }
                //放入当前时刻同一秒插入的最后一个数据
                object.put(ysSDate,flow1s.get(len-1).getSpeed());
            }
            ctx.channel().writeAndFlush(new TextWebSocketFrame(object.toJSONString()));
            TimeUnit.MILLISECONDS.sleep(100L);//延时1秒
        }
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
