package cn.hunnu.recommender;

import cn.hunnu.recommender.examination.entity.Exams;
import cn.hunnu.recommender.examination.mapper.ExamsMapper;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@MapperScan("cn.hunnu.recommender.user.dao.PermissionsTestMapper")
class RecommenderApplicationTests {

	@Test
<<<<<<< HEAD
	public void contextLoads() {
		List<Exams> exams = examsMapper.selectList(null);
		exams.forEach(System.out::println);
=======
	void contextLoads() {
>>>>>>> da356ba343ec037a1f0ffa013690fca8bb008025
	}


}
