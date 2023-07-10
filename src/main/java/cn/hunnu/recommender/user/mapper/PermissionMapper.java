package cn.hunnu.recommender.user.mapper;

import cn.hunnu.recommender.examination.entity.Options;
import cn.hunnu.recommender.user.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 权限 Mapper 接口
 * </p>
 *
 * @author czj
 * @since 2023-05-19
 */
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

}
