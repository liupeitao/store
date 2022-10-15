package com.example.demo.mappers;

import com.example.demo.entity.User;
import com.example.demo.mappers.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
/**
 *表示启动单元测试类， 需要一个参数， 必须是SpringRunner的示例类型。
 */
@RunWith(SpringRunner.class)
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public  void insert(){
        User u = new User();
        u.setUsername("leriupeitao");
        u.setPassword("rooft");
        Integer rows = userMapper.insert(u);
        System.out.println(rows);
    }

    /**可以单独运行， 不用启动整个项目
     * 1. 必须有@Test
     * 2. 返回值必须是void
     * 3. 参数列表不能指定任何类型
     * 4. 方法必须是public修饰。
     */
    @Test
    public void findByName(){
        String name = "liupeitao";
        User u = userMapper.findByName(name);
        System.out.println(u.toString());
    }
}
