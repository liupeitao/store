package com.example.demo.service.imp;

import com.example.demo.entity.User;
import com.example.demo.mappers.UserMapper;
import com.example.demo.service.IUserService;
import com.example.demo.service.ex.InsertException;
import com.example.demo.service.ex.PassWordNotMatchException;
import com.example.demo.service.ex.UserNotFoundException;
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
    @Override
    public User login(String username, String password) {
        User userIndb =  userMapper.findByName(username);
        if(userIndb == null){
            throw  new UserNotFoundException("用户数据不正确");
        }
        if(userIndb.getIsDelete() == 1){
            throw  new UserNotFoundException("用户已经注销了");
        }
        String dbPasswd = userIndb.getPassword();
        String salt = userIndb.getSalt();
        String passwd = getMD5Passwd(password, salt);
        if(!passwd.equals(dbPasswd)){
            throw new PassWordNotMatchException("密码不匹配");
        }
        User user = new User();
        user.setUid(userIndb.getUid());
        user.setUsername(userIndb.getUsername());
        user.setAvatar(userIndb.getAvatar());
        return user;
    }

    private String getMD5Passwd(String passwd, String salt){
        for(int i = 0; i < 3;i++){
            passwd = DigestUtils.md5DigestAsHex((salt+passwd+salt).getBytes()).toUpperCase();
        }
        return  passwd;
    }


}
