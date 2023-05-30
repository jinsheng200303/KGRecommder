package cn.hunnu.recommender.examination.serviceImpl;

import cn.hunnu.recommender.examination.entity.Resources;
import cn.hunnu.recommender.examination.mapper.ResourcesMapper;
import cn.hunnu.recommender.examination.service.ResourcesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 学习资源表 服务实现类
 * </p>
 *
 * @author JinSheng
 * @since 2023-05-30
 */
@Service
public class ResourcesServiceImpl extends ServiceImpl<ResourcesMapper, Resources> implements ResourcesService {

}
