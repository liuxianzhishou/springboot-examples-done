package com.yh.mybatisgenwebsocket;

import com.yh.mybatisgenwebsocket.utils.SerialListener;
import com.yh.mybatisgenwebsocket.websocket.ServerSingleton;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class Booster implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event)
    {
        if (event.getApplicationContext().getParent() == null)
        {
            //开启websocket服务
            ServerSingleton.getInstance().start();
            //开启串口服务
            SerialListener.open();
        }
    }
}
