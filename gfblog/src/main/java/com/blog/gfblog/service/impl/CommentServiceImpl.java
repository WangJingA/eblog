package com.blog.gfblog.service.impl;

import com.blog.gfblog.pojo.Comment;
import com.blog.gfblog.mapper.CommentMapper;
import com.blog.gfblog.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论表 服务实现类
 * </p>
 *
 * @author husir
 * @since 2024-06-04
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
