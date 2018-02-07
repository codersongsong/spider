package com.spider.song.spidercommon.encrypt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: 继承PropertyPlaceholderConfigurer定义支持密文版属性的属性配置器
 * @User: songzhengjie
 * @Date: 2018-02-05
 * @Time: 17:47
 */
public class PropertySecurity {

    private static Logger logger = LoggerFactory.getLogger(PropertySecurity.class.getSimpleName());

    private static String[] encryptPropNames = { "PASSWORD"};


    /**
     * 对特定属性的属性值进行转换
     */
    public static String convertProperty(String propertyName, String propertyValue) throws Exception{

        if (isEncryptProp(propertyName)) {
                String decryptValue = DESUtils.getDecryptString(propertyValue);
                logger.info("[convertProperty]::propertyName:{}",propertyName);;
                return decryptValue;
        } else {
            return propertyValue;
        }

    }

    /**
     * 判断是否是需要解密的属性
     */
    private static boolean isEncryptProp(String propertyName) {
        for (String encryptPropName : encryptPropNames) {
            if (propertyName != null && propertyName.toUpperCase().contains(encryptPropName)) {
                return true;
            }
        }
        return false;
    }
}
