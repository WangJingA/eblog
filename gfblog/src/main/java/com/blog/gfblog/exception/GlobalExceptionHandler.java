package com.blog.gfblog.exception;

import com.blog.gfblog.common.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;

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
    // 定义空指针异常捕获
    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public ResponseResult notPointException(HttpServletRequest request,NullPointerException e){
        logger.error("发生空指针异常，异常原因是：{}",e);
        return ResponseResult.error();
    }
    // 运行时异常
    @ExceptionHandler(value = RuntimeException.class)
    @ResponseBody
    public ResponseResult runTimeException(HttpServletRequest request,RuntimeException e){
        logger.error("发生运行时异常，异常原因是：{}",e);
        return ResponseResult.error();
    }
    // 全部异常
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseResult allException(HttpServletRequest request,Exception e){
        logger.error("发生异常，异常原因是：{}",e);
        return ResponseResult.error();
    }
}
