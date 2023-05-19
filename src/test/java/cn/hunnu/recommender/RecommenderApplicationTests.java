package cn.hunnu.recommender;

import cn.hunnu.recommender.examination.entity.Exams;
import cn.hunnu.recommender.examination.mapper.ExamsMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class RecommenderApplicationTests {

	@Autowired
	private ExamsMapper examsMapper;

	@Test
	void contextLoads() {
		List<Exams> exams = examsMapper.selectList(null);
		exams.forEach(System.out::println);
	}

}
