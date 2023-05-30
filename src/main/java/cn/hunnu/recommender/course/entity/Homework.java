package cn.hunnu.recommender.course.entity;

import cn.hunnu.recommender.course.entity.CourseBaseEntity;
import cn.hunnu.recommender.enums.StatusType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 作业
 * </p>
 *
 * @author JinSheng
 * @since 2023-05-30
 */
@Getter
@Setter
@TableName("homework")
@ApiModel(value = "Homework对象", description = "作业")
public class Homework extends CourseBaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("作业ID")
    @TableId(value = "homework_id", type = IdType.AUTO)
    private Integer homeworkId;

    @ApiModelProperty("标题")
    @TableField("title")
    private String title;

    @ApiModelProperty("作业内容")
    @TableField("homework_content")
    private String homeworkContent;

    @ApiModelProperty("截止时间")
    @TableField("end_time")
    private LocalDateTime endTime;

    @ApiModelProperty("状态")
    @TableField("status")
    private StatusType status;


}
