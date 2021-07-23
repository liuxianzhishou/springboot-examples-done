package com.example.nettyws.netty.websocket;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * 初始化类，实现配置
 */

public class ServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        //获取管道（pipeline）
        ChannelPipeline pipeline = channel.pipeline();
        //以下三个是Http的支持
        //websocket 基于http协议，所需要的http 编解码器
        pipeline.addLast(new HttpServerCodec());
        //在http上有一些数据流产生，有大有小，我们对其进行处理，既然如此，我们需要使用netty 对下数据流写 提供支持，这个类叫：ChunkedWriteHandler
        pipeline.addLast(new ChunkedWriteHandler());
        //对httpMessage 进行聚合处理，聚合成request或 response
        pipeline.addLast(new HttpObjectAggregator(1024*64));
        //websocket支持,设置路由
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
        //自定义的handler,实现具体方法
        pipeline.addLast(new ServerHandler());

    }
}
