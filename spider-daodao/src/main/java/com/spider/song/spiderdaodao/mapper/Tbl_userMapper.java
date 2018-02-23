package com.spider.song.spiderdaodao.mapper;

import com.spider.song.spiderdaodao.entity.Tbl_user;

public interface Tbl_userMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Tbl_user record);

    int insertSelective(Tbl_user record);

    Tbl_user selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Tbl_user record);

    int updateByPrimaryKey(Tbl_user record);
}