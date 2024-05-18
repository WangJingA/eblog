package com.blog.gfblog.aspect;

import com.google.gson.Gson;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 日志打印切面类
 * 实现日志循环打印
 * @author husir
 * @date 2024/05/03
 */
@Component
@Aspect
public class LogPrintAspect {

    Logger logger = LoggerFactory.getLogger(LogPrintAspect.class);
    /**
     * 切入点
     */
    @Pointcut("execution(public * com.blog.gfblog.controller.*.*(..))")
    public void pointCut(){

    }

    @Before("pointCut()")
    public void beforeMethodLosgsPrint(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        logger.info("------开始打印请求---");
        // 打印请求的路径和方法名称
        logger.info("请求方法路径：{},名称是：{}",
                joinPoint.getSignature().getDeclaringType(),
                joinPoint.getSignature().getName());
        // 打印请求的参数
        logger.info("请求的参数是：{}",joinPoint.getArgs());
        // 打印请求的ip
        logger.info("IP:{}",attributes.getRequest().getRemoteAddr());
        // 打印请求的URI
        logger.info("URI:{}",attributes.getRequest().getRequestURI());
    }
    /**
     * 后置通知-打印结束标识
     */
    @After("pointCut()")
    public void after(){
        logger.info("-----------结束END----------");
        logger.info("");
    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        logger.info("Response Arg:{}",new Gson().toJson(result));
        logger.info("Time Consuming:{}",System.currentTimeMillis()-startTime);
        return result;
    }
}
