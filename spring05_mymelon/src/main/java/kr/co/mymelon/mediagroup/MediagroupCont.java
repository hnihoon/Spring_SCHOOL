package kr.co.mymelon.mediagroup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;

@Controller
public class MediagroupCont {
	
	
	//private MediagroupDAO dao = new MediagroupDAO();
	//@Repository에 의해서 이미 객체가 생성되어 있으므로 new 하지 않아도 된다.
	
	//@Autowired 스프링컨테이너(톰캣)가 생성해준 객체를 연결
	@Autowired
	private MediagroupDAO dao;
	
	public MediagroupCont() {
		System.out.println("-----MediagroupCont() 객체 생성됨");
	}
	
	//http://localhost:9095/mediagroup/list.do
	@RequestMapping("mediagroup/list.do")
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mediagroup/list");
		
		List<MediagroupDTO> list = dao.list();
		int totalRowCount=dao.totalRowCount(); //총 글개수
		
		mav.addObject("list", list);
		mav.addObject("count", totalRowCount);
		
		return mav;
	}
	
	//미디어 그룹 쓰기 페이지 호출
	@GetMapping("mediagroup/create.do")
	public String createForm() {
		return "mediagroup/createForm"; // /WEB-INF/views/mediagroup/createForm.jsp
	}
	
	@PostMapping("mediagroup/create.do")
	public ModelAndView createProc(MediagroupDTO dto) {
		ModelAndView mav = new ModelAndView();
		
		int cnt = dao.create(dto);
		if(cnt==0) {
			mav.setViewName("mediagroup/msgView");
			
			String msg1 = "<p>미디어 그룹 등록 실패</p>";
			String img = "<img src='../images/fail.png'>";
			String link1 = "<input type='button' value='다시시도' onclick='javascript:history.back()'>";
			String link2 = "<input type='button' value='그룹목록' onclick='location.href=\"list.do\"'>";
			
			mav.addObject("msg1", msg1);
			mav.addObject("img", img);
			mav.addObject("link1", link1);
			mav.addObject("link2", link2);
			
		} else {
			String al = "alert(등록이 완료 되었습니다!)";
			mav.addObject(al);
			mav.setViewName("redirect:/mediagroup/list.do");
		}
		return mav;
	}
}
