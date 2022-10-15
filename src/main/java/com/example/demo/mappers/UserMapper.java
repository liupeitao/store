package com.example.demo.mappers;

import com.example.demo.entity.User;

//用户模块的持久层模块
public interface UserMapper {
    /**
     * 插入用户的数据。
     * @param user 用户的数据
     * @return  返回值判断是否成功 ， 影响的行数
     */
    Integer insert(User user);

    /**
     * 按照名字查询
     * @param userName
     * @return
     */
    User findByName(String userName);
}
