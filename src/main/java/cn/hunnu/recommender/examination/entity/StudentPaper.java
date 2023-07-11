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
 * 
 * </p>
 *
 * @author JinSheng
 * @since 2023-07-11
 */
@Getter
@Setter
@TableName("student_paper")
@ApiModel(value = "StudentPaper对象", description = "")
public class StudentPaper implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("学生考试答题主键ID")
    @TableId(value = "student_paper_id", type = IdType.AUTO)
    private Integer studentPaperId;

    @ApiModelProperty("考试ID")
    @TableField("exam_id")
    private Integer examId;

    @ApiModelProperty("试卷内容(使用JSON数据格式保存)")
    @TableField("paper")
    private String paper;

    @ApiModelProperty("学生ID")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty("提交时间")
    @TableField("time")
    private String time;

    @ApiModelProperty("得分")
    @TableField("score")
    private Integer score;


}
