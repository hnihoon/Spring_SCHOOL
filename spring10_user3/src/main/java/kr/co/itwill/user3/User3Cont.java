package kr.co.itwill.user3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class User3Cont {
	
	@Autowired
	User3ServiceImp uService;

	public User3Cont() {
		System.out.println("-----User3Cont()객체 생성됨");
	}
	
	//요청명령어 http://localhost:9095/test
	@GetMapping("/test")
	public String test() {
		return "Data 준비중";
	}
	
	
	@PutMapping("/user3/save")
	public ResultDTO save(@RequestBody User3DTO uDto) {
		return uService.save(uDto);
	}
	
	@PostMapping("/user3/list")
	public ResultDTO findAll() {
		return uService.findAll();
	}
}
