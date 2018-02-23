package com.spider.song.spiderdaodao.controller;

import com.spider.song.spiderdaodao.entity.Tbl_user;
import com.spider.song.spiderdaodao.mapper.Tbl_userMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

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
    public String index(){

        Tbl_user user = new Tbl_user();
        user.setName("song");
        user.setAge(18);
        user.setInterest("coding");
        int b = tbl_userMapper.insert(user);
        System.out.println(b);

        return "index";
    }

}
