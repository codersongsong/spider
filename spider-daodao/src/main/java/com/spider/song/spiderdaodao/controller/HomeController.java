package com.spider.song.spiderdaodao.controller;

import com.spider.song.spiderdaodao.entity.Tbl_user;
import com.spider.song.spiderdaodao.mapper.Tbl_userMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ========================================
 * Created with IntelliJ IDEA.
 * Description:
 * User: songzhengjie
 * Date: 2018-02-23
 * Time: 18:37
 * ========================================
 */
@Controller
@RequestMapping
public class HomeController {


    @Resource
    private Tbl_userMapper tbl_userMapper;

    @RequestMapping("/")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response){

        ModelAndView mv = new ModelAndView();

        long id = 5;
        Tbl_user user = tbl_userMapper.selectByPrimaryKey(id);
        System.out.println(user.toString());
        mv.addObject("user", user);
        mv.setViewName("index");

        return mv;
    }

}
