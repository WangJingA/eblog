package com.blog.gfblog.controller;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.blog.gfblog.common.ResponseResult;
import com.blog.gfblog.entity.dto.article.ArticleMainDataDTO;
import com.blog.gfblog.entity.query.ArticleQuery;
import com.blog.gfblog.mapper.ArticleMapper;
import com.blog.gfblog.mapper.ArticleTypeMapper;
import com.blog.gfblog.mapper.SysUserMapper;
import com.blog.gfblog.pojo.Article;
import com.blog.gfblog.pojo.ArticleType;
import com.blog.gfblog.pojo.SysUser;
import com.blog.gfblog.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 文章表 前端控制器
 * </p>
 *
 * @author husir
 * @since 2024-06-04
 */
@Api(tags = "易博客管理员功能接口文档")
@ApiOperation("文章管理")
@RestController
@RequestMapping("/admin")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    private ArticleTypeMapper articleTypeMapper;

    /**
     * 管理员-文章管理-文章列表数据
     * @param articleQuery
     * @return
     */
    @PostMapping("/article_list")
    @ApiOperation("管理员-文章管理-文章列表数据")
    public ResponseResult articleList(@RequestBody ArticleQuery articleQuery){
        List<ArticleMainDataDTO> resultList = new ArrayList<>();
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("total",0);
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        if (StrUtil.isNotEmpty(articleQuery.getArticleTypeCode())){
            queryWrapper.eq(Article::getArticleTypeId,articleQuery.getArticleTypeCode());
        }
        if (StrUtil.isNotEmpty(articleQuery.getArticleTitle())){
            queryWrapper.like(Article::getArticleTitle,articleQuery.getArticleTitle());
        }
        List<Article> articleList = articleMapper.selectList(queryWrapper);
        List<String> articleTypeCodeList = new ArrayList<>();
        List<String> userIdList = new ArrayList<>();
        if (CollUtil.isNotEmpty(articleList)){
            articleTypeCodeList = articleList.stream().map(Article::getArticleTypeId).collect(Collectors.toList());
            userIdList = articleList.stream().map(Article::getUserId).collect(Collectors.toList());
        }
        List<ArticleType> articleTypeList = new ArrayList();
        if (CollUtil.isNotEmpty(articleTypeCodeList)) {
            LambdaQueryWrapper<ArticleType> articleTypeLambdaQueryWrapper  = new LambdaQueryWrapper<>();
            articleTypeLambdaQueryWrapper.in(ArticleType::getArticleTypeId,articleTypeCodeList);
            articleTypeList = articleTypeMapper.selectList(articleTypeLambdaQueryWrapper);
        }
        Map<String,String> articleTypeCodeTONameMap =new HashMap<>();
        if (CollUtil.isNotEmpty(articleTypeList)){
            articleTypeCodeTONameMap =  articleTypeList.stream().collect(Collectors.toMap(ArticleType::getArticleTypeId,ArticleType::getArticleTypeName));
        }
        List<SysUser> sysUsers = new ArrayList<>();
        if (CollUtil.isNotEmpty(userIdList)) {
            LambdaQueryWrapper<SysUser> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
            userLambdaQueryWrapper.in(SysUser::getSysUserId, userIdList);
             sysUsers = userMapper.selectList(userLambdaQueryWrapper);
        }
        Map<String,String> userIdToNameMap = new HashMap<>();
        if (CollUtil.isNotEmpty(sysUsers)){
            userIdToNameMap = sysUsers.stream().collect(Collectors.toMap(SysUser::getSysUserId,SysUser::getSysUserName));
        }
        if (CollUtil.isNotEmpty(articleList)){
            Map<String, String> finalArticleTypeCodeTONameMap = articleTypeCodeTONameMap;
            Map<String, String> finalUserIdToNameMap = userIdToNameMap;
            articleList.forEach(article -> {
                ArticleMainDataDTO articleMainDataDTO = new ArticleMainDataDTO();
                BeanUtils.copyProperties(article,articleMainDataDTO);
                articleMainDataDTO.setArticleTypeName(finalArticleTypeCodeTONameMap.get(article.getArticleTypeId()));
                articleMainDataDTO.setUserName(finalUserIdToNameMap.get(article.getUserId()));
                resultList.add(articleMainDataDTO);
            });
            resultMap.put("total",resultList.size());
            resultMap.put( "records",resultList.stream().skip((articleQuery.getPageIndex() -1) * articleQuery.getPageSize())
                    .limit(articleQuery.getPageSize())
                    .collect(Collectors.toList()));
        }
        return ResponseResult.Success(resultMap);
    }

    /**
     * 管理员-文章管理-删除文章
     * @param articleId 文章ID
     * @return
     */
    @ApiOperation("管理员-文章管理-删除文章")
    @PostMapping("del_article")
    public ResponseResult delArticle(@RequestParam(value = "articleId",required = true) String articleId){
        articleMapper.deleteById(articleId);
        return ResponseResult.Success();
    }
}

