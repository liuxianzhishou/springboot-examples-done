package com.example.nettyws.netty.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;

/**
 * 启动类，实现初始化操作
 */
@Component
public class ServerSingleton {
    /**
     * 单例模式：保证一个类仅有一个实例，并提供一个访问它的全局访问点
     * 饿汉式 缺点：用不用都初始化，浪费内存空间
     */
    private static final ServerSingleton serverSingleton= new ServerSingleton();

    private EventLoopGroup mainGroup;
    private EventLoopGroup subGroup;
    private ServerBootstrap serverBootstrap;
    private ChannelFuture future;

    public ServerSingleton() {
        //mainGroup用来处理连接
        mainGroup = new NioEventLoopGroup();
        //subGroup用来处理后续事件
        subGroup = new NioEventLoopGroup();
        serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(mainGroup, subGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ServerInitializer());
    }

    public static ServerSingleton getInstance() {
        return serverSingleton;
    }

    /**
     * 绑定端口
     * 当这个方法执行后，ServerBootstrap就可以接受指定端口上的socket连接了。
     * 一个ServerBootstrap可以绑定多个端口
     * @return null
     */
    public Channel bind() {
        return serverBootstrap.bind(8888).syncUninterruptibly().channel();
    }

    public void stop() {
        mainGroup.shutdownGracefully();
        subGroup.shutdownGracefully();
    }

    //这个才是入口程序，走start()操作
    public void start() {
        Executors.newCachedThreadPool().submit(() -> {
            try {
                //this表示这个类
                future = this.bind().closeFuture();
                System.out.println("启动 WebSocket 成功");
                future.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("服务端启动失败");
            } finally {
                //退出程序
                stop();
                System.out.println("服务端已关闭");
            }
        });
    }
}
