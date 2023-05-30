package cn.hunnu.recommender.examination.entity;

import cn.hunnu.recommender.enums.StatusType;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ExaminationBaseEntity implements Serializable {

    @ApiModelProperty("状态 已封禁/已删除/正常")
    @TableField("status")
    private StatusType status;
}