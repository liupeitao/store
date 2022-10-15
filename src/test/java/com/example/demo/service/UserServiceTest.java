package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.mappers.UserMapper;
import com.example.demo.service.ex.ServiceException;
import com.example.demo.service.imp.UserService;
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
public class UserServiceTest {
    @Autowired
    private IUserService userService;
    @Test
    public  void reg(){
        try {
            User u = new User();
            u.setUsername("liuyang");
            u.setPassword("rooft");
            Integer rows = userService.reg(u);
            System.out.println(rows);
        }catch (ServiceException e){
           System.out.println(e.getClass().getSimpleName());
           System.out.println(e.getMessage());
        }
    }

    /**可以单独运行， 不用启动整个项目
     * 1. 必须有@Test
     * 2. 返回值必须是void
     * 3. 参数列表不能指定任何类型
     * 4. 方法必须是public修饰。
     */
}
