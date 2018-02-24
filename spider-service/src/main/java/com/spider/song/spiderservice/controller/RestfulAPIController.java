package com.spider.song.spiderservice.controller;

import com.spider.song.spiderdaodao.entity.Tbl_user;
import com.spider.song.spiderdaodao.mapper.Tbl_userMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * ========================================
 * Created with IntelliJ IDEA.
 * Description:传说中的restfulAPI接口测试类
 * User: songzhengjie
 * Date: 2018-02-24
 * Time: 14:26
 * ========================================
 */
@RestController//省略返回json的responseBody,此配置默认返回json
@RequestMapping("/test")
public class RestfulAPIController {

    @Resource
    private Tbl_userMapper tbl_userMapper;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Object queryUserInfo(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response){
        System.out.println("id:>>>>>>>>>>"+id);
        Tbl_user user = tbl_userMapper.selectByPrimaryKey(id);
        System.out.println(user.toString());
        Map map = new HashMap();
        map.put("song", "18");
        map.put("stefan", 19);
        map.put("jason", 20);
        return map;
    }


    @RequestMapping(value="/", method=RequestMethod.POST)
    public String postUser(@ModelAttribute Tbl_user user) {
        // 处理"/users/"的POST请求，用来创建User
        // 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数
        return "success";
    }

}
