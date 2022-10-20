package com.example.demo.service.imp;

import com.example.demo.entity.User;
import com.example.demo.mappers.UserMapper;
import com.example.demo.service.IUserService;
import com.example.demo.service.ex.*;
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

    @Override
    public void changePassword(Integer uid, String oldPassword, String newPassword) {
        User user =  userMapper.findByUid(uid);
        if(user == null || user.getIsDelete()==1){
            throw  new UpdateException("用户更新失败,可能是因为用户数据不存有");
        }
       String oldMD5Password = getMD5Passwd(oldPassword, user.getSalt());
        if(!user.getPassword().equals(oldMD5Password)){
            System.out.println(oldMD5Password);
            System.out.println(user.getPassword());
            throw new PassWordNotMatchException("更新失败，密码错误");
        }
        String newMD5Password = getMD5Passwd(newPassword, user.getSalt());
        Integer rows = userMapper.updatePasswordByUid(uid, newMD5Password, user.getUsername(), new Date());
        if (rows != 1){
            throw new UpdateException("更新失败，未知异常");
        }
    }

    @Override
    public User getByUid(Integer uid) {
        User user = userMapper.findByUid(uid);
        if(user == null || user.getIsDelete() == 1){
            throw  new UserNotFoundException("更新信息失败， 用户不存有");
        }
        // 减少对象传递开销 ，传给前端修改信息的页面，有写东西并不需要。
        User res = new User();
        res.setUsername(user.getUsername());
        res.setPhone(user.getPhone());
        res.setEmail(user.getEmail());
        res.setGender(user.getGender());
        return user;
    }

    /**
     *
     * @param uid
     * @param username  记录谁修改了当前用户信息 一般是自己， 也可能是管理员
     * @param user  用户对象的数据
     */
    @Override
    public void changeInfo(Integer uid, String username, User user) {
        userMapper.findByUid(uid);
        user.setUid(uid);
        user.setModifiedUser(username);
        user.setModifiedTime(new Date());
        Integer rows =  userMapper.updateInfoByUid(user);
        if(rows != 1){
            throw  new UpdateException("更新信息失败,未知异常");
        }
    }

    @Override
    public void changeAvatar(Integer uid, String avartar, String username) {
        User res = userMapper.findByUid(uid);
        if(res == null || res.getIsDelete() == 1){
            throw  new UserNotFoundException("无法修改头像， 因为用户不存在");
        }
        int rows =  userMapper.updateAvatarByUid(uid, avartar, username,new Date());
        if (rows != 1){
            throw  new UpdateException("更新用户头像失败");
        }
    }
}
