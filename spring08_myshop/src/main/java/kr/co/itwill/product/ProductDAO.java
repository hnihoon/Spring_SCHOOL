package kr.co.itwill.product;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDAO {

	public ProductDAO() {
		System.out.println("-----ProductDAO() 객체 생성됨");
	}
	
	//스프링 빈으로 자동 생성된 객체를 가져와서 연결하기 (Spring08MyshopApplication 클래스 참조)
	@Autowired
	SqlSession sqlSession;
	
	public void insert(Map<String, Object> map) {
		sqlSession.insert("product.insert", map);
	}
	
}
