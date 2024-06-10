package com.blog.gfblog.service;

import com.blog.gfblog.pojo.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author husir
 * @since 2024-06-10
 */
public interface SysMenuService extends IService<SysMenu> {
    List<String> selectPermsByUserId(Long userId);
}
