package com.spider.song.spiderquartz.logic;


import com.spider.song.spidercommon.date.DateFormatUtils;
import com.spider.song.spidercommon.date.DateUtils;
import com.spider.song.spidercommon.encrypt.MD5Utils;
import com.spider.song.spidercommon.mail.SendEmail;
import com.spider.song.spidercommon.utils.DateUtil;
import com.spider.song.spidercommon.utils.RedisUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.Date;

import static com.spider.song.spidercommon.statement.Constants.EMAIL_FLAG.*;


@Service
public class SpiderHome {

    private Logger logger = LoggerFactory.getLogger(SpiderHome.class.getSimpleName());

    private static final String RUC_URL = "http://econ.ruc.edu.cn/";

    public static void main(String[] args) {
        System.out.println("spider Starting……");
        String url = "http://econ.ruc.edu.cn/more_news.php?cid=10854";
        String str = "受理2018年4月答辩申请的通知（3月1-9日）";
        String dd = DateUtil.formatDate(new Date(), "yyyy-MM-dd");
        System.out.println(dd+" 23:59:59");
        System.out.println(DateUtil.formatDate(new Date(),"yyyy-MM-dd HH:mm:ss"));
        String ee = DateUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
        System.out.println(DateUtils.compareTime(ee,dd+" 23:59:59",3));
        try {
            String encrMd5Tital = MD5Utils.encodeMessage(str.getBytes("utf-8"));
            System.out.println(encrMd5Tital);
            System.out.println(DateUtils.compareTime("2012-12-12 10:11:55","2012-12-12 12:12:20",1));
            //new SpiderHome().spiderRobot(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("spider Ending……");
    }

    public Document spiderConnect(String url) throws Exception {


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

    public void spiderRobot(String url) throws Exception {
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
            String nowDateStr = DateUtil.formatDate(nowDate, "yyyy-MM-dd");//"2017-12-14";//
            if (dateStr.compareTo(nowDateStr) < 0) {
                System.out.println("发布日期小于今天");
            }
            if (dateStr.compareTo(nowDateStr) == 0) {
                System.out.println("发布日期等于今天");
                operateUpdate(elementAs, element_Li, element_a);
            }
            if (dateStr.compareTo(nowDateStr) > 0) {
                System.out.println("发布日期大于今天");
                //operateUpdate(elementAs,element_Li,element_a);
            }
        }
    }

    public void operateUpdate(Elements elementAs, Element element_Li, Element element_a) throws Exception {
        elementAs = element_Li.getElementsByTag("a");
        if (elementAs == null || elementAs.get(0) == null) {
            logger.info("a标签节点的span子节点未找到]");
            return;
        }
        element_a = elementAs.get(0);
        String title = element_a.text();
        if (isSend(title)) {
            logger.info("title:" + title);
            String hrefValue = element_a.attr("href");//displaynews.php?id=14503
            logger.info("href:" + hrefValue);
            queryNoticeHtml(RUC_URL + hrefValue, title);
        }
    }

    public void queryNoticeHtml(String url, String title) throws Exception {
        try {
            Document document = spiderConnect(url);

            String to = RedisUtils.getProps(TO_EMAIL_ACCOUNT);
            String cc = RedisUtils.getProps(TO_EMAIL_ACCOUNT_CC);//必须抄送自己一份，否则会被垃圾邮件机制拦截发送
            String senderNickname = RedisUtils.getProps(SENDER_NICKNAME);
            String subject = title;
            String content = document.select("html body div div.right div").html();// sb.toString();

            //发送邮件
            logger.info("邮件正文：》》》》》》》》》》》:" + content);
            boolean bool = SendEmail.send(senderNickname, to, subject, cc, content);
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

    /**
     * 判断今天是否发送这条通知
     *
     * @param title
     * @return
     */
    public boolean isSend(String title) {
        if (title == null || "".equals(title)) {
            return false;
        }
        try {
            String keyMd5 = MD5Utils.encodeMessage(title.getBytes("UTF-8"));
            String value = RedisUtils.get("todayTitle" + keyMd5);
            if (value == null) {
                Jedis jedis = RedisUtils.getJedisConnection();
                int seconds = DateUtil.leftSecondsToday();
                jedis.setex("todayTitle" + keyMd5, seconds, "sended");
                logger.info("isSend::今天未发送title = [{}],缓存过期时间[{}]秒", title,seconds);
                return true;
            }
        } catch (Exception e) {
            logger.error("[isSended]::md5加密出错[title]:{}", title);
            return false;
        }
        logger.info("isSend::今天已发送过title = [{}]", title);
        return false;
    }

}
