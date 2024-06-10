package com.blog.gfblog.entity.bo;

import com.alibaba.fastjson.annotation.JSONField;
import com.blog.gfblog.pojo.SysUser;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * spring-security框架登录用户信息类
 * @author husir
 * @date 2024/06/10
 */
@Data
@NoArgsConstructor
public class LoginUser  implements UserDetails {
    // 用户信息
    private SysUser userDO;
    // 权限信息
    private List<String> permissions;
    @JSONField(serialize = false)
    private List<SimpleGrantedAuthority> authorities;
    public LoginUser(SysUser userDO, List<String> permissions){
        this.userDO = userDO;
        this.permissions = permissions;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 将权限信息封装成 SimpleGrantedAuthority
        if (authorities != null) {
            return authorities;
        }
        authorities = this.permissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.userDO.getSysUserPassword();
    }

    @Override
    public String getUsername() {
        return this.userDO.getSysUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
}
