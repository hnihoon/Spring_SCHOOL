package kr.co.itwill;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//클라이언트에서 접속할 수 있도록 보안 설정
//8080포트에서 온 host는 허용해준다.
public class WebConfig implements WebMvcConfigurer{
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {

		registry.addMapping("/**").allowedOrigins("http://localhost:8080");
	}
}
