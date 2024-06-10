package com.blog.gfblog.service;

import com.blog.gfblog.pojo.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author husir
 * @since 2024-06-10
 */
public interface SysRoleService extends IService<SysRole> {
    List<String> selectRolesByUserId(@Param("userId") Long userId);
}
