package com.blog.gfblog.service.impl;

import com.blog.gfblog.common.ResponseResult;
import com.blog.gfblog.entity.bo.LoginUser;
import com.blog.gfblog.exception.BusinessException;
import com.blog.gfblog.pojo.SysUser;
import com.blog.gfblog.service.LoginService;
import com.blog.gfblog.utils.JwtUtil;
import com.blog.gfblog.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public ResponseResult login(SysUser userDto) {
        // 1 获取AuthenticationManager 对象 然后调用 authenticate() 方法
        // UsernamePasswordAuthenticationToken 实现了Authentication 接口
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDto.getSysUserName(),
                userDto.getSysUserPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //2 认证没通过 提示认证失败
        if (Objects.isNull(authenticate)) {
            throw new BusinessException("认证失败用户信息不存在");
        }
        //认证通过 使用userid 生成jwt token令牌
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUserDO().getSysUserId().toString();
        Map<String,String> userInfoMap = new HashMap<>();
        userInfoMap.put("userId",userId);
        String token = JwtUtil.generateJwtToken(userInfoMap);
        // 把用户信息存入到redis中
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        redisUtil.set("login:"+userId, loginUser);
        return ResponseResult.Success(map);
    }

    @Override
    public ResponseResult logout() {
        // 1 获取 SecurityContextHolder 中的用户id
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser)authentication.getPrincipal();
        //2 删除redis 中的缓存信
        String key = "login:"+loginUser.getUserDO().getSysUserId().toString();
        redisUtil.del(key);
        return ResponseResult.Success("退出成功!");
    }
}
