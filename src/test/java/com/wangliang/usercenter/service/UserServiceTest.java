package com.wangliang.usercenter.service;

import com.wangliang.usercenter.model.domain.User;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class UserServiceTest {

    @Resource
    private UserService userService;

    @Test
    public void testAddUser(){
        User user = new User();
        user.setUsername("wangliang");
        user.setUserAccount("123");
        user.setAvatarUrl("https://blog.csdn.net/thinkwon/article/details/106610831");
        user.setUserPassword("123456");
        user.setPhone("123456");
        user.setEmail("1234556");
        user.setPlanetCode("123");
        boolean result = userService.save(user);
        System.out.println(user.getId());
        Assert.assertTrue(result);
    }

    @Test
    void userRegister() {
        // 1.  userPassword为空
        String userAccount = "wlwl";
        String userPassword = "";
        String checkPassword = "12345678";
        long result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1,result);
        // 2. userAccount < 4
        userAccount = "wl";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1,result);
        // 3.userPassword < 8
        userAccount = "wlwl";
        userPassword = "1234567";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1,result);
        // 4.userAccount包含特殊字符
        userAccount = "w[lwl";
        userPassword = "12345678";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1,result);
        // 5.checkPassword不一致
        checkPassword = "123456789";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1,result);
        // 6.userAccount重复
        userAccount = "wangliang";
        checkPassword = "12345678";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1,result);
        // 7.注册用户kobe
        userAccount = "ko【be";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertTrue(result > 0);
    }
}