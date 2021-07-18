package com.example.redisBoot.service.serviceImpl;

import com.example.redisBoot.dao.ProductDao;
import com.example.redisBoot.entity.Product;
import com.example.redisBoot.service.RedisService;
import com.example.redisBoot.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private ProductDao productDao;

    //内联类
//    @Autowired
//    private RedisUtil.redisList redisList;
    @Autowired
    private RedisUtil.redisString redisString;

    /**
     * 需要判断redis怎么处理，现在先解决数据库的问题
     * DONE:插入数据，只插入到数据库中
     * @param product
     */
    public void insert(Product product)
    {
        productDao.insert(product);
//        redisString.set(product.getProductId(),product.getProductCategories());
    }

    public void delete(String productId)
    {
        //先清缓存
        if(redisUtil.hasKey(productId))
        {
            redisUtil.del(productId);
        }
        //后删数据库内容
        productDao.delete(productId);
    }

    public void update(Product product)
    {
        //先删除相关缓存，若存在这条信息
        if(redisUtil.hasKey(product.getProductId()))
        {
            redisUtil.del(product.getProductId());
            System.out.println("更新-redis删除成功");
        }
        //再更数据
        productDao.update(product);
        System.out.println("更新-数据库更新成功");
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public List<Product> searchProduct() {
        List list;
//        if(redisUtil.hasKey("productList"))
//        {
//            System.out.println("从redis中获取所有数据.");
//            list = redisList.get("productList", 0, -1);
//        }
//        else {
            list = productDao.searchProduct();
            System.out.println("------成功从数据库中获取所有数据.------");
//            System.out.println("------2-将数据存入redis...");
//            redisList.set("productList", list);
//            System.out.println("------3-成功存入redis.----------end");
//        }
        return list;
    }

    public Product getOne(String productId)
    {
        Product res=new Product();
        if(redisUtil.hasKey(productId))
        {
            System.out.println("从redis中获取某个数据");
            res.setProductId(productId);
            res.setProductCategories((String) redisString.get(productId));//强制转换，object->string
        }
        else
        {
            //如果没有，就从数据库中找数据
            res=productDao.getOne(productId);
            System.out.println("------1-从数据库中获取某个数据------");
            System.out.println("-------将数据存入redis...");
            redisString.set(productId,res.getProductCategories());
            System.out.println("------3-成功存入redis.----------end");
        }
        return res;
    }

}
