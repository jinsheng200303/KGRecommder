package cn.hunnu.recommender.examination.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RecordsQuery extends PageInfo{
    @ApiModelProperty("记录答案")
    private String recordAnswer;
}
