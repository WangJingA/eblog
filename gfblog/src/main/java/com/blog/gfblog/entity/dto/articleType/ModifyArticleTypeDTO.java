package com.blog.gfblog.entity.dto.articleType;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

@ApiModel
@Data
@Accessors(chain = true)
public class ModifyArticleTypeDTO {
    /**
     * 文章类型id
     */
    @NotBlank(message = "文章类型ID不能为空")
    private String articleTypeId;
    /**
     * 文章类型名称
     */
    private String articleTypeName;
    /**
     * 文章类型排序
     */
    private String articleTypeOrder;
    /**
     * 文章类型图片
     */
    private String articleTypeImageUrl;
}
