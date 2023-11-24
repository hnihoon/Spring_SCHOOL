package kr.co.itwill.comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/comment")
public class CommentCont {

	public CommentCont() {
		System.out.println("-----CommentCont()객체생성됨");
	}

	@Autowired
	private CommentDAO commentDao;
	
	@PostMapping("/insert")
	@ResponseBody
	public int mCommentServiceInsert(@RequestParam int product_code, @RequestParam String content, HttpSession session) {
									 //HttpServletRequest req
									 //@ModelAttribute commentDTO commentDto
		CommentDTO commentDto = new CommentDTO();
		commentDto.setProduct_code(product_code);
		commentDto.setContent(content);
		commentDto.setWname("test");
		//로그인 기능을 구현했거나 따로 댓글 작성자를 입력받는 폼이 있다면 입력 받아온 값으로 사용하면 된다.
		//->예)session.getAttribute("s_id")활용
		//->여기서는 따로 폼을 구현하지 않았기 때문에 임시로 "test"
		
		int cnt = commentDao.commentInsert(commentDto);
		
		return cnt;
	}
	
	@GetMapping("/list")
	@ResponseBody
	public List<CommentDTO> mCommentServiceList(int product_code) throws Exception {
		List<CommentDTO> list = commentDao.commentList(product_code);
		return list;
	}
	
	@PostMapping("/update")
	@ResponseBody
	public int mCommentServiceUpdateProc(@RequestParam int cno, @RequestParam String content) throws Exception {
		CommentDTO commentDto = new CommentDTO();
		commentDto.setCno(cno);
		commentDto.setContent(content);
		//로그인하고 난 후, 댓글수정을 하려면 mCommentServiceInsert()함수 참조
		
		int cnt = commentDao.commentUpdage(commentDto);
		return cnt;
	}					
						  
	@PostMapping("/delete/{cno}")
	@ResponseBody
	public int mCommentServiceDelete(@PathVariable int cno) throws Exception{
		int cnt = commentDao.commentDelete(cno);
		return cnt;
	}
}




























































