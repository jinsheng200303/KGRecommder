package cn.hunnu.recommender.examination.serviceImpl;

import cn.hunnu.recommender.examination.entity.Papers;
import cn.hunnu.recommender.examination.mapper.PapersMapper;
import cn.hunnu.recommender.examination.service.PapersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 试卷表 服务实现类
 * </p>
 *
 * @author JinSheng
 * @since 2023-05-30
 */
@Service
public class PapersServiceImpl extends ServiceImpl<PapersMapper, Papers> implements PapersService {

}
