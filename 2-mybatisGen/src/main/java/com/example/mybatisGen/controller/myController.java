package com.example.mybatisGen.controller;

import com.example.mybatisGen.entity.Person;
import com.example.mybatisGen.entity.Show;
import com.example.mybatisGen.service.Impl.PersonServiceImpl;
import com.example.mybatisGen.service.Impl.ShowServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 出错3——多次使用mybatis-generator造成xml文件代码重复
 * 出错4——没有加@RestController
 */
@RestController
public class myController {
    @Autowired
    private PersonServiceImpl personService;
    @Autowired
    private ShowServiceImpl showService;

    @RequestMapping(value = "/getPerson")
    public Person findOnePerson(@RequestParam(value = "id",required = true) long id)
    {
        return personService.selectByPrimaryKey(id);
    }

    @RequestMapping(value = "/getShow")
    public Show findOneShow(@RequestParam(value = "id",required = true) long id)
    {
        return showService.selectByPrimaryKey(id);
    }

}
