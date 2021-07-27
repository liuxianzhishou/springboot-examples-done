package com.yh.mybatisgenwebsocket.controller;

import com.yh.mybatisgenwebsocket.entity.Flow1;
import com.yh.mybatisgenwebsocket.entity.Flow2;
import com.yh.mybatisgenwebsocket.service.Impl.Flow1ServiceImpl;
import com.yh.mybatisgenwebsocket.service.Impl.Flow2ServiceImpl;
import com.yh.mybatisgenwebsocket.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {
    @Autowired
    private Flow1ServiceImpl flow1Service;

    @Autowired
    private Flow2ServiceImpl flow2Service;

    @RequestMapping(value = "/selectTime")
    public List<Flow1> getFlow1()
    {
        return flow1Service.selectByTime("20210726113554");
    }

    @RequestMapping(value = "/insertTime")
    public int insertTime()
    {
        String date=DateUtil.getYsSDateAgo(0);
        System.out.println(date);
        return flow2Service.insert(new Flow2(null,"56.3",date));
    }
}
