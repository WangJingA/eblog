package com.blog.gfblog.service.impl;

import com.blog.gfblog.entity.bo.LoginUser;
import com.blog.gfblog.pojo.SysUser;
import com.blog.gfblog.service.SysMenuService;
import com.blog.gfblog.service.SysRoleService;
import com.blog.gfblog.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UserDetailServiceImpl implements UserDetailsService {

    // 用户信息
    @Autowired
    private SysUserService userService;
    //权限菜单信息
    @Autowired
    private SysMenuService sysMenuService;
    //角色信息
    @Autowired
    private SysRoleService sysRoleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser userDO = userService.getUserByUsername(username);
        // 获取用户所有的角色
        List<String> roles = sysRoleService.selectRolesByUserId(new Long(userDO.getSysUserId()));
        Set<String> roleSet = roles.stream().map(s -> "ROLE_" + s).collect(Collectors.toSet());
        // 获取用户所有的权限
        List<String> permissions = sysMenuService.selectPermsByUserId(new Long(userDO.getSysUserId()));
        permissions.addAll(roleSet);
        return new LoginUser(userDO, permissions);
    }
}
