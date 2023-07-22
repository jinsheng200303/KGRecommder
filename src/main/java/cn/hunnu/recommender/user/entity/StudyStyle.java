package cn.hunnu.recommender.user.entity;

import cn.hunnu.recommender.user.entity.userBaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 学习风格测量表
 * </p>
 *
 * @author czj
 * @since 2023-07-22
 */
@Getter
@Setter
@TableName("study_style")
@ApiModel(value = "StudyStyle对象", description = "")
public class StudyStyle extends userBaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("学习风格ID")
    @TableId(value = "study_style_id", type = IdType.AUTO)
    private Integer studyStyleId;

    @ApiModelProperty("学习风格名称")
    @TableField("study_style_name")
    private String studyStyleName;

    @ApiModelProperty("学习风格对应的资源类型")
    @TableField("resource_type")
    private String resourceType;


}
