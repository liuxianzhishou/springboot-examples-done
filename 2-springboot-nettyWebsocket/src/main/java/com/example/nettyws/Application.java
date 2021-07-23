package com.example.nettyws;

import com.example.nettyws.netty.websocket.ServerSingleton;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        //开启websocket服务
        ServerSingleton.getInstance().start();
    }

}
