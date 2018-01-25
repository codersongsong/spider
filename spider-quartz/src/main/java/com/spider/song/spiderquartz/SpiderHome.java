package com.spider.song.spiderquartz;


import com.spider.song.spidercommon.mail.SendEmail;
import com.spider.song.spidercommon.utils.DateUtil;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.mail.Address;
import javax.mail.internet.InternetAddress;
import javax.swing.text.AbstractDocument;
import java.util.Date;


@Service
public class SpiderHome {

    private static Logger logger = LoggerFactory.getLogger(SpiderHome.class.getSimpleName());

    private static final String RUC_URL = "http://econ.ruc.edu.cn/";

    public static void main(String[] args) {
        System.out.println("spider Starting……");
        String url = "http://econ.ruc.edu.cn/more_news.php?cid=10854";
        try {
            spiderRobot(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("spider Ending……");
    }

    public static Document spiderConnect(String url) throws Exception {


        Connection conn = Jsoup.connect(url);
        conn.userAgent("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0; MALC)")
                .timeout(3000);
        try {
            Document document = conn.get();

            return document;
        } catch (Exception e) {
            logger.error("链接网站{}出错:{}", url, e);
            throw new Exception("链接网站出错:{}", e);
        }
    }

    public static void spiderRobot(String url) throws Exception {
        Document document = spiderConnect(url);
        Element element = document.body();
        Elements elements = document.select("div div.right ul li");
//            Elements elements =element.getElementsByTag("div.right div ul li");
        if (elements == null) {
            logger.info("li节点列表未获取到!");
            return;
        }
        Element element_Li = null;
        Element element_a = null;
        Element element_Span = null;
        Elements elementAs = null;
        Elements elementSpans = null;
        String dateStr = null;
        Date nowDate = new Date();
        for (int i = 0; i < elements.size(); i++) {
            element_Li = elements.get(i);
            System.out.println("[第" + i + "个节点:]" + element_Li.text());
            elementSpans = element_Li.getElementsByTag("span");
            if (elementSpans == null || elementSpans.get(0) == null) {
                logger.info("[第" + i + "个节点的span子节点未找到]");
                continue;
            }
            element_Span = elementSpans.get(0);
            dateStr = element_Span.text().replace("[", "").replace("]", "");
            String nowDateStr = "2017-12-14";//DateUtil.formatDate(nowDate, "yyyy-MM-dd");
            if (dateStr.compareTo(nowDateStr) < 0) {
                System.out.println("发布日期小于今天");
            }
            if (dateStr.compareTo(nowDateStr) == 0) {
                System.out.println("发布日期等于今天");
                operateUpdate(elementAs,element_Li,element_a);
            }
            if (dateStr.compareTo(nowDateStr) > 0) {
                System.out.println("发布日期大于今天");
                //operateUpdate(elementAs,element_Li,element_a);
            }
        }
    }

    public static void  operateUpdate(Elements elementAs,Element element_Li,Element element_a) throws Exception {
        elementAs = element_Li.getElementsByTag("a");
        if (elementAs == null || elementAs.get(0) == null) {
            logger.info("a标签节点的span子节点未找到]");
            return;
        }
        element_a = elementAs.get(0);
        String title = element_a.text();
        System.out.println("title:" + title);
        String hrefValue = element_a.attr("href");//displaynews.php?id=14503
        System.out.println("href:" + hrefValue);
        queryNoticeHtml(RUC_URL + hrefValue,title);
    }

    public static void queryNoticeHtml(String url,String title) throws Exception {
        try {
            Document document = spiderConnect(url);

            String from = "stefan1102@163.com";
            String to = "songzhengjie@gomeholdings.com" +
                    ",349052898@qq.com" +
                    ",stefan1102@163.com";
            String cc = "stefan1102@163.com";
            String subject = title;
            String content =document.select("html body div div.right div").html();// sb.toString();

            //发送邮件
            logger.info("邮件正文：》》》》》》》》》》》:" + content);
            boolean bool = SendEmail.send(null, to, subject, cc, content);
            if (bool) {
                logger.info("邮件已发送,请注意查收!");
            } else {
                logger.error("邮件发送失败,请请假代码BUG!");
            }
        } catch (Exception e) {
            logger.error("邮件发送出错:{}", e);
            throw new Exception("邮件发送出错:{}", e);
        }
    }

}
