package com.spider.song.spidercommon.encrypt;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: 继承PropertyPlaceholderConfigurer定义支持密文版属性的属性配置器
 * @User: songzhengjie
 * @Date: 2018-02-05
 * @Time: 17:47
 */
public class EncryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {


    private String[] encryptPropNames = { "PASSWORD"};


    /**
     * 对特定属性的属性值进行转换
     */
    @Override
    protected String convertProperty(String propertyName, String propertyValue) {

        if (isEncryptProp(propertyName)) {
            try {
                String decryptValue = DESUtils.getDecryptString(propertyValue);
                System.out.println(decryptValue);
                return decryptValue;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            return propertyValue;
        }

        return super.convertProperty(propertyName, propertyValue);
    }

    /**
     * 判断是否是需要解密的属性
     */
    private boolean isEncryptProp(String propertyName) {
        for (String encryptPropName : encryptPropNames) {
            if (propertyName != null && propertyName.toUpperCase().contains(encryptPropName)) {
                return true;
            }
        }
        return false;
    }
}
