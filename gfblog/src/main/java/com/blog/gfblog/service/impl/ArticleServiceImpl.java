package com.blog.gfblog.service.impl;

import com.blog.gfblog.pojo.Article;
import com.blog.gfblog.mapper.ArticleMapper;
import com.blog.gfblog.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章表 服务实现类
 * </p>
 *
 * @author husir
 * @since 2024-06-04
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

}
