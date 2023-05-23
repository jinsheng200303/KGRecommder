package cn.hunnu.recommender.exception;

import cn.hunnu.recommender.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/*
全局异常捕获
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    //运行时异常处理
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Result exceptionHandler(){
        return Result.error();
    }

    @ExceptionHandler(value = CustomException.class)
    @ResponseBody
    //自定义异常捕获
    public Result CustomExceptionHandler(CustomException e){
        log.error("错误原因为"+e.getMessage());
        return new Result().error(e.getCode(),e.getMessage());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    //方法异常捕获
    public Result MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e){
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        String defaultMessage = fieldErrors.get(0).getDefaultMessage();
        log.error("错误原因为"+defaultMessage);
        return new Result().error(defaultMessage);
    }
}
