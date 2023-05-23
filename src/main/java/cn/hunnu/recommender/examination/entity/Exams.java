package cn.hunnu.recommender.examination.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 考试表
 * </p>
 *
 * @author JinSheng
 * @since 2023-05-19
 */
@Getter
@Setter
@TableName("exams")
@ApiModel(value = "Exams对象", description = "考试表")
public class Exams extends examsBaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("考试ID")
    @TableId(value = "exam_id", type = IdType.AUTO)
    private Integer examId;

    //数据为""和空格和null情况下会被拦截
    @NotBlank(message = "考试标题不能为空")
    @Length(min = 3, max = 50, message = "考试标题长度需要在【3-50】之间")
    @ApiModelProperty("考试标题")
    @TableField("exam_title")
    private String examTitle;

    @ApiModelProperty("考试持续时间")
    @TableField("time_duration")
    private LocalDateTime timeDuration;

    @Range(min = 0,max = 100, message = "考试最终得分需要在【0-100】之间")
    @ApiModelProperty("最终得分")
    @TableField("total_marks")
    private Integer totalMarks;

}
