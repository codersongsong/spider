package com.spider.song.spiderwechat.controller;


import com.spider.song.spidercommon.dao.Articles;
import com.spider.song.spidercommon.dao.ReplyMessage;
import com.spider.song.spidercommon.encrypt.SHA1Utils;
import com.spider.song.spidercommon.utils.DateUtil;
import com.spider.song.spidercommon.utils.XmlUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

import static com.spider.song.spidercommon.statement.Constants.WECHAT_FLAG.*;

@Controller
@RequestMapping("/verifySign")
public class VerifySignController {

    private Logger logger = LoggerFactory.getLogger("VerifySignController");

    private static final String TOKEN = "V587handsomeStefan";

    @ResponseBody
    @RequestMapping(value = "/compare", method = RequestMethod.GET)
    public String compare(HttpServletRequest request, HttpServletResponse response) {

        String signature = request.getParameter("signature");//微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数
        String timestamp = request.getParameter("timestamp");//时间戳
        String nonce = request.getParameter("nonce");//随机数
        String echostr = request.getParameter("echostr");//随机字符串

        String[] params = new String[]{TOKEN, timestamp, nonce};
        Arrays.sort(params);

        StringBuilder sb = new StringBuilder();
        for (String str : params) {
            sb.append(str);
        }
        String paramStr = SHA1Utils.getSha1(sb.toString());
        if (paramStr != null && paramStr.equals(signature)) {
            System.out.println("微信后台映射成功!");
            return echostr;
        }
        return "error";
    }


    @ResponseBody
    @RequestMapping(value = "/compare", method = RequestMethod.POST)
    public String passiveResponse(HttpServletRequest request, HttpServletResponse response) {

        try {
            Map params = XmlUtils.xmlToMap(request);
            String toUserName = (String) params.get("ToUserName");//ToUserName        开发者微信号
            String fromUserName = (String) params.get("FromUserName");//FromUserName     发送方帐号（一个OpenID）
            String createTime = (String) params.get("CreateTime");//CreateTime     消息创建时间 （整型）
            String msgType = (String) params.get("MsgType");//MsgType  text
            String content = (String) params.get("Content");//Content          文本消息内容
            String msgId = (String) params.get("MsgId");//MsgId    消息id，64位整型

            System.out.println("params:" + params);
            logger.info("[passiveResponse]::消息描述msgType:{}", msgType);
            ReplyMessage replyMessage = new ReplyMessage();
            replyMessage.setFromUserName(toUserName);
            replyMessage.setToUserName(fromUserName);
            replyMessage.setCreateTime(DateUtil.formatDate(new Date(), "yyyy-MM-dd hh:mm:ss"));
            if (MESSAGE_TEXT.equals(msgType)) {
                if ("songsong".equals(content)) {
                    replyMessage.setMsgType(MESSAGE_NEWS);
                    List<Articles> artilcles = new ArrayList<>();
                    Articles article1 = new Articles();
                    article1.setTitle("我吃火锅");
                    article1.setDescription("我吃火锅你吃火锅底料description");
                    article1.setPicUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1518179371445&di=52cc5450ed416496ef0c160271ee3c4a&imgtype=0&src=http%3A%2F%2Fg.hiphotos.baidu.com%2Fbaike%2Fc0%253Dbaike72%252C5%252C5%252C72%252C24%253Bt%253Dgif%2Fsign%3D4bd244e100087bf469e15fbb93ba3c49%2F574e9258d109b3decac59fc2cebf6c81800a4c25.jpg");
                    article1.setUrl("http://47.91.155.122:1102/spider-quartz");
                    Articles article2 = new Articles();
                    article2.setTitle("你吃火锅底料");
                    article2.setDescription("你吃火锅底料description");
                    article2.setPicUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1518180264570&di=8d3636b240397f8c5d3cf7f174f757fd&imgtype=jpg&src=http%3A%2F%2Fimg0.imgtn.bdimg.com%2Fit%2Fu%3D3040160180%2C2913699572%26fm%3D214%26gp%3D0.jpg");
                    article2.setUrl("http://47.91.155.122:1102/spider-quartz");
                    artilcles.add(article1);
                    artilcles.add(article2);
                    replyMessage.setArticleCount(String.valueOf(artilcles.size()));
                    replyMessage.setArticles(artilcles);


                } else {
                    replyMessage.setMsgType(MESSAGE_TEXT);
                    replyMessage.setContent("【我爱吃火锅】您发送的消息是:" + content);
                }
            } else if (MESSAGE_VOICE.equals(msgType)) {
                String mediaID = (String) params.get("MediaID");//语音消息媒体id，可以调用多媒体文件下载接口拉取该媒体
                String Format = (String) params.get("Format");//语音格式：amr
                String Recognition = (String) params.get("Recognition");//语音识别结果，UTF8编码

                replyMessage.setMsgType(MESSAGE_TEXT);
                replyMessage.setContent("【我爱吃火锅】您发送的消息是:" + Recognition);
            } else if (MESSAGE_LINK.equals(msgType)) {
                String title = (String) params.get("Title");//消息标题
                String description = (String) params.get("Description");//消息描述
                String url = (String) params.get("Url");//消息连接

                replyMessage.setMsgType(MESSAGE_TEXT);
                replyMessage.setContent("【我爱吃火锅】您发送的消息是:" + content);
            } else if (MESSAGE_IMAGE.equals(msgType)) {
                String picUrl = (String) params.get("PicUrl");//图片链接（由系统生成）
                String mediaId = (String) params.get("MediaId");//图片消息媒体id，可以调用多媒体文件下载接口拉取数据。

                replyMessage.setMsgType(MESSAGE_TEXT);
                replyMessage.setContent("【我爱吃火锅】您发送的消息是:" + content);
            } else if (MESSAGE_EVENT.equals(msgType)) {
                String event = (String) params.get("Event");
                if (MESSAGE_SUBSCRIBE.equals(event)) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("【我爱吃火锅】");
                    stringBuffer.append("欢迎您的关注，请按照菜单提示进行操作:\n\n");
                    stringBuffer.append("1.我吃火锅\n");
                    stringBuffer.append("2.你吃火锅底料\n");

                    replyMessage.setMsgType(MESSAGE_TEXT);
                    replyMessage.setContent(stringBuffer.toString());
                } else if (MESSAGE_UNSUBSCRIBE.equals(event)) {

                }
            } else {

            }
            String msg = XmlUtils.objectToXml(replyMessage);
            System.out.println("msg:" + msg);
            return msg;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }

}
