package cn.hunnu.recommender.examination.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class OptionsQuery extends PageInfo{
    @ApiModelProperty("选项内容")
    private String optionsValue;
}
