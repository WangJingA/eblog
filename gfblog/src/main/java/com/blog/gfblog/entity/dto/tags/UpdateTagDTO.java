package com.blog.gfblog.entity.dto.tags;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * 修改标签入参实体类
 * @author husir
 * @date 2024/05/26
 */
@ApiModel
@Data
@Accessors(chain = true)
public class UpdateTagDTO {
    /**
     * 文章标签id
     */
    @NotBlank(message = "标签ID不能为空")
    private String articleTagId;
    /**
     * 标签名称
     */
    @NotBlank(message = "标签名称不能为空")
    private String articleTagName;
}
