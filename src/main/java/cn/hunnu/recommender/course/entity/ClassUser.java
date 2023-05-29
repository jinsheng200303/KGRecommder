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
 * 课堂用户
 * </p>
 *
 * @author czj
 * @since 2023-05-30
 */
@Getter
@Setter
@TableName("class_user")
@ApiModel(value = "ClassUser对象", description = "课堂用户")
public class ClassUser {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("课程ID")
    @TableField("class_id")
    private Integer classId;

    @ApiModelProperty("用户ID")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty("课堂用户关联ID")
    @TableId(value = "class_user_id", type = IdType.AUTO)
    private Integer classUserId;


}
