package portfolio.beom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BeomApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeomApplication.class, args);
	}

	//TODO 멤버와 게시물 연관관계 맺고(ex 다대일, 일대다, 양방향, 단방향),
	// 게시글 조회 API 개발 -> 단건조회, 전체조회, 조건조회(게시글 작성 기준순(오름차순, 내림차순), 게시글 제목 ... )

}
