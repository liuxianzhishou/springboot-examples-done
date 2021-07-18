package com.example.redisBoot.entity;

import java.io.Serializable;

/**
 * 1-建立数据库对应实体
 * 疑问？-是否需要implements Serializable接口，自动屏蔽了操作系统的差异，字节顺序等
 * ----如果在网络的环境下做类传输，应该还是implements Serializable
 * 图书信息实体
 * @author yh
 */
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    private String productId;
    private String productCategories;

    //构造函数
    public Product()
    {
        super();
    }
    public Product(String productId,String productCategories)
    {
        super();
        this.productId=productId;
        this.productCategories=productCategories;
    }

    //get&set方法
    public String getProductId() {
        String productId = this.productId;
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductCategories() {
        return productCategories;
    }

    public void setProductCategories(String productCategories) {
        this.productCategories = productCategories;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productCategories='" + productCategories + '\'' +
                '}';
    }
}
