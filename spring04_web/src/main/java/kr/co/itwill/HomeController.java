package kr.co.itwill;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

//URL에서 요청, 응답이 가능한 클래스 지정
@Controller  //->스프링 컨테이너(톰캣)가 자동으로 객체 생성됨
public class HomeController {

	public HomeController() {
		System.out.println("-----HomeController()객체 생성됨");
	}

	//요청 명령어 등록하고, 실행의 주체는 메소드 (함수)
	
	//결과확인 http://localhost:9095/home.do
	@RequestMapping("/home.do")
	public ModelAndView home() {
		ModelAndView mav = new ModelAndView();
		
		//application.properties 환경설정 참조
		//  /WER-INF/views/start.jsp
		mav.setViewName("start");
		
		//뷰패이지(start.jsp)와 값을 공유하기 위해 request전역변수 활용하기
		//Request.setAttribute()와 동일한 기능의 함수
		mav.addObject("message", "어서오세요!!");
		mav.addObject("img", "<img src='images/mouse.png'>");
		
		return mav;
	}
	
}
