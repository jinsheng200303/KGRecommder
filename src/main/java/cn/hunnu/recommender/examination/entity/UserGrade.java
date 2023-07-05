package cn.hunnu.recommender.examination.entity;

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
 * 学生成绩表
 * </p>
 *
 * @author JinSheng
 * @since 2023-07-05
 */
@Getter
@Setter
@TableName("user_grade")
@ApiModel(value = "UserGrade对象", description = "学生成绩表")
public class UserGrade implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户ID；外键")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty("考试ID；外键")
    @TableField("exams_id")
    private Integer examsId;

    @ApiModelProperty("成绩")
    @TableField("grade")
    private String grade;

    @ApiModelProperty("用户成绩关联ID")
    @TableId(value = "user_grade_id", type = IdType.AUTO)
    private Integer userGradeId;


}
