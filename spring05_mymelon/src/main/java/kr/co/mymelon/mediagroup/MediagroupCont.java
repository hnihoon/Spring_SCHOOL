package kr.co.mymelon.mediagroup;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
	//1) 페이징 없는 목록
	/*@RequestMapping("mediagroup/list.do")
	 * public ModelAndView list() { ModelAndView mav = new ModelAndView();
	 * mav.setViewName("mediagroup/list");
	 * 
	 * List<MediagroupDTO> list = dao.list(); 
	 * int totalRowCount=dao.totalRowCount();
	 * //총 글개수
	 * 
	 * mav.addObject("list", list); 
	 * mav.addObject("count", totalRowCount);
	 * 
	 * return mav; }
	 */
	
	//2) 패이징 있는 목록
	@RequestMapping("mediagroup/list.do")
	public ModelAndView list(HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mediagroup/list");
		
		int totalRowCount = dao.totalRowCount();//총 글갯수
		
		//페이징
		int numPerPage	 = 5; //한 페이지당 행 갯수
		int pagePerBlock = 10;//페이지 리스트
		
		//현재 페이지
		String pageNum = req.getParameter("pageNum");
		if(pageNum==null) { //전달된 현재 페이지값이 없다면, 무조건 1페이지
			pageNum="1";
		}
		
		int currentPage=Integer.parseInt(pageNum);
        int startRow   =(currentPage-1)*numPerPage+1;
        int endRow     =currentPage*numPerPage;
      
        //페이지 수
        double totcnt = (double)totalRowCount/numPerPage;
        int totalPage = (int)Math.ceil(totcnt);
          
        double d_page = (double)currentPage/pagePerBlock;
        int Pages     = (int)Math.ceil(d_page)-1;
        int startPage = Pages*pagePerBlock;
        int endPage   = startPage+pagePerBlock+1;
        
        List list=null;      
        if(totalRowCount>0){            
              list=dao.list2(startRow, endRow);           
        } else {            
              list=Collections.EMPTY_LIST;            
        }
        mav.addObject("pageNum",   currentPage);
        mav.addObject("count",     totalRowCount);
        mav.addObject("totalPage", totalPage);
        mav.addObject("startPage", startPage);
        mav.addObject("endPage",   endPage);
        mav.addObject("list", list);
        
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
			mav.setViewName("redirect:/mediagroup/list.do");
		}
		return mav;
	}
	
	@GetMapping("mediagroup/delete.do")
 	 //전달받는 방법1)
	/* public ModelAndView deleteForm(HttpServletRequest req) { 
	 * int mediagroupno = Integer.parseInt(req.getParameter("mediagroupno"));
	    }*/
	
	//방법2)
	//public ModelAndView deleteForm(@RequestParam("mediagroupno") int a){}
	
	//방법3)
//	public ModelAndView deleteForm(@ModelAttribute MediagroupDTO dto){
//		int mediagroupno = dto.getMediagroupno();
//	}
	
	//방법4)
	public ModelAndView deleteForm(int mediagroupno){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mediagroup/deleteForm");
		mav.addObject("mediagroupno", mediagroupno);
		return mav;
	}
	
	@PostMapping("mediagroup/delete.do")
	public ModelAndView deleteProc(int mediagroupno) {
		ModelAndView mav = new ModelAndView();

		int cnt = dao.delete(mediagroupno);
		if(cnt==0) {
			mav.setViewName("mediagroup/msgView");
			
			String msg1 = "<p>미디어 그룹 삭제 실패</p>";
			String img = "<img src='../images/crying.png'>";
			String link1 = "<input type='button' value='다시시도' onclick='javascript:history.back()'>";
			String link2 = "<input type='button' value='그룹목록' onclick='location.href=\"list.do\"'>";
			
			mav.addObject("msg1", msg1);
			mav.addObject("img", img);
			mav.addObject("link1", link1);
			mav.addObject("link2", link2);
			
		} else {
			mav.setViewName("redirect:/mediagroup/list.do");
		}
		return mav;
	}
	
	@GetMapping("mediagroup/update.do")
	public ModelAndView updateForm(int mediagroupno) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mediagroup/updateForm");
		mav.addObject("dto", dao.read(mediagroupno));
		return mav;
	}
	
	@PostMapping("mediagroup/update.do")
	public ModelAndView updateProc(MediagroupDTO dto) {
		ModelAndView mav = new ModelAndView();
		
		int cnt = dao.update(dto);
		
		if(cnt==0) {
			mav.setViewName("mediagroup/msgView");
			
			String msg1 = "<p>미디어 그룹 수정 실패</p>";
			String img = "<img src='../images/crying.png'>";
			String link1 = "<input type='button' value='다시시도' onclick='javascript:history.back()'>";
			String link2 = "<input type='button' value='그룹목록' onclick='location.href=\"list.do\"'>";
			
			mav.addObject("msg1", msg1);
			mav.addObject("img", img);
			mav.addObject("link1", link1);
			mav.addObject("link2", link2);
			
		} else {
			mav.setViewName("redirect:/mediagroup/list.do");
		}
		return mav;
	}
}




















