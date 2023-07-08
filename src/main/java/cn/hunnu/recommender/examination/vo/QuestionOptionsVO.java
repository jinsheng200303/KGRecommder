package cn.hunnu.recommender.examination.vo;

import cn.hunnu.recommender.examination.entity.Options;
import cn.hunnu.recommender.user.dto.PageInfo;
import cn.hutool.json.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@Accessors(chain = true)
@TableName(autoResultMap = true)
public class QuestionOptionsVO extends PageInfo {

    @ApiModelProperty("题库ID号")
    private Integer bankId;

    @ApiModelProperty("试题ID号")
    private Integer questionId;

    @ApiModelProperty("试题类型;选择题或者判断题")
    private String questionType;

    @ApiModelProperty("试题知识点分类")
    private Integer knowledgeId;

    @ApiModelProperty("试题描述")
    private String questionStatement;

    @ApiModelProperty("试题答案")
    private String answer;

    @ApiModelProperty("试题难度")
    private String difficultyLevel;

    @ApiModelProperty("试题得分")
    private String score;

    @ApiModelProperty("选项列表")
    private List<OptionVO> optionVOS;

}
