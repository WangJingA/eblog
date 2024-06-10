package com.blog.gfblog.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.gfblog.mapper.SysUserMapper;
import com.blog.gfblog.pojo.SysUser;

public interface SysUserService extends IService<SysUser> {
    SysUser getUserByUsername(String username);
}
