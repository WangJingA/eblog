package com.blog.gfblog.entity.dto.articleType;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

@ApiModel
@Data
@Accessors(chain = true)
public class AddArticleTypeDTO {
    /**
     * 文章类型名称
     */
    private String articleTypeName;
    /**
     * 文章类型排序
     */
    private String articleTypeOrder;
}
