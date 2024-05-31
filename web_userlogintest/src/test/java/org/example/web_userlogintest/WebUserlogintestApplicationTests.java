package org.example.web_userlogintest;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.example.web_userlogintest.mapper.UserMapper;
import org.example.web_userlogintest.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@SpringBootTest
class WebUserlogintestApplicationTests {
    //UserMapper 类型的对象
   // @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        List<User> userList =userMapper.list();
        userList.stream().forEach(user->{
            System.out.println(user);
        });
    }
    //生成令牌 校验令牌
    @Test
    public void testGenJwt(){
        Map<String,Object> clasims=new HashMap<>();
        clasims.put("id",1);
        clasims.put("username","tmo");
        String jwt=Jwts.builder()//建立一个jwt令牌
                .signWith(SignatureAlgorithm.HS256,"sssss") //签名算法
                .setClaims(clasims)//自定义内容给 载荷
                .setExpiration(new Date(System.currentTimeMillis()+3600*1000))//设置有效期为一个小时
                .compact();//拿到一个字符串类型的返回值 这就是shengchengdejwt令牌
        System.out.println(jwt);
    }
    @Test
    public void testParseJwt(){
        //解析jwt
        Claims claims=Jwts.parser()
                .setSigningKey("sssss")//签名密钥
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwiZXhwIjoxNzEzOTU0NTU1LCJ1c2VybmFtZSI6InRtbyJ9.f5_YzTvTCDK9epPrvJRD334VIPYF74J7GEkk4nW-0eQ")
                .getBody();
        System.out.println(claims);
    }

}
