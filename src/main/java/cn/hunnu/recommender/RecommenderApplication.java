package cn.hunnu.recommender;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class RecommenderApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecommenderApplication.class, args);
		log.info("应用启动完成！");

	}

}
