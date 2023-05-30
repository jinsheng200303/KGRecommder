package cn.hunnu.recommender.examination.entity;

import cn.hunnu.recommender.examination.entity.examinationBaseEntity;
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
 * 选项表
 * </p>
 *
 * @author JinSheng
 * @since 2023-05-30
 */
@Getter
@Setter
@TableName("options")
@ApiModel(value = "Options对象", description = "选项表")
public class Options extends examinationBaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("选项ID号")
    @TableId("option_id")
    private Integer optionId;

    @ApiModelProperty("试题ID号;外键")
    @TableField("question_id")
    private Integer questionId;

    @ApiModelProperty("选项标号")
    @TableField("option_label")
    private String optionLabel;

    @ApiModelProperty("选项内容")
    @TableField("option_value")
    private String optionValue;


}
