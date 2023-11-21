package kr.co.itwill.product;

import java.io.File;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import kr.co.itwill.comment.CommentDAO;

@Controller
@RequestMapping("/product")
public class ProductCont {

	public ProductCont() {
		System.out.println("-----ProductCont()객체 생성됨");
	}
	
	@Autowired
	ProductDAO productDao;
	
	//결과확인 http://localhost:9095
	//또는 http://localhost:9095/product/list
	
	@RequestMapping("/list")
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("product/list");
		mav.addObject("list", productDao.list());
		return mav;
	}
	
	@GetMapping("/write")
	public String write() {
		return "product/write";
	}
	
	@PostMapping("/insert")
	public String insert(@RequestParam Map<String, Object> map,
						 @RequestParam MultipartFile img,
						 HttpServletRequest req) {
		//매개변수가 Map이면 name이 key로 저장된다
		//예<input type="text" name="product_name">
		
		//주의사항 : 파일업로드할 때 리네임 되지 않음
		//업로드된 파일은 /storage 폴더에 저장
		
		String filename="-";
		long filesize = 0;
		if(img != null && !img.isEmpty()) { //파일이 존재한다면
			filename=img.getOriginalFilename();
			filesize=img.getSize();
			try {
				ServletContext application = req.getSession().getServletContext();
				String path = application.getRealPath("/storage"); //실제 물리적인 경로
				img.transferTo(new File(path + "\\" + filename)); //파일저장
			} catch(Exception e) {
				System.out.println(e);
			}
		}
		
		map.put("filename", filename);
		map.put("filesize", filesize);
		
		productDao.insert(map);
		
		return "redirect:/product/list";
	}

	@GetMapping("/search")
	public ModelAndView search(@RequestParam(defaultValue = "" ) String product_name) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("product/list");
		mav.addObject("list", productDao.search(product_name));
		mav.addObject("product_name", product_name); //검색어
		return mav;
	}
	
	/*
	  	※ 참조 : http://pretyimo.cafe24.com/lectureRead.do?lectureno=438
	  	
		Mapping의 종류
		@GetMapping
		@PostMapping
		@PutMapping
		@DeleteMapping
		
		전송방식 : method = get | post | put | delete
	 */
	
	/*
	@RequestParam
	http://localhost:9095?aaa=bbb&ccc=ddd
	
	@PathVariable
	http://localhost:9095/bbb/ddd
	*/
	
	//http://localhost:9095/product/detail?product_code=22
    //@GetMapping("/detail") 
    //public ModelAndView detail(@RequestParam int product_code) {}
	  
	//http://localhost:9095/product/detail/5
	/*
	 * @GetMapping("/detail/{product_code}") public ModelAndView
	 * detail(@PathVariable int product_code) { ModelAndView mav = new
	 * ModelAndView(); mav.setViewName("product/detail"); mav.addObject("product",
	 * productDao.detail(product_code)); return mav; }
	 */
	
	@GetMapping("/detail/{product_code}")
	public ModelAndView detail(@PathVariable int product_code) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("product/detail");
		mav.addObject("product", productDao.detail(product_code));
		return mav;
	}
	
	@PostMapping("delete")
	public String delete(HttpServletRequest req) {
		int product_code = Integer.parseInt(req.getParameter("product_code"));
		
		//삭제하고자 하는 파일명 가져오기
		String filename=productDao.filename(product_code);
		
		//첨부된 파일 삭제하기
		if(filename != null && !filename.equals("-")) {
			ServletContext application = req.getSession().getServletContext();
			String path = application.getRealPath("/storage");
			File file=new File(path + "\\" + filename);
			if(file.exists()) {
				file.delete();
			}
		}
		
		productDao.delete(product_code);
		
		return "redirect:/product/list";
	}
	
	@PostMapping("/update")
	public String update(@RequestParam Map<String, Object> map
					   , @RequestParam MultipartFile img
					   , HttpServletRequest req){
		
		String filename="-";
		long filesize = 0;
		if(img != null && !img.isEmpty()) { //파일이 존재한다면
			filename=img.getOriginalFilename();
			filesize=img.getSize();
			try {
				ServletContext application = req.getSession().getServletContext();
				String path = application.getRealPath("/storage"); //실제 물리적인 경로
				img.transferTo(new File(path + "\\" + filename)); //파일저장
				
			} catch(Exception e) {
				System.out.println(e);
			}
		} else {
			int product_code = Integer.parseInt(map.get("product_code").toString());
			Map<String, Object> oldProduct=productDao.detail(product_code);
			filename=oldProduct.get("FILENAME").toString();
			filesize=Long.parseLong(oldProduct.get("FILESIZE").toString());
		}
		
		map.put("filename", filename);
		map.put("filesize", filesize);
		productDao.update(map);
		
		return "redirect:/product/list";
	}
}















