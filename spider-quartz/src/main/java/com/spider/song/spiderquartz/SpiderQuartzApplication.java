package com.spider.song.spiderquartz;

import com.spider.song.spidercommon.utils.SpringContextUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 原因是：springboot启动时会自动注入数据源和配置jpa
 解决：在@SpringBootApplication中排除其注入
 */
@EnableScheduling
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
public class SpiderQuartzApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext= SpringApplication.run(SpiderQuartzApplication.class, args);
		SpringContextUtil.setSpringApplicationContext(applicationContext);

	}
}
