package com.blog.gfblog.filter;


import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.blog.gfblog.entity.bo.LoginUser;
import com.blog.gfblog.exception.BusinessException;
import com.blog.gfblog.utils.JwtUtil;
import com.blog.gfblog.utils.RedisUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class JwtAuthenticationFilter  extends OncePerRequestFilter {
    @Autowired
    private RedisUtil redisUtil;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws BusinessException, ServletException, IOException, ServletException, IOException {
        // 获取token
        String token = request.getHeader("token");
        if (StrUtil.isEmpty(token)) {
            // token不存在 放行 并且直接return 返回
            filterChain.doFilter(request, response);
            return;
        }
        // 解析token
        String userId = null;
        try {
            DecodedJWT decodedJWT = JwtUtil.verifyToken(token);
            userId = decodedJWT.getSubject();
        } catch (Exception e) {
            throw new BusinessException("token非法");
        }
        // 获取userid 从redis中获取用户信息
        String redisKey = "login:" + userId;
        LoginUser loginUser = (LoginUser)redisUtil.get(redisKey);
        if (Objects.isNull(loginUser)) {
            throw new BusinessException("用户未登录");

        }

        //将用户信息存入到SecurityContextHolder
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        // 放行
        filterChain.doFilter(request, response);
    }
}
