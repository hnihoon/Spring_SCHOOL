package kr.co.mymelon.media;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MediaCont {
	
	@Autowired
	MediaDAO dao;

	public MediaCont() {
		System.out.println("-----MediaCont() 객체 생성됨");
	}
	
}
