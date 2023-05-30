package cn.hunnu.recommender.examination.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RecordDiagQuery extends PageInfo{
    @ApiModelProperty("做题记录与诊断结果关联ID")
    private Integer recordDiagId;
}
