package cn.hunnu.recommender.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {

    @ApiModelProperty("状态码")
    private Integer code;
    @ApiModelProperty("提示")
    private String msg;
    @ApiModelProperty("数据")
    private Object data;

    public static Result success() {
        return new Result(200,"操作成功",null);
    }

    public static Result success(Object data){
        return new Result(200,"操作成功",data);
    }

    public static<T> Result success(T data,String message){
        return new Result(200,message,data);
    }

    public static Result error(String msg){
        return new Result(500,msg,null);
    }

    public static Result error(Integer code, String msg){
        return new Result(code,msg,null);
    }

    public static Result error(){
        return new Result(500,"系统错误，请联系管理员",null);
    }

    public static Result error(Integer code){
        return new Result(code,"错误错误",null);
    }
}