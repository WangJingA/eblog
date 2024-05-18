package com.blog.gfblog.controller;


import cn.hutool.core.util.StrUtil;
import cn.hutool.system.HostInfo;
import cn.hutool.system.OsInfo;
import cn.hutool.system.SystemUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blog.gfblog.common.MinioCommon;
import com.blog.gfblog.common.ResponseResult;
import com.blog.gfblog.constant.AdminConstant;
import com.blog.gfblog.entity.dto.AdminLoginDTO;
import com.blog.gfblog.entity.dto.user.SystemInfoAndUserDetailDTO;
import com.blog.gfblog.entity.dto.user.UserListPageDTO;
import com.blog.gfblog.mapper.AdMapper;
import com.blog.gfblog.mapper.SysUserMapper;
import com.blog.gfblog.pojo.Ad;
import com.blog.gfblog.pojo.SysUser;
import com.blog.gfblog.service.*;
import io.minio.ObjectWriteResponse;
import io.swagger.annotations.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.Multipart;
import java.util.List;
import java.util.UUID;

@RestController
@Api(tags = "易博客管理员功能接口文档")
@RequestMapping( "/admin")
public class AdminController {
    @Autowired
    private MinioCommon minioCommon;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private ArticleTagService articleTagService;
    @Autowired
    private ArticleTypeService articleTypeService;
    @Autowired
    private ArticleTagListService articleTagListService;
    @Autowired
    private SysUserService userService;
    @Autowired
    private AdMapper adMapper;
    @Autowired
    SysUserMapper userMapper;
    @Autowired
    SysUserMapper sysUserMapper;

    @ApiOperation("管理员-登录")
    public ResponseResult adminLogin(@RequestBody @ApiParam AdminLoginDTO adminLoginDTO){
        return null;
    }

    /**
     * 管理员界面用户信息详情
     * @param userListPageDTO 查询dto
     * @return 用户详情
     */
    @ApiOperation("管理员-用户管理-用户列表")
    @PostMapping("/list_user_info")
    public ResponseResult listUserInfo(@RequestBody @Validated @ApiParam UserListPageDTO userListPageDTO){
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        if (StrUtil.isNotEmpty(userListPageDTO.getUserName())){
            queryWrapper.like(SysUser::getSysUserName,userListPageDTO.getUserName());
        }
        IPage<SysUser> page = new Page<>();
        List<SysUser> sysUserList = userMapper.selectList(queryWrapper);
        page.setPages(userListPageDTO.getPageIndex());
        page.setSize(userListPageDTO.getPageSize());
        IPage<SysUser> userIPage = new Page<>();
        userIPage.setCurrent(userListPageDTO.getPageIndex());
        userIPage.setRecords(sysUserList);
        userIPage.setSize(userListPageDTO.getPageSize() );
        userIPage.setTotal(sysUserList.size());
        return  ResponseResult.Success(userIPage);
    }

    /**
     * 管理员界面首页信息类
     * @return 首页信息详情
     */
    @ApiOperation("管理员-主界面-用户和机器数据")
    @PostMapping( "/home_page_info")
    public ResponseResult adminIndex(){
        LambdaQueryWrapper<Ad> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Ad::getAdTypeId, AdminConstant.MANGE_HOME_PAGE_AD_TYPE);
        List<Ad> ads = adMapper.selectList(queryWrapper);
        // 系统信息
        OsInfo osInfo = SystemUtil.getOsInfo();
        HostInfo hostInfo = SystemUtil.getHostInfo();
        // 文章数量
        int articleCount = articleService.count();
        int articleTagCount = articleTagListService.count();
        int userCount = userService.count();
        int articleTypeCpunt = articleTypeService.count();
        SystemInfoAndUserDetailDTO systemInfoAndUserDetailDTO = new SystemInfoAndUserDetailDTO();
        systemInfoAndUserDetailDTO.setSystemType(osInfo.getName())
                .setServiceIp(hostInfo.getAddress())
                .setArticleNumber(articleCount)
                .setTagNumber(articleTagCount)
                .setArticleTypeCount(articleTypeCpunt)
                .setAds(ads)
                .setUserNumber(userCount);
        return ResponseResult.Success(systemInfoAndUserDetailDTO);
    }

    /**
     * 测试minion上传文件
     * @param file
     * @return
     */
    @ApiOperation("管理员-上传头像")
    @ApiImplicitParams(@ApiImplicitParam(value = "file",type = "File",required = true))
    @PostMapping("/upload_user_icon")
    public ResponseResult updload(@RequestBody MultipartFile file){
        String upload = minioCommon.upload(file, "blog");
        return ResponseResult.Success(upload);
    }

    /**
     * 删除用户，真删除
     * @param userId 用户id
     * @return
     */
    @ApiOperation("管理员-用户管理-删除用户")
    @ApiImplicitParams(
            @ApiImplicitParam(value = "userId",type = "String",required = true)
    )
    @PostMapping("/del_user")
    public ResponseResult delUserByUserId(@RequestParam("userId") String userId){
        int delete = userMapper.deleteById(userId);
        return ResponseResult.Success();
    }

    /**
     * 修改用户信息
     * @param sysUser 用户信息
     * @return
     */
    @ApiOperation("管理员-用户管理-修改用户信息")
    @PostMapping("/update_user")
    public ResponseResult updateUserByUserId(@RequestBody @ApiParam SysUser sysUser){
        int update = sysUserMapper.updateById(sysUser);
        return ResponseResult.Success();
    }

    /**
     * 新增用户
     * @param sysUser 新增用户信息
     * @return
     */
    @ApiOperation("管理员-用户管理-新增用户")
    @PostMapping("/add_user")
    public ResponseResult addUser(@RequestBody @ApiParam SysUser sysUser){
        sysUser.setSysUserId(UUID.randomUUID().toString());
        sysUserMapper.insert(sysUser);
        return ResponseResult.Success();
    }
}
