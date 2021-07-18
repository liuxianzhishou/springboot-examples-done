package com.neo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//basePackages属性值即为刚才写的实体类对应接口的路径
@MapperScan(basePackages = "com.neo.mapper")
@SpringBootApplication
public class MAMApplication {

	public static void main(String[] args) {
		SpringApplication.run(MAMApplication.class, args);
	}
}
