package com.spider.song.spiderwechat.controller;


import com.spider.song.spidercommon.utils.SHA1Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

@Controller
@RequestMapping("/verifySign")
public class VerifySignController {

    private Logger logger = LoggerFactory.getLogger("VerifySignController");

    private static final String TOKEN = "V587handsomeStefan";

    @RequestMapping("/compare")
    @ResponseBody
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
}
