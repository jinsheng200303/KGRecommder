package cn.hunnu.recommender.user.mapper;

import cn.hunnu.recommender.user.entity.Validation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 邮箱验证表 Mapper 接口
 * </p>
 *
 * @author czj
 * @since 2023-06-27
 */
@Mapper
public interface ValidationMapper extends BaseMapper<Validation> {

}
