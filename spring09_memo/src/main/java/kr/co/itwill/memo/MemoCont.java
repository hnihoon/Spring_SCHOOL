package kr.co.itwill.memo;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//● Frontend단 : Vue.js기반의 서버통신
//참조 E:\java202307\workspace_vue\project04\src\views\3_memo\MemoListView.vue
/*
● CORS(Cross-origin resource sharing)
- 웹 페이지의 제한된 자원을 외부 도메인에서 접근을 허용해주는 매커니즘
- 서로 다른 도메인에서 리소스를 공유하는 방식
- @CrossOrigin : 모든 도메인, 모든 요청방식에 대해 허용
*/

//@Controller + @ResponseBody
@RestController
@CrossOrigin
public class MemoCont {

	public MemoCont() {
		System.out.println("-----MemoCont()객체 생성됨");
	}

	@GetMapping("memolist")
	public JSONObject[] memolist() {
		
		JSONObject dto1 = new JSONObject();
		dto1.put("memono", 1);
		dto1.put("subject", "무궁화 꽃이 피었습니다");
		dto1.put("writer", "오필승");
		dto1.put("readcnt", 5);
		
		JSONObject dto2 = new JSONObject();
		dto2.put("memono", 2);
		dto2.put("subject", "바람과 함께 사라지다");
		dto2.put("writer", "코리아");
		dto2.put("readcnt", 7);
		
		JSONObject[] jsons = {dto1, dto2};
		return jsons;
	}
	
}
