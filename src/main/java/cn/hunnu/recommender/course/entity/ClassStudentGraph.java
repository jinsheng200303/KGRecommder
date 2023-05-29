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
 * 课堂学生图谱
 * </p>
 *
 * @author czj
 * @since 2023-05-29
 */
@Getter
@Setter
@TableName("class_student_graph")
@ApiModel(value = "ClassStudentGraph对象", description = "课堂学生图谱")
public class ClassStudentGraph {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("课堂ID")
    @TableField("class_id")
    private Integer classId;

    @ApiModelProperty("学生用户ID")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty("图谱ID")
    @TableField("graph_id")
    private Integer graphId;

    @ApiModelProperty("课堂学生图谱关联ID")
    @TableId(value = "class_student_graph_id", type = IdType.AUTO)
    private Integer classStudentGraphId;


}
