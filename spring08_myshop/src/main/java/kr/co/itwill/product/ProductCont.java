package kr.co.itwill.product;

import java.io.File;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;

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
		if(img != null || !img.isEmpty()) { //파일이 존재한다면
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
}














