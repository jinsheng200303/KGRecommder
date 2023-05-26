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
 * 公告
 * </p>
 *
 * @author czj
 * @since 2023-05-26
 */
@Getter
@Setter
@TableName("announcement")
@ApiModel(value = "Announcement对象", description = "公告")
public class Announcement extends CourseBaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("公告ID")
    @TableId(value = "announcement_id", type = IdType.AUTO)
    private Integer announcementId;

    @ApiModelProperty("标题")
    @TableField("title")
    private String title;

    @ApiModelProperty("公告内容")
    @TableField("announcement_content")
    private String announcementContent;


}
