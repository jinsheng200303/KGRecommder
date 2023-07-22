package cn.hunnu.recommender.user.entity;

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
 * 学习风格测量表
 * </p>
 *
 * @author czj
 * @since 2023-07-21
 */
@Getter
@Setter
@TableName("study_style_paper")
@ApiModel(value = "StudyStylePaper对象", description = "学习风格测量表")
public class StudyStylePaper extends userBaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("题目ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("题干")
    @TableField("question_name")
    private String questionName;

    @ApiModelProperty("选项一")
    @TableField("option1")
    private String option1;

    @ApiModelProperty("选项二")
    @TableField("option2")
    private String option2;


}
