package com.example.redisBoot.controller;

import com.example.redisBoot.entity.Product;
import com.example.redisBoot.service.serviceImpl.RedisServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * redis:每次运行完要flushdb，清空redis缓存当前数据库数据
 * mysql:每次运行完要删除原有数据库，当然也可以不删除，不过最开始的是最初始的，所有数据看起来方便
 */
@RestController
@RequestMapping(value = "/redis")
public class RedisController {
    @Autowired
    private RedisServiceImpl redisService;

    /**
    //增
    //http://localhost:8088/redis/insert/51&羽国志异&一莲托生品---------error
    //http://localhost:8088/redis/insert/51&羽国志异
    //http://localhost:8088/redis/insert/60&一莲托生品
    //问题是：当删除某个数据后，新增数据自动填补到之前的空出来的位置，否则在最后面添加
     */
    @RequestMapping(value = "/insert/{id}&{cate}")
    public List<Product> insert(@PathVariable String id,@PathVariable String cate)
    {
        redisService.insert(new Product(id,cate));
        return redisService.searchProduct();
    }

    /**
    //删
    //删缓存，读数据库
     */
    @RequestMapping(value = "/delete")
    public List<Product> delete(@RequestParam("productId") String productId)
    {
        redisService.delete(productId);
        return redisService.searchProduct();
    }

    /**
     *改
     *删缓存，读数据库
     *http://localhost:8088/redis/update?id=52&cate=妖刀诀
     *报WARN----
     */
    @RequestMapping(value = "/update")
    public List<Product> update(@RequestParam String id,@RequestParam String cate)
    {
        redisService.update(new Product(id,cate));
        return redisService.searchProduct();
    }

    //查所有
    //读数据库
    @RequestMapping(value = "/getAll")
    public List<Product> testRedis()
    {
        return redisService.searchProduct();
    }

    //查一个
    //只有该方法可以从redis中直接查找数据
    @RequestMapping(value = "/getOne/{productId}")
    public Product getOne(@PathVariable String productId)
    {
        return redisService.getOne(productId);
    }
}
