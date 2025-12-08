package com.greenpaperuj.wuziqi.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public class UserMapper {
    @Insert("insert into user (username, password) values " +
    "(#{username}, #{password)")
    void signup(User user);
}
