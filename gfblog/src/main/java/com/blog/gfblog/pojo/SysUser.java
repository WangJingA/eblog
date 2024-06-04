package com.blog.gfblog.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author husir
 * @since 2024-06-04
 */
public class SysUser extends Model<SysUser> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableId(value = "sys_user_id", type = IdType.AUTO)
    private String sysUserId;

    /**
     * 用户名
     */
    private String sysUserName;

    /**
     * 用户密码
     */
    private String sysUserPassword;

    /**
     * 用户头像地址
     */
    private String sysUserIcon;

    /**
     * 注册时间
     */
    private LocalDateTime sysUserRegisterTime;

    /**
     * 更新时间
     */
    private LocalDateTime sysUserUpdateTime;

    /**
     * 是否冻结（0：正常，1：冻结）
     */
    private Integer sysUserFrozen;


    public String getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(String sysUserId) {
        this.sysUserId = sysUserId;
    }

    public String getSysUserName() {
        return sysUserName;
    }

    public void setSysUserName(String sysUserName) {
        this.sysUserName = sysUserName;
    }

    public String getSysUserPassword() {
        return sysUserPassword;
    }

    public void setSysUserPassword(String sysUserPassword) {
        this.sysUserPassword = sysUserPassword;
    }

    public String getSysUserIcon() {
        return sysUserIcon;
    }

    public void setSysUserIcon(String sysUserIcon) {
        this.sysUserIcon = sysUserIcon;
    }

    public LocalDateTime getSysUserRegisterTime() {
        return sysUserRegisterTime;
    }

    public void setSysUserRegisterTime(LocalDateTime sysUserRegisterTime) {
        this.sysUserRegisterTime = sysUserRegisterTime;
    }

    public LocalDateTime getSysUserUpdateTime() {
        return sysUserUpdateTime;
    }

    public void setSysUserUpdateTime(LocalDateTime sysUserUpdateTime) {
        this.sysUserUpdateTime = sysUserUpdateTime;
    }

    public Integer getSysUserFrozen() {
        return sysUserFrozen;
    }

    public void setSysUserFrozen(Integer sysUserFrozen) {
        this.sysUserFrozen = sysUserFrozen;
    }

    @Override
    protected Serializable pkVal() {
        return this.sysUserId;
    }

    @Override
    public String toString() {
        return "SysUser{" +
        "sysUserId=" + sysUserId +
        ", sysUserName=" + sysUserName +
        ", sysUserPassword=" + sysUserPassword +
        ", sysUserIcon=" + sysUserIcon +
        ", sysUserRegisterTime=" + sysUserRegisterTime +
        ", sysUserUpdateTime=" + sysUserUpdateTime +
        ", sysUserFrozen=" + sysUserFrozen +
        "}";
    }
}
