package org.example.web_userlogintest.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.web_userlogintest.pojo.User;

import java.util.List;
//持久层 数据的访问操作 对数据进行增删改查
//为什么要用三层架构 因为写在一起复用性差 难以维护和管理
@Mapper//在运行时会自动生成该接口的实现类对象(代理对象)，并且将该对象交给ioc容器管理
// 控制反转 ioc 对象的创建控制权由程序自身转移到外部 这种思想称为控制反转
//依赖注入 di容器为应用程序提供运行时，所依赖的资源 称为 依赖注入
//ioc容器中创建管理的对象称为bean对象
public interface UserMapper {

    //查询全部用户信息
    @Select("select * from user")
    public List<User> list();

    /**
     *
     * @param user
     * @return
     */
                                    //参数写成动态的
    @Select("select * from user where username=#{username} and password=#{password}")
    public abstract User getUserInformation(User user);

}
