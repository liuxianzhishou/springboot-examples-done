package com.example.mybatisGen;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 出错1——@MapperScan忘记写了
 */
@SpringBootApplication
// mapper 接口类扫描包配置
@MapperScan("com.example.mybatisGen.dao")
public class MybatisGenApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisGenApplication.class, args);
    }

}
