package org.example.web_userlogintest.service.impl;

import org.example.web_userlogintest.mapper.UserMapper;
import org.example.web_userlogintest.pojo.User;
import org.example.web_userlogintest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
//service做业务逻辑处理
@Service
@Component //交给ioc容器
public class UserServiceImpl implements UserService {
    //注入
    @Autowired
    private UserMapper userMapper;
    @Override
    public User login(User user){
        //mapper 持久层 操作数据库
        return userMapper.getUserInformation(user);
    }
    //User getTestUser(User user);
}
