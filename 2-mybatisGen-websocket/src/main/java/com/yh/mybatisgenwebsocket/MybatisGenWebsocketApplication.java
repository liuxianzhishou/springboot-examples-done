package com.yh.mybatisgenwebsocket;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.yh.mybatisgenwebsocket.mapper")
public class MybatisGenWebsocketApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisGenWebsocketApplication.class, args);

    }

}
