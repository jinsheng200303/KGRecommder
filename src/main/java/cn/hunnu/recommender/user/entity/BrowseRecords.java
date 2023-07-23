package cn.hunnu.recommender.user.entity;

import cn.hunnu.recommender.user.entity.userBaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 浏览记录表
 * </p>
 *
 * @author czj
 * @since 2023-07-23
 */
@Getter
@Setter
@TableName("browse_records")
@ApiModel(value = "BrowseRecords对象", description = "浏览记录表")
public class BrowseRecords extends userBaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("浏览记录ID")
    @TableId(value = "browse_record_id", type = IdType.AUTO)
    private Integer browseRecordId;

    @ApiModelProperty("用户ID")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty("资源ID")
    @TableField("resource_id")
    private Integer resourceId;

    @ApiModelProperty("浏览时间（最近一次）")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField("start_time")
    private Date startTime;


}
