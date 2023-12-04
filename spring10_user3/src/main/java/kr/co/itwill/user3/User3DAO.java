package kr.co.itwill.user3;

import java.util.List;

public interface User3DAO {

	public List<User3DTO> findAll();	//목록
	public int editById(User3DTO uDto);
	public int delete(int no);
	public int save(User3DTO uDto);		//추가
}
