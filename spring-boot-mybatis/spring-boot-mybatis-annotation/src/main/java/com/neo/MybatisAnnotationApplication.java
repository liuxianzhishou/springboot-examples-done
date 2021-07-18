package com.neo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.neo.mapper")///在启动类中添加对 mapper 包扫描,這樣就不需要在 每個Mapper 类上面添加注解@Mapper
public class MybatisAnnotationApplication {

	public static void main(String[] args) {

		SpringApplication.run(MybatisAnnotationApplication.class, args);
	}
}
