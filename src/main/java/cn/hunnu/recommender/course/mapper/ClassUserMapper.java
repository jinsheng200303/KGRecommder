package cn.hunnu.recommender.course.mapper;

import cn.hunnu.recommender.course.entity.ClassUser;
import cn.hunnu.recommender.course.vo.ClassVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 课堂用户 Mapper 接口
 * </p>
 *
 * @author czj
 * @since 2023-05-30
 */
@Mapper
public interface ClassUserMapper extends BaseMapper<ClassUser> {
    @Select("select class_user.user_id,class_id from class_user,person_role where class_user.user_id=person_role.user_id and class_id=#{classId} and role_id=#{roleId}")
    public List<ClassVO> classStudentQuery(int classId,int roleId);
}
