package org.example.web_userlogintest.interceptor;

import com.alibaba.fastjson.JSONObject;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.example.web_userlogintest.pojo.Result;
import org.example.web_userlogintest.utils.JwtUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
@Slf4j
@Component
public class LoginCheckinterceptor implements HandlerInterceptor {
    //目标资源usercontorller
    @Override//目标资源方法运行前运行，返回true：放行 返回false ，不放行
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("prehandle...");

        //1.获取请求url
        String url=request.getRequestURI().toString();
        log.info("请求的url：{}",url);
        //2.判断请求url中是否包含login，如果包含，说明是登陆操作，放行
        if(url.contains("login")){
            log.info("登陆操作，放行...");
            return true;
        }
        //3.获取请求头中的令牌（token）
        String jwt=request.getHeader("token");
        //4.判断令牌是否存在，如果不存在 返回错误结果
        if(!StringUtils.hasLength(jwt)){
            log.info("请求头token为空，返回未登陆的信息");
            Result error=Result.error("not_login");
            String notLogin= JSONObject.toJSONString (error);
            response.getWriter().write(notLogin);
            return false;
        }
        //5.解析token，如果解析失败，返回错误结果（未登录）
        try {
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("令牌解析失败!");
            //创建响应结果对象
            Result responseResult = Result.error("NOT_LOGIN");
            //把Result对象转换为JSON格式字符串 (fastjson是阿里巴巴提供的用于实现对象和json的转换工具类)
            String json = JSONObject.toJSONString(responseResult);
            //设置响应头
            response.setContentType("application/json;charset=utf-8");
            //响应
            response.getWriter().write(json);
            return false;
        }
    //6.放行
        log.info("令牌合格 放行");
        return true;
    }

    @Override//目标资源方法运行后运行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle");
    }

    @Override//视图渲染完毕后最后运行
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("aftercompletion...");
    }
}
