package com.example.redisBoot.dao;

import com.example.redisBoot.entity.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 3-数据库操作
 */
@Mapper
public interface ProductDao {

    //1-增加数据到数据库中
    @Insert("INSERT INTO product(productId,productCategories) VALUES(#{productId},#{productCategories})")
    void insert(Product product);

    //2-根据Id删除某个数据
    @Delete("DELETE FROM product WHERE productId=#{productId}")
    void delete(String productId);

    //3-根据id修改数据其他信息
    @Update("UPDATE product SET productCategories=#{productCategories} WHERE productId=#{productId}")
    void update(Product product);

    /**
     * 4.1-查询所有图书列表
     * @return
     */
    @Select("SELECT * FROM product")
    @Results(
            {
                    @Result(property = "productId",column = "productId"),
                    @Result(property = "productCategories",column = "productCategories")
            }
    )
    List<Product> searchProduct();

    //4.2-根据Id查找产品信息
    @Select("SELECT * FROM product WHERE productId=#{productId}")
    Product getOne(String productId);


}
