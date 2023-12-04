package kr.co.itwill.user3;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class User3ServiceImp implements User3Service{

	@Autowired
	User3DAOImp uDao;
	
	private ResultDTO rDto;
	
	public User3ServiceImp() {
		System.out.println("-----UserServiceImp()객체 생성됨");
	}

	@Override
	public ResultDTO findAll() {
		rDto = new ResultDTO();
		List<User3DTO>resultList=uDao.findAll();
		if(resultList != null) {
			rDto.setState(true);
			rDto.setResult(resultList);
		} else {
			rDto.setState(false);
		}
		return rDto;
	}

	@Override
	public ResultDTO editById(User3DTO uDto) {
		rDto = new ResultDTO();
		int state=uDao.editById(uDto);
		if(state == 1) {
			rDto.setState(true);
			rDto.setMessage("사용자 수정이 성공 하였습니다.");
		} else {
			rDto.setState(false);
			rDto.setMessage("사용자 수정이 실패 하였습니다.");
		}
		return rDto;
	}

	@Override
	public ResultDTO delete(int no) {
		rDto = new ResultDTO();
		int state=uDao.delete(no);
		if(state == 1) {
			rDto.setState(true);
			rDto.setMessage("사용자 삭제가 성공 하였습니다.");
		} else {
			rDto.setState(false);
			rDto.setMessage("사용자 삭제가 실패 하였습니다.");
		}
		return rDto;
	}

	@Override
	public ResultDTO save(User3DTO uDto) {
			rDto = new ResultDTO();
			int state=uDao.save(uDto);
			if(state == 1) {
				rDto.setState(true);
				rDto.setMessage("사용자 생성이 성공 하였습니다.");
			} else {
				rDto.setState(false);
				rDto.setMessage("사용자 생성이 실패 하였습니다.");
			}
			return rDto;
		}
}
