package com.blog.gfblog.utils;

import cn.hutool.core.collection.CollUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;

import java.util.Calendar;
import java.util.Map;

/**
 * jwt工具类
 * @author husir
 * @date 2024/06/10
 */
public class JwtUtil {
    // jwt加密解密密钥
    public static String secretKey;
    @Value("${jwt.secretKey}")
    public void setSecretKey(String secretKey){
        JwtUtil.secretKey = secretKey;
    }
    // 过期时间
    public static Integer expireDay;
    @Value("${jwt.expireDay}")
    public void setExpireDay(Integer expireDay){
        JwtUtil.expireDay = expireDay;
    }

    /**
     * 生成jwt-token
     * @param generateInfoMap 生成jwt-token信息对象入参（token中存放的就是map中的信息）
     * @return token
     */
    public static String generateJwtToken(Map<String,String> generateInfoMap){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH,JwtUtil.expireDay);
        JWTCreator.Builder builder = JWT.create();
        if (CollUtil.isNotEmpty(generateInfoMap)){
            generateInfoMap.forEach((k,v)->{
                builder.withClaim(k,v);
            });
        }
        builder.withExpiresAt(calendar.getTime());
        return builder.sign(Algorithm.HMAC256(secretKey));
    }

    /**
     * 验证jwt-token
     * @param token
     * @return token存储的信息
     */
    public static DecodedJWT verifyToken(String token){
       return JWT.require(Algorithm.HMAC256(secretKey)).build().verify(token);
    }
}
