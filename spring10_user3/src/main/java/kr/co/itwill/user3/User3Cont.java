package kr.co.itwill.user3;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class User3Cont {

	public User3Cont() {
		System.out.println("-----User3Cont()객체 생성됨");
	}
	
	//요청명령어 http://localhost:9095/test
	@GetMapping("/test")
	public String test() {
		return "Data 준비중";
	}
}
