package com.example.redisBoot.service;

import com.example.redisBoot.entity.Product;

import java.util.List;

public interface RedisService {

    void insert(Product product);

    void delete(String productId);

    void update(Product product);

    public List<Product> searchProduct();

    Product getOne(String productId);
}
