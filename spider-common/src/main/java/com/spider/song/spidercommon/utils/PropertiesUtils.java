package com.spider.song.spidercommon.utils;

import com.spider.song.spidercommon.encrypt.PropertySecurity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Properties;
import java.util.Set;

/**
 * ========================================
 * Created with IntelliJ IDEA.
 * Description:配置文件properties的工具类
 * User: songzhengjie
 * Date: 2018-02-07
 * Time: 11:16
 * ========================================
 */
public class PropertiesUtils {

    private static Logger logger = LoggerFactory.getLogger(PropertiesUtils.class.getSimpleName());

    private PropertiesUtils() {
        //防止外部实例化
    }

    /**
     * 根据key获取项目启动指定的properties配置文件的属性值
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static String getProperty(String key) throws Exception {

        Resource resource = getDefaultEnvResource();
        return getProperty(key, resource);
    }


    /**
     * 获取配置文件资源
     * FileSystemResource：以文件的绝对路径方式进行访问
     * ClassPathResourcee：以类路径的方式访问
     * ServletContextResource：web应用根目录的方式访问
     * @param path application.properties
     */
    public static Resource getResource(String path) {
        Resource resource = new ClassPathResource(path);
        return resource;
    }

    /**
     * 获取启动加载的配置文件资源
     */
    public static Resource getDefaultEnvResource() throws Exception {
        Resource resource = getResource("application.properties");
        String env = getProperty("spring.profiles.active", resource);
        resource = getResource("application-" + env + ".properties");
        return resource;
    }

    /**
     * 获取具体配置资源resource中key对应的属性值
     */
    public static String getProperty(String key, Resource resource) throws Exception {
        Properties props = makeProperties(resource);

        String propValue = props.getProperty(key);
        String value = PropertySecurity.convertProperty(key, propValue);
        logger.info("[getProperty]::propsName:{},propsValue:{}", key, propValue);
        return value;
    }

    /**
     * 生成Properties对象,并对资源强行UTF-8编码
     */
    public static Properties makeProperties(Resource resource) throws Exception {
        Properties props = PropertiesLoaderUtils.loadProperties(new EncodedResource(resource,"UTF-8"));
        return props;
    }

    public static void getAllProps() throws Exception {
        Resource resource = getDefaultEnvResource();
        Properties properties = PropertiesLoaderUtils.loadProperties(resource);
        Set<String> propNames = properties.stringPropertyNames();
        for (String name : propNames) {
            String value = properties.getProperty(name);
            System.out.println(name+":"+value);
        }
    }


    public static void main(String[] args) throws Exception {
        try {
            getProperty("senderNickName");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
