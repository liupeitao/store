package com.example.demo.mappers;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

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

    User findByUid(Integer uid);

    /**
     * 多个参数必须要加@param 单个参数的可以不用加，这是mybatis的规则。
     *
     * @param uid
     * @param password
     * @param modifiedUser
     * @param modifiedTime
     * @return
     */
    Integer updatePasswordByUid(@Param("uid") Integer uid,
                                @Param("password") String password,
                                @Param("modifiedUser") String modifiedUser,
                                @Param("modifiedTime") Date modifiedTime);

    Integer updateInfoByUid(User user);

    Integer updateAvatarByUid(@Param("uid")Integer uid,
                                     @Param("avatar") String avatar,
                                     @Param("modifiedUser") String modifiedUser,
                                     @Param("modifiedTime") Date modifiedTime);
}
