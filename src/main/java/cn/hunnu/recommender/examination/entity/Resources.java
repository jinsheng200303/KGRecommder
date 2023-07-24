package cn.hunnu.recommender.examination.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 学习资源表
 * </p>
 *
 * @author JinSheng
 * @since 2023-05-30
 */
@Getter
@Setter
@TableName("resources")
@ApiModel(value = "Resources对象", description = "学习资源表")
public class Resources extends ExaminationBaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("资源ID")
    @TableId(value = "resource_id", type = IdType.AUTO)
    private Integer resourceId;

    @ApiModelProperty("资源名称")
    @TableField("resource_name")
    private String resourceName;

    @ApiModelProperty("资源类型")
    @TableField("resource_type")
    private String resourceType;

    @ApiModelProperty("资源链接")
    @TableField("resource_link")
    private String resourceLink;

    @ApiModelProperty("资源对应知识点ID")
    @TableField("knowledge_id")
    private String knowledgeId;
}
