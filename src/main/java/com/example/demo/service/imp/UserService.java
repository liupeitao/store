package com.example.demo.service.imp;

import com.example.demo.entity.User;
import com.example.demo.mappers.UserMapper;
import com.example.demo.service.IUserService;
import com.example.demo.service.ex.InsertException;
import com.example.demo.service.ex.UsernameDuplicatedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public Integer reg(User user) {
        String name = user.getUsername();
        User result = userMapper.findByName(name);
        if(result != null){
            throw new UsernameDuplicatedException("用户已经被占用");
        }
        String oldPasswd = user.getPassword();
        String salt = UUID.randomUUID().toString().toUpperCase();
        user.setSalt(salt);
        String md5Passwd = getMD5Passwd(oldPasswd, salt);
        user.setPassword(md5Passwd);
        user.setIsDelete(0);
        user.setCreatedUser(user.getUsername());
        user.setModifiedUser(user.getModifiedUser());
        Date date = new Date();
        user.setCreatedTime(date);
        user.setModifiedTime(date);
        Integer rows = userMapper.insert(user);
        if(rows != 1){
            throw  new InsertException("用户在插入的时候产生了未知异常");
        }
        return rows;
    }
    private String getMD5Passwd(String passwd, String salt){
        for(int i = 0; i < 3;i++){
            passwd = DigestUtils.md5DigestAsHex((salt+passwd+salt).getBytes()).toUpperCase();
        }
        return  passwd;
    }
}
