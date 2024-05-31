package org.example.web_userlogintest.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.web_userlogintest.pojo.Result;
import org.example.web_userlogintest.pojo.User;
import org.example.web_userlogintest.service.UserService;
import org.example.web_userlogintest.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
//controller 接受请求 做出响应
@Slf4j
@RestController
@RequestMapping("/api")
public class LoginController {
    //进行注入操作  注入一个service   面向接口的方式编程
    @Autowired
    //进行登陆操作
    private UserService userservice;
    //@RequestMapping 将http请求映射到mvc和rest控制器的方法上
    //因为发起的是post请求  指定请求路径
    @PostMapping("/login")//处理http中的post请求 并将请求映射到具体的处理方法中
    @GetMapping("/test")
    //请求参数名与形参对象属性名相同  pojo 实体 放实体类
                        //将js格式的数据封装到实体对象中
    //requesbody 将方法返回值直接响应  若返回值是一个对象或者一个集合list 将结果转为js格式的数据
    public Result login(@RequestBody User user1){
        //输出日志
        log.info("登录：{}",user1);
        //传递一个User 对象  登陆操作 进行校验查询
        User u=userservice.login(user1);
        //数据库查询成功 有此用户信息 可以登陆成功  生成令牌 下发令牌
        if(u!=null){
            Map<String,Object> clasim=new HashMap<>();
            clasim.put("id",u.getId());
            clasim.put("username",u.getUsername());
            clasim.put("password",u.getPassword());
            //生成令牌
            String jwt=JwtUtils.generateJwt(clasim);//jwt中包含了当前登录的员工信息
            return Result.success(jwt);//响应令牌
        }
        return Result.error("用户名或密码错误");
       //return u!=null?Result.success():Result.error("用户名或密码错误");
    }
    public String test(){
        return "sss";
    }
}
