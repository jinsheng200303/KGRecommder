package cn.hunnu.recommender.examination.vo;

import cn.hunnu.recommender.user.dto.PageInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class OptionVO extends PageInfo {

    @ApiModelProperty("选项ID号")
    private Integer optionId;

    @ApiModelProperty("选项标号")
    private String optionLabel;

    @ApiModelProperty("选项内容")
    private String optionValue;
}
