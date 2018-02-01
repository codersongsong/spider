package com.spider.song.spiderquartz;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: (修改启动类，继承 SpringBootServletInitializer 并重写 configure 方法)
 * @User: songzhengjie
 * @Date: 2018-02-01
 * @Time: 12:57
 */
public class SpringBootStartApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 注意这里要指向原先用main方法执行的Application启动类
        return builder.sources(SpiderQuartzApplication.class);
    }

}
