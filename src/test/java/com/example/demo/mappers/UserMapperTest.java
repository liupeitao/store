package com.example.demo.mappers;

import com.example.demo.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTest {
    @Autowired
    private  UserMapper userMapper;

    @Test
    public  void insert(){
        User u = new User();
        u.setUsername("leriupeitao");
        u.setPassword("rooft");
        Integer rows = userMapper.insert(u);
        System.out.println(rows);
    }
}
