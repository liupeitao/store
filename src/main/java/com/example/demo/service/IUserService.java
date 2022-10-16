package com.example.demo.service;

import com.example.demo.entity.User;

public interface IUserService {
    /**
     * 注册方法
     * @param user
     * @return
     */
    public Integer reg(User user);

    /**
     * 用户登录
     * @param user
     * @return
     */
    public User login(String username, String passwd);
}
