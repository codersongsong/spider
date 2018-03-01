package com.spider.song.spiderservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.spider.song.spiderdaodao.mapper")
public class SpiderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpiderServiceApplication.class, args);
	}
}
