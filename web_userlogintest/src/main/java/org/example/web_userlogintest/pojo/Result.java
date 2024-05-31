package org.example.web_userlogintest.pojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/*
* 通用返回结果 服务端响应的数据最终都会封装成此对象*/
//统一响应结果  设置统一的响应类 可以更好地管理
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private Integer code;//响应码，1 代表成功; 0 代表失败
    private String msg; //响应信息 描述字符串
    private Object data; //返回的数据
    public static Result success(){
        return new Result(1,"success",null);
    }
    public static Result success(Object data){
        return new Result(1,"success",data);
    }
    //失败响应
    public static Result error(String msg){
        return new Result(0,msg,null);
    }
}