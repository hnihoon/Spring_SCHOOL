package kr.co.itwill.calc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CalcController {

	public CalcController() {
		System.out.println("-----CalcController() 객체 생성됨");
	}

	//URL에서 요청한 명령어를 등록
	//@RequestMapping(value="", method = GET | POST
	
	//결과확인 http://localhost:9095/add.do?no1=3&no2=5
	// /add.do 요청명령어를 get방식으로 요청하면
	@RequestMapping(value = "/add.do", method = RequestMethod.GET)
	public ModelAndView add(HttpServletRequest req) {
		
		//사용자가 요청한 값 가져오기
		int no1 = Integer.parseInt(req.getParameter("no1"));
		int no2 = Integer.parseInt(req.getParameter("no2"));
		int result = no1+no2;
		
		//View페이지로 이동하기 위한 클래스
		ModelAndView mav = new ModelAndView();
		//application.properties환경설정의 prefix와 suffix참조
		// /WEB-INF/views/calcResult.jsp 뷰페이지로 이동
		mav.setViewName("calcResult");
		
		//전역변수를 활용하여 공유장소에 값 올리기
		req.setAttribute("no1", no1);
		req.setAttribute("no2", no2);
		req.setAttribute("result", result);
		req.setAttribute("message", "<h3>두수 사이의 합</h3>");
		req.setAttribute("img", "<img src='images/add.png'>");
		
		return mav;
	}
	
	//결과확인 http://localhost:9095/sub.do?no1=3&no2=5
	//@GetMapping(value = "/sub.do") 아래 명령어와 동일
	@RequestMapping(value = "/sub.do", method = RequestMethod.GET)
	public ModelAndView sub(HttpServletRequest req) {
		
		//사용자가 요청한 값 가져오기
		int no1 = Integer.parseInt(req.getParameter("no1"));
		int no2 = Integer.parseInt(req.getParameter("no2"));
		int result = no1-no2;
		
		//View페이지로 이동하기 위한 클래스
		ModelAndView mav = new ModelAndView();
		mav.setViewName("calcResult");
		
		mav.addObject("no1", no1);
		mav.addObject("no2", no2);
		mav.addObject("result", result);
		mav.addObject("message", "<h3>두수를 뺀 결과</h3>");
		mav.addObject("img", "<img src='images/sub.png'>");
		
		return mav;
		
	}
	
	//결과확인 http://localhost:9095/dbs.do?no1=3&no2=5
	@RequestMapping(value = "/dbs.do", method = RequestMethod.GET)
	public ModelAndView dbs(HttpServletRequest req) {
		
		//사용자가 요청한 값 가져오기
		double no1 = Double.parseDouble(req.getParameter("no1"));
		double no2 = Double.parseDouble(req.getParameter("no2"));
		double result = no1/no2;
		
		//View페이지로 이동하기 위한 클래스
		ModelAndView mav = new ModelAndView();
		mav.setViewName("calcResult");
		
		mav.addObject("no1", no1);
		mav.addObject("no2", no2);
		mav.addObject("result", result);
		mav.addObject("message", "<h3>두수를 나눈 결과</h3>");
		mav.addObject("img", "<img src='images/search.png'>");
		
		return mav;
		
	}
	
	//결과확인 http://localhost:9095/gop.do?no1=3&no2=5
	@GetMapping("/gop.do")
	public ModelAndView gop(HttpServletRequest req) {
		
		//사용자가 요청한 값 가져오기
		int no1 = Integer.parseInt(req.getParameter("no1"));
		int no2 = Integer.parseInt(req.getParameter("no2"));
		int result = no1*no2;
		
		//View페이지로 이동하기 위한 클래스
		ModelAndView mav = new ModelAndView();
		mav.setViewName("calcResult");
		
		mav.addObject("no1", no1);
		mav.addObject("no2", no2);
		mav.addObject("result", result);
		mav.addObject("message", "<h3>두수를 곱한 결과</h3>");
		mav.addObject("img", "<img src='images/mul.png'>");
		
		return mav;
		
	}
	
}
