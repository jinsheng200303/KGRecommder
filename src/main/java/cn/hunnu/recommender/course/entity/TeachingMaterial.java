package cn.hunnu.recommender.course.entity;

import cn.hunnu.recommender.course.entity.CourseBaseEntity;
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
 * 教学资料
 * </p>
 *
 * @author czj
 * @since 2023-05-30
 */
@Getter
@Setter
@TableName("teaching_material")
@ApiModel(value = "TeachingMaterial对象", description = "教学资料")
public class TeachingMaterial extends CourseBaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("资源ID")
    @TableId(value = "material_id", type = IdType.AUTO)
    private Integer materialId;

    @ApiModelProperty("标题")
    @TableField("title")
    private String title;

    @ApiModelProperty("资源链接")
    @TableField("material_link")
    private String materialLink;


}
