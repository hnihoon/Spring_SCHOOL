package kr.co.itwill.user3;

public interface User3Service {

	public ResultDTO findAll();
	public ResultDTO editById(User3DTO uDto);
	public ResultDTO delete(int no);
	public ResultDTO save(User3DTO uDto);
}
