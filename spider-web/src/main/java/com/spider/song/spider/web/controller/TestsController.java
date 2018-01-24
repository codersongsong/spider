package com.spider.song.spider.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <Description> 测试控制器 </Description>
 * <ClassName> TestsController </ClassName>
 *
 * @Author generator
 * @Date 2018年01月23日 18时:28分:06秒
 */
@Controller
@RequestMapping("/tests")
public class TestsController {

	@RequestMapping("/tests")
	public String init(Model model,HttpServletRequest request, HttpServletResponse response) {
		
		return "tests";
	}

}
