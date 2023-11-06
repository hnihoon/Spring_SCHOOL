package kr.co.itwill.bbs;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class BbsCont {

	public BbsCont() {
		System.out.println("-----BbsCont() 객체 생성됨");
	}

	  //방법1) 
	 /* //결과확인 : http://localhost:9095/bbs/create.do GET방식으로 호출
	 * @RequestMapping(value ="/bbs/create.do", method=RequestMethod.GET) 
	 * public ModelAndView bbsForm() { 
	 * ModelAndView mav = new ModelAndView();
	 * mav.setViewName("bbs/bbsForm"); //WEB-INF/views/bbs/bbsForm.jsp 
	 * return mav; 
	 * }
	 */
	
	//방법2)
	/*
	 * @GetMapping("/bbs/create.do")
	 * public ModelAndView bbsForm() { 
	 * ModelAndView mav = new ModelAndView(); 
	 * mav.setViewName("bbs/bbsForm"); 
	 * return mav; 
	 * }
	 */
	
	//방법3)

	  @GetMapping("/bbs/create.do") 
	  public String bbsForm() { 
	  return"bbs/bbsForm";
	 }
	
	
	//1)
	//동일한 요청명령어 GET | POST 방식으로 구분해서 호출 가능하다
	//@RequestMapping(value="/bbs/create.do", method=RequestMethod.POST)
	/*
	 * @PostMapping("/bbs/create.do") //RequestMapping도 사용가능 public ModelAndView
	 * bbsIns(HttpServletRequest req) { //사용자가 입력 요청한 정보 가져오기 String wname =
	 * req.getParameter("wname").trim(); String subject =
	 * req.getParameter("subject").trim(); String content =
	 * req.getParameter("content").trim(); String passwd =
	 * req.getParameter("passwd").trim();
	 * 
	 * ModelAndView mav = new ModelAndView(); 
	 * mav.setViewName("bbs/bbsResult");
	 * mav.addObject("wname", wname); 
	 * mav.addObject("subject", subject);
	 * mav.addObject("content", content); 
	 * mav.addObject("passwd", passwd);
	 * 
	 * return mav; 
	 * }
	 */
	
	//2)매개변수(parameter)가 DTO 객체인 경우
	//->해당클래스(BbsDTO)에 반드시 폼 컨트롤 요소이름으로 되어 있는 멤버변수와 각 getter와 setter함수가 있어야 한다.
	//->예를 들어 <input type=text name=wname>와 private String wname의 이름이 동일해야 한다.
	@PostMapping("/bbs/create.do")
	public ModelAndView bbsIns(@ModelAttribute BbsDTO dto) { //@ModelAttribute 생략 가능
		ModelAndView mav = new ModelAndView();
		mav.setViewName("bbs/bbsResult2");
		mav.addObject("dto", dto);
		return mav;
	}
}





