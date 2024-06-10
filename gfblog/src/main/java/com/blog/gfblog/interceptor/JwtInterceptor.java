package com.blog.gfblog.interceptor;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.blog.gfblog.utils.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

// 整合拦截器实现统一token 验证
public class JwtInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        Map<String, Object> map = new HashMap<>();
        if (StrUtil.isEmpty(token)) {
            map.put("status", "514");
            map.put("message", "token为空");
            String s = new ObjectMapper().writeValueAsString(map);
            response.setContentType("application/json;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(s);
            return false;
        }
        // jwt异常处理
        try{
            JwtUtil.verifyToken(token);
            return true;
        }catch (SignatureVerificationException e1) {
            map.put("status", "510");
            map.put("message", "签名异常");
        }catch (TokenExpiredException e2) {
            map.put("status", "511");
            map.put("message", "token已过期");
        }catch (AlgorithmMismatchException e3) {
            map.put("status", "512");
            map.put("message", "加密算法异常");
        }catch (JWTDecodeException e4) {
            map.put("status", "513");
            map.put("message", "token解密异常");
        }catch (Exception e) {
            map.put("status", "500");
            map.put("message", "系统异常");
        }
        String info = new ObjectMapper().writeValueAsString(map);
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(info);
        return false;
    }
}
