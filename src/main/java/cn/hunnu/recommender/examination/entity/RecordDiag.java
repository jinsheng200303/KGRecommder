package cn.hunnu.recommender.examination.entity;

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
 * 记录与诊断关联表
 * </p>
 *
 * @author JinSheng
 * @since 2023-05-30
 */
@Getter
@Setter
@TableName("record_diag")
@ApiModel(value = "RecordDiag对象", description = "记录与诊断关联表")
public class RecordDiag implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("记录与诊断关联ID")
    @TableId(value = "record_diag_id", type = IdType.AUTO)
    private Integer recordDiagId;

    @ApiModelProperty("记录ID")
    @TableField("record_id")
    private Integer recordId;

    @ApiModelProperty("结果ID")
    @TableField("result_id")
    private Integer resultId;


}
