package cn.hunnu.recommender.examination.mapper;

import cn.hunnu.recommender.examination.entity.Questions;
import cn.hunnu.recommender.examination.vo.QuestionOptionsVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 试题表 Mapper 接口
 * </p>
 *
 * @author JinSheng
 * @since 2023-05-30
 */
@Mapper
public interface QuestionsMapper extends BaseMapper<Questions> {

    Page<Questions> queryQuestion(Page<Object> objectPage,@Param("bankId") Integer bankId);

}
