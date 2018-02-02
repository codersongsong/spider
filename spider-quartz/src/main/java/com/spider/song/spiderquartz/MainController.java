package com.spider.song.spiderquartz;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description:
 * @User: songzhengjie
 * @Date: 2018-02-02
 * @Time: 10:12
 */
@Controller
public class MainController {

    private org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Value("${InstituteOfEconomicsURL}")
    private String ioeSpiderURL;

    @RequestMapping("/test")
    public String indexOK(String spiderURL, HttpServletRequest request){
        logger.debug("indexOK::spiderURL = [{}], request = [{}]",spiderURL, request);

        spiderURL = ioeSpiderURL;
        System.out.println("IoeSpiderURL:"+spiderURL);
        logger.info("[indexOK]::spiderURL:{}",spiderURL);
        return "index";
    }

}
