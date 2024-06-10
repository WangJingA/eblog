package com.blog.gfblog.service.impl;

import com.blog.gfblog.pojo.Ad;
import com.blog.gfblog.mapper.AdMapper;
import com.blog.gfblog.service.AdService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 广告表 服务实现类
 * </p>
 *
 * @author husir
 * @since 2024-06-04
 */
@Service
public class AdServiceImpl extends ServiceImpl<AdMapper, Ad> implements AdService {

}
