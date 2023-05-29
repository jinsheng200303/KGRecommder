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
 * 课堂公告
 * </p>
 *
 * @author czj
 * @since 2023-05-29
 */
@Getter
@Setter
@TableName("class_announcement")
@ApiModel(value = "ClassAnnouncement对象", description = "课堂公告")
public class ClassAnnouncement {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("课程ID")
    @TableField("class_id")
    private Integer classId;

    @ApiModelProperty("公告ID")
    @TableField("announcement_id")
    private Integer announcementId;

    @ApiModelProperty("课堂公告关联ID")
    @TableId(value = "class_announcement_id", type = IdType.AUTO)
    private Integer classAnnouncementId;


}
