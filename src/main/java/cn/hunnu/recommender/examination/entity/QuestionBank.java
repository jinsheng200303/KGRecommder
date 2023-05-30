package cn.hunnu.recommender.examination.entity;

import cn.hunnu.recommender.examination.entity.examinationBaseEntity;
import com.baomidou.mybatisplus.annotation.FieldFill;
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
 * 题库表
 * </p>
 *
 * @author JinSheng
 * @since 2023-05-30
 */
@Getter
@Setter
@TableName("question_bank")
@ApiModel(value = "QuestionBank对象", description = "题库表")
public class QuestionBank extends examinationBaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("题库ID号")
    @TableId(value = "bank_id", type = IdType.AUTO)
    private Integer bankId;

    @ApiModelProperty("题库名称")
    @TableField("bank_name")
    private String bankName;

    @ApiModelProperty("题库描述")
    @TableField("bank_desc")
    private String bankDesc;

    @ApiModelProperty("题库创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("题库修改时间")
    @TableField("modify_time")
    private LocalDateTime modifyTime;


}
