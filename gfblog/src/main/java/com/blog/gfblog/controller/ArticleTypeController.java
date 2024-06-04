package com.blog.gfblog.controller;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.blog.gfblog.common.ResponseResult;
import com.blog.gfblog.entity.dto.articleType.AddArticleTypeDTO;
import com.blog.gfblog.entity.dto.articleType.ModifyArticleTypeDTO;
import com.blog.gfblog.entity.dto.minio.ArticletTypeDTO;
import com.blog.gfblog.entity.query.ArticleTypeQuery;
import com.blog.gfblog.mapper.ArticleMapper;
import com.blog.gfblog.mapper.ArticleTypeMapper;
import com.blog.gfblog.pojo.Article;
import com.blog.gfblog.pojo.ArticleType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 文章类型表 前端控制器
 * </p>
 *
 * @author husir
 * @since 2024-05-26
 */
@RestController
@Api(tags = "易博客管理员功能接口文档")
@ApiOperation("文章类型管理")
@RequestMapping("/admin")
public class ArticleTypeController {
    @Autowired
    ArticleTypeMapper articleTypeMapper;
    @Autowired
    ArticleMapper articleMapper;
    /**
     * 文章类型列表
     * @param typeQuery
     * @return
     */
    @ApiOperation(value = "管理员-文章类型管理-文章类型列表数据")
    @PostMapping(value = "/article_type_list")
    public ResponseResult articleTypeList(@RequestBody ArticleTypeQuery typeQuery){
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("total",0);
        // 查询出所有的文章类型
        LambdaQueryWrapper<ArticleType> queryWrapper = new LambdaQueryWrapper<>();
        if (StrUtil.isNotBlank(typeQuery.getArticleTypeCode())){
            queryWrapper.eq(ArticleType::getArticleTypeId,typeQuery.getArticleTypeCode());
        }
        if (StrUtil.isNotEmpty(typeQuery.getArticleTypeName())){
            queryWrapper.like(ArticleType::getArticleTypeName,typeQuery.getArticleTypeName());
        }
        List<ArticleType> articleTypes = articleTypeMapper.selectList(queryWrapper);
        // 查询出文章
        List<Article> articleList = articleMapper.selectList(new LambdaQueryWrapper<>());
        // 将文章和文章类型进行关联
        // 先对文章进行文章类型分组
        List<ArticletTypeDTO> resultArtilceTypeList = new ArrayList<>();
            if (CollUtil.isNotEmpty(articleTypes)){
                resultMap.put("total",articleTypes.size());
                if (CollUtil.isNotEmpty(articleList)){
                    Map<String, List<Article>> articleTypeMap = articleList.stream()
                            .collect(Collectors.groupingBy(Article::getArticleTypeId));
                        articleTypes.forEach(item->{
                            ArticletTypeDTO articletTypeDTO = new ArticletTypeDTO();
                            BeanUtils.copyProperties(item,articletTypeDTO);
                            List<Article> articles = articleTypeMap.get(item.getArticleTypeId());
                            if (CollUtil.isNotEmpty(articles)){
                                articletTypeDTO.setArticleNumber(String.valueOf(articles.size()));
                            }
                            resultArtilceTypeList.add(articletTypeDTO);
                        });
            }
        }
        // 分页
        if (Objects.nonNull(typeQuery.getPageIndex()) && Objects.nonNull(typeQuery.getPageSize())){
            if (CollUtil.isNotEmpty(resultArtilceTypeList)){
                resultArtilceTypeList.stream().skip((typeQuery.getPageIndex() -1) * typeQuery.getPageSize())
                        .limit(typeQuery.getPageSize())
                        .collect(Collectors.toList());
            }
        }
        resultMap.put("articleTypeList",resultArtilceTypeList);
        return ResponseResult.Success(resultMap);
    }

    /**
     * 文章类型下拉列表
     * @return
     */
    @ApiOperation(value = "管理员-文章类型管理-文章类型下拉列表")
    @PostMapping(value = "/article_type_select")
    public ResponseResult articleTypeSelect(){
        return ResponseResult.Success(articleTypeMapper.selectList(new LambdaQueryWrapper<>()));
    }

    /**
     * 删除文章类型
     * @param articleTypeId 文章类型id
     * @return
     */
    @ApiOperation(value = "管理员-文章类型管理-删除文章类型")
    @ApiImplicitParam(value = "articleTypeId",type = "String",required = true)
    @PostMapping(value = "/del_article_type")
    public ResponseResult delArticleType(@RequestParam("article_type_id") String articleTypeId){
        articleTypeMapper.deleteById(articleTypeId);
        return ResponseResult.Success();
    }

    /**
     * 修改文章类型
     * @param modifyArticleTypeDTO
     * @return
     */
    @ApiOperation(value = "管理员-文章类型管理-修改文章类型")
    @PostMapping("/modify_article_type")
    public ResponseResult modifyArticleType(@RequestBody  @Valid ModifyArticleTypeDTO modifyArticleTypeDTO){
        LambdaUpdateWrapper<ArticleType> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(ArticleType::getArticleTypeId,modifyArticleTypeDTO.getArticleTypeId());
        ArticleType articleType = new ArticleType();
        articleType.setArticleTypeId(modifyArticleTypeDTO.getArticleTypeId());
        articleType.setArticleTypeName(modifyArticleTypeDTO.getArticleTypeName());
        articleType.setArticleTypeOrder(Integer.parseInt(modifyArticleTypeDTO.getArticleTypeOrder()));
        articleType.setArticleTypeImageUrl(modifyArticleTypeDTO.getArticleTypeImageUrl());
        articleType.setUpdateTime(DateUtil.toLocalDateTime(new Date()));
        articleTypeMapper.update(articleType,updateWrapper);
        return ResponseResult.Success();
    }


    /**
     * 新增文章类型
     * @param addArticleTypeDTO
     * @return
     */
    @ApiOperation(value = "管理员-文章类型管理-新增文章类型")
    @PostMapping("/add_article_type")
    public ResponseResult addArticleType(@RequestBody  @Valid AddArticleTypeDTO addArticleTypeDTO){
        ArticleType articleType = new ArticleType();
        articleType.setArticleTypeId(UUID.randomUUID().toString());
        articleType.setArticleTypeName(addArticleTypeDTO.getArticleTypeName());
        articleType.setArticleTypeImageUrl(articleType.getArticleTypeImageUrl());
        articleType.setArticleTypeOrder(Integer.parseInt(addArticleTypeDTO.getArticleTypeOrder()));
        articleType.setCreateTime(DateUtil.toLocalDateTime(new Date()));
        articleTypeMapper.insert(articleType);
        return ResponseResult.Success();
    }
}

