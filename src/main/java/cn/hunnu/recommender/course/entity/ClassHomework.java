package cn.hunnu.recommender.course.entity;

import cn.hunnu.recommender.course.entity.CourseBaseEntity;
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
 * 课堂作业
 * </p>
 *
 * @author czj
 * @since 2023-05-29
 */
@Getter
@Setter
@TableName("class_homework")
@ApiModel(value = "ClassHomework对象", description = "课堂作业")
public class ClassHomework {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("课程ID")
    @TableField("class_id")
    private Integer classId;

    @ApiModelProperty("作业ID")
    @TableField("homework_id")
    private Integer homeworkId;

    @ApiModelProperty("课堂作业关联ID")
    @TableId(value = "class_homework_id", type = IdType.AUTO)
    private Integer classHomeworkId;


}
