package com.greenpaperuj.wuziqi.mapper;

import com.greenpaperuj.wuziqi.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserMapper {
    @Insert("insert into user (username, password) values (#{username}, #{password})")
    void signup (User user);

    @Select("select * from user where username = #{username} and password = #{password}")
    User selectByUsernameAndPassword(String username, String password);

    @Select("select * from user")
    List<User> list();

    @Select("select * from user where id = #{id}")
    User selectById(Integer id);

    @Update("update user set score = #{score} where id = #{id}")
    void updateScore(Integer score, Integer id);
}
