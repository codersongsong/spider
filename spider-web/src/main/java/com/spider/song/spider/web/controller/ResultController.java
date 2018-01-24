package com.spider.song.spider.web.controller;
import com.gomeplus.frame.controller.AbstractWebController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <Description> 异常结果控制器 </Description>
 * <ClassName> ResultController </ClassName>
 *
 * @Author generator
 * @Date 2018年01月23日 18时:28分:06秒
 */
@Controller
@RequestMapping("/result")
public class ResultController extends AbstractWebController {

    @RequestMapping("/404error")
    public String error404(Model model, HttpServletRequest request, HttpServletResponse response) {

        return "error/404";
    }

    @RequestMapping("/500error")
    public String error500(Model model, HttpServletRequest request, HttpServletResponse response) {

        return "error/500";
    }

}