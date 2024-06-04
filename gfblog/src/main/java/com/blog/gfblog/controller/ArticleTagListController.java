package com.blog.gfblog.controller;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blog.gfblog.common.ResponseResult;
import com.blog.gfblog.entity.dto.tags.AddTagDTO;
import com.blog.gfblog.entity.dto.tags.UpdateTagDTO;
import com.blog.gfblog.entity.query.ArticleTagQuery;
import com.blog.gfblog.mapper.ArticleTagMapper;
import com.blog.gfblog.pojo.ArticleTag;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 文章标签文章列表表 前端控制器
 * </p>
 *
 * @author husir
 * @since 2024-05-26
 */
@Api(tags = "易博客管理员功能接口文档")
@ApiOperation("文章标签管理")
@RestController
@RequestMapping("/admin")
public class ArticleTagListController {
    @Autowired
    ArticleTagMapper articleTagMapper;

    /**
     * 标签列表数据
     * @return
     */
    @ApiOperation(value = "管理员-标签管理-标签列表")
    @PostMapping(value = "/article_tag_list")
    public ResponseResult listArticleTag(@RequestBody ArticleTagQuery articleTagQuery){
        LambdaQueryWrapper<ArticleTag> queryWrapper = new LambdaQueryWrapper<>();
        if (StrUtil.isNotEmpty(articleTagQuery.getTagName())){
            queryWrapper.like(ArticleTag::getArticleTagName,articleTagQuery.getTagName());
        }
        IPage<ArticleTag> page  = new Page(articleTagQuery.getPageIndex(),articleTagQuery.getPageSize());
        return ResponseResult.Success(articleTagMapper.selectPage(page,queryWrapper));
    }

    /**
     * 删除文章标签
     * @param tagId 标签id
     * @return
     */
    @ApiOperation(value = "管理员-标签管理-删除文章标签")
    @PostMapping(value = "/del_article_tag")
    public ResponseResult delArticleTag(@RequestParam(value = "articleTagId",required = true) String tagId){
        articleTagMapper.deleteById(tagId);
        return ResponseResult.Success();
    }

    /**
     * 新增文章标签
     * @param addTagDTO 参数
     * @return
     */
    @ApiOperation(value = "管理员-标签管理-新增文章标签")
    @PostMapping(value = "/add_tag")
    public ResponseResult addTags(@RequestBody @Validated AddTagDTO addTagDTO){
        judgeSameTagExist(addTagDTO.getArticleTagName());
        ArticleTag articleTag = new ArticleTag();
        articleTag.setArticleTagId(UUID.randomUUID().toString());
        articleTag.setArticleTagName(addTagDTO.getArticleTagName());
        articleTag.setArticleAddTagTime(DateUtil.toLocalDateTime(new Date()));
        articleTagMapper.insert(articleTag);
        return ResponseResult.Success();
    }

    /**
     * 修改文章标签
     * @param updateTagDTO 参数
     * @return
     */
    @ApiOperation(value = "管理员-标签管理-新增文章标签")
    @PostMapping(value = "/update_tag")
    public ResponseResult updateTags(@RequestBody @Validated UpdateTagDTO updateTagDTO){
        judgeSameTagExist(updateTagDTO.getArticleTagName());
        ArticleTag articleTag = new ArticleTag();
        LambdaUpdateWrapper<ArticleTag> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(ArticleTag::getArticleTagId,updateTagDTO.getArticleTagId())
                .set(ArticleTag::getArticleUpdateTime,DateUtil.toLocalDateTime(new Date()))
                .set(ArticleTag::getArticleTagName,updateTagDTO.getArticleTagName());
        articleTagMapper.update(null,updateWrapper);
        return ResponseResult.Success();
    }

    /**
     * 判断标签是否存在
     * @param tagName
     */
    private void judgeSameTagExist(String tagName){
        LambdaQueryWrapper<ArticleTag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ArticleTag::getArticleTagName,tagName);
        List<ArticleTag> articleTags = articleTagMapper.selectList(queryWrapper);
        if (CollUtil.isNotEmpty(articleTags) && articleTags.size() > 0){
            throw new IllegalArgumentException("标签已存在，请重新输入!");
        }
    }
}

