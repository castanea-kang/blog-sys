package com.itkang.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.itkang.blog.mapper")
public class BlogSysApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogSysApplication.class, args);
	}

}

