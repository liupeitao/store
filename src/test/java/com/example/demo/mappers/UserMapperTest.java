package com.example.demo.mappers;

import com.example.demo.entity.User;
import com.example.demo.mappers.UserMapper;
import com.example.demo.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.SimpleTypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest
/**
 *表示启动单元测试类， 需要一个参数， 必须是SpringRunner的示例类型。
 */
@RunWith(SpringRunner.class)
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private IUserService userService;
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

    @Test
    public  void findByUid(){
        User user =  userMapper.findByUid(36);
        System.out.println(user);
    }
    @Test
    public  void updatePasswordByUid(){
        Integer  i =  userMapper.updatePasswordByUid(40,"liupeitao","管理员",new Date());
        System.out.println(i);
    }

    @Test

    public void changePassword() {
        userService.changePassword(40, "liuh","liupeitao");
    }
    @Test
    public  void updateInfoByUid(){
        User user = new User();
        user.setUid(40);
        user.setPhone("88888888888");
        user.setEmail("aaaa@outlook.com");
        user.setGender(1);

        userMapper.updateInfoByUid(user);
    }

    @Test
    public void getByUid(){
        System.out.println(userService.getByUid(40));
    }

    @Test
    public void changeInfo(){
//        System.out.println(userService.changeInfo(););
        User u = userService.getByUid(40);
        u.setEmail("liu@outlook.com");
        u.setPhone("13222222222222");
        u.setGender(1);
        userService.changeInfo(40, "tret4543", u);
    }
}
