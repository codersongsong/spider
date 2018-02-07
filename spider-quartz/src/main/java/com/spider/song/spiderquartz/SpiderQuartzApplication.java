package com.spider.song.spiderquartz;

import com.spider.song.spidercommon.utils.SpringContextUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 原因是：springboot启动时会自动注入数据源和配置jpa
 解决：在@SpringBootApplication中排除其注入
 */
@EnableScheduling
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
public class SpiderQuartzApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpiderQuartzApplication.class, args);
	}

	//如果再第一个properties文件中没有找到，就认为没有了，不继续找下一个properties文件，true时继续寻找
	//@Bean
	//public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
	//	PropertySourcesPlaceholderConfigurer c = new PropertySourcesPlaceholderConfigurer();
	//	c.setIgnoreUnresolvablePlaceholders(true);
	//	return c;
	//}

}
