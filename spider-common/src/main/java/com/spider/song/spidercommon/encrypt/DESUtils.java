package com.spider.song.spidercommon.encrypt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: DES加解密工具类
 * @User: songzhengjie
 * @Date: 2018-02-05
 * @Time: 16:42
 */
public class DESUtils {

    private static Logger logger = LoggerFactory.getLogger(DESUtils.class.getSimpleName());

    /**
     * 1、指定DES加解密所用的密匙
     */
    private static Key key;
    private static String KEY_STR = "ScAKC0XhadTHT3Al0QIDAQAB";//加密密匙

    static {
        try {
            //KeyGenerator generator = KeyGenerator.getInstance("DES");
            //generator.init(new SecureRandom(KEY_STR.getBytes()));
            //key = generator.generateKey();
            //generator = null;
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
            DESKeySpec keySpec = new DESKeySpec(KEY_STR.getBytes());
            key =secretKeyFactory.generateSecret(keySpec);
        } catch (Exception e) {
            logger.error("获取KeyGenerator出错:", e);
        }
    }

    /**
     * 2、对字符串进行DES加密,返回BASE64编码的加密字符串
     */
    public static String getEncryptString(String str) throws Exception {
        BASE64Encoder base64Encoder = new BASE64Encoder();
        SecureRandom sr = new SecureRandom();
        String encodeStr;
        try {
            byte[] strBytes = str.getBytes("UTF8");
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, key,sr);
            byte[] encryptStrBytes = cipher.doFinal(strBytes);
            encodeStr =  base64Encoder.encode(encryptStrBytes);
        } catch (Exception e) {
            logger.error("对字符串str:{}进行DES加密,返回BASE64编码的加密字符串,出错:{}",str,e);
            throw e;
        }
        return encodeStr;
    }

    /**
     * 3、对BASE64编码的加密字符串进行解密,返回解密后的字符串
     */
    public static String getDecryptString(String str) throws Exception {
        BASE64Decoder base64Decoder = new BASE64Decoder();
        SecureRandom sr = new SecureRandom();
        String decodeStr;
        try {
            byte[] strBytes = base64Decoder.decodeBuffer(str);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, key,sr);
            byte[] decryptStrBytes = cipher.doFinal(strBytes);
            decodeStr = new String(decryptStrBytes, "UTF8");
        } catch (Exception e) {
            logger.error("对BASE64编码的加密字符串str:{}进行解密,返回解密后的字符串,出错:{}",str,e);
            throw e;
        }
        return decodeStr;
    }


    /**
     * 测试：对入参的字符串进行加密，打印出加密后和解密后的串
     */
    public static void main(String[] args) throws Exception {

        String argTest = "!QAZ2wsx!QAZ";
        String pass = "31385815916s";
        System.out.println("正向加密行为》》》"+argTest+":"+getEncryptString(argTest));
        System.out.println("逆向解密行为》》》"+getEncryptString(argTest)+":"+getDecryptString(getEncryptString(argTest)));
        System.out.println("逆向解密行为》》》"+getEncryptString(pass));

    }

}
