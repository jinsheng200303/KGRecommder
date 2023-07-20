package cn.hunnu.recommender.examination.mapper;

import cn.hunnu.recommender.examination.entity.StudentPaper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author JinSheng
 * @since 2023-07-11
 */
@Mapper
public interface StudentPaperMapper extends BaseMapper<StudentPaper> {

    @Delete("delete from person_knowledge where user_id = #{userId} and knowledge_id = #{knowledgeId}")
    void deletePersonKnowledge(Integer userId,Integer knowledgeId,float comprehension);

    @Insert("insert into person_knowledge(user_id,knowledge_id,comprehension) values(#{userId},#{knowledgeId},#{comprehension})")
    void savePersonKnowledge(Integer userId,Integer knowledgeId,float comprehension);

}
