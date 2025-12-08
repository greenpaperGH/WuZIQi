package com.greenpaperuj.wuziqi.mapper;

import com.greenpaperuj.wuziqi.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Insert("insert into user (username, password) values (#{username}, #{password})")
    void signup (User user);

    @Select("select * from user where username = #{username} and password = #{password}")
    User selectByUsernameAndPassword(String username, String password);
}
