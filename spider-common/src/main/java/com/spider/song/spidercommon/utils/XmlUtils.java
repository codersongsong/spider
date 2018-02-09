package com.spider.song.spidercommon.utils;

import com.spider.song.spidercommon.dao.Articles;
import com.spider.song.spidercommon.dao.ReplyMessage;
import com.thoughtworks.xstream.XStream;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ========================================
 * Created with IntelliJ IDEA.
 * Description:
 * User: songzhengjie
 * Date: 2018-02-09
 * Time: 13:26
 * ========================================
 */
public class XmlUtils {


    /**
     * 讲request请求中的xml转换成map
     * @param request
     * @return
     * @throws Exception
     */
    public static Map<String, String> xmlToMap(HttpServletRequest request) throws Exception {

        Map map = new HashMap();
        SAXReader saxReader = new SAXReader();
        InputStream inputStream = request.getInputStream();

        try {

            Document document = saxReader.read(inputStream);
            Element element = document.getRootElement();
            List<Element> list = element.elements();
            for (Element ele : list) {
                map.put(ele.getName(), ele.getText());
            }
        } finally {
            inputStream.close();
        }

        return map;
    }

    /**
     *  将对象转换成xml格式
     * @param object
     * @return String
     */
    public static String objectToXml(Object object){
        XStream xStream = new XStream();
        //xStream.alias("xml",object.getClass());
        xStream.alias("xml",ReplyMessage.class);
        xStream.alias("item",Articles.class);
        return xStream.toXML(object);

    }
     public static String objecsstToXml(ReplyMessage replyMessage){
        XStream xStream = new XStream();
        xStream.alias("xml",ReplyMessage.class);
        xStream.alias("item",Articles.class);
        return xStream.toXML(replyMessage);

    }




}
