package com.blog.gfblog.exception;

import com.blog.gfblog.common.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 全局异常处理
 * @author husir
 * @date 2024/05/26
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler .class);

    // 自定义业务性异常处理
    public ResponseResult businessException(HttpServletRequest request,BusinessException e){
        logger.error("发生业务异常，异常原因是：{}",e.getErrorMsg());
        return ResponseResult.error(Integer.parseInt(e.errorCode),e.getErrorMsg());
    }
    // 定义参数异常捕获
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseResult illegalArgumentException(HttpServletRequest request, MethodArgumentNotValidException e){
        logger.error("错误的请求参数：{}",e);
        BindingResult exceptions = e.getBindingResult();
        // 判断异常中是否有错误信息，如果存在就使用异常中的消息，否则使用默认消息
        if (exceptions.hasErrors()) {
            List<ObjectError> errors = exceptions.getAllErrors();
            if (!errors.isEmpty()) {
                // 这里列出了全部错误参数，按正常逻辑，只需要第一条错误即可
                FieldError fieldError = (FieldError) errors.get(0);
                return ResponseResult.error(500,fieldError.getDefaultMessage());
            }
        }
        return ResponseResult.error(500,"请求参数错误");
    }

    // 定义空指针异常捕获
    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public ResponseResult notPointException(HttpServletRequest request,NullPointerException e){
        logger.error("发生空指针异常，异常原因是：{}",e);
        return ResponseResult.error(500,e.getMessage());
    }
    // 运行时异常
    @ExceptionHandler(value = RuntimeException.class)
    @ResponseBody
    public ResponseResult runTimeException(HttpServletRequest request,RuntimeException e){
        logger.error("发生运行时异常，异常原因是：{}",e);
        return ResponseResult.error(500,e.getMessage());
    }
    // 全部异常
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseResult allException(HttpServletRequest request,Exception e){
        logger.error("发生异常，异常原因是：{}",e);
        return ResponseResult.error(500,e.getMessage());
    }
}
