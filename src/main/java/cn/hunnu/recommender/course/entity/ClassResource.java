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
 * 课堂资源
 * </p>
 *
 * @author czj
 * @since 2023-05-29
 */
@Getter
@Setter
@TableName("class_resource")
@ApiModel(value = "ClassResource对象", description = "课堂资源")
public class ClassResource {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("课堂ID")
    @TableField("class_id")
    private Integer classId;

    @ApiModelProperty("课堂资源关联ID")
    @TableId(value = "class_material_id", type = IdType.AUTO)
    private Integer classMaterialId;

    @ApiModelProperty("资源ID")
    @TableField("material_id")
    private Integer materialId;


}
