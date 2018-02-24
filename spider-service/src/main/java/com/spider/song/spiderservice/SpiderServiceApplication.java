package com.spider.song.spiderservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.spider.song")
public class SpiderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpiderServiceApplication.class, args);
	}
}
