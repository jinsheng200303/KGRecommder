package cn.hunnu.recommender.examination.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class examsBaseEntity implements Serializable {

    @ApiModelProperty("开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "start_time", fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty("结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "end_time", fill = FieldFill.UPDATE)
    private Date updateTime;

    @ApiModelProperty("状态 已封禁/已删除/正常")
    @TableField("status")
    private Integer status;
}