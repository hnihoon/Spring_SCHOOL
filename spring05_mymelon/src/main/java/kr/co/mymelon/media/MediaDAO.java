package kr.co.mymelon.media;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MediaDAO {
	
	@Autowired
	JdbcTemplate jt;

	public MediaDAO() {
		System.out.println("-----MediaDAO()객체 생성됨");
	}

	
}
