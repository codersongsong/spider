package com.spider.song.spiderwechat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 原因是：springboot启动时会自动注入数据源和配置jpa
 解决：在@SpringBootApplication中排除其注入
 */
@EnableScheduling
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
public class SpiderWechatApplication implements EmbeddedServletContainerCustomizer {

	public static void main(String[] args) {
		SpringApplication.run(SpiderWechatApplication.class, args);
	}

	@Override
	public void customize(ConfigurableEmbeddedServletContainer config) {
		config.setPort(80);
	}
}
