package com.example.mapper;

import com.example.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @mybatis UserMapper
 * @项目：test
 * @创建者：linmin
 * @创建时间：2018/1/16
 * @公司：汽车易生活
 */
public interface UserDaoMapper {

    @Select("SELECT id,name,age FROM user")
    @Results({
            @Result(property = "name",  column = "name"),
            @Result(property = "age", column = "age")
    })
    List<User> findAll();

    @Insert("INSERT INTO user(name,age) values(#{name},#{age})")
    int insert(User user);

    @Delete("DELETE FROM user where id = #{id}")
    void delete(Integer id);

    User findOneById(Integer id);

    void update(User user);
}
