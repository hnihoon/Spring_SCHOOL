package kr.co.itwill.user3;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class User3DAOImp implements User3DAO{
	
	@Autowired
	private JdbcTemplate jt;
	
	StringBuilder sql = null;
	
	public User3DAOImp() {
		System.out.println("-----User3DAOImp() 객체 생성됨");
	}

	@Override
	public List<User3DTO> findAll() {
		List<User3DTO> list=null;
		try {
			sql=new StringBuilder();
			sql.append(" SELECT no, name, email, pwd, gender, del, regdate ");
			sql.append(" FROM user3 " );
			sql.append(" ORDER BY no DESC " );
			
			RowMapper<User3DTO> rowMapper = new RowMapper<User3DTO>() {
				@Override
				public User3DTO mapRow(ResultSet rs, int rowNum) throws SQLException {
					
				User3DTO dto = new User3DTO();
				dto.setNo(rs.getInt("no"));
				dto.setName(rs.getString("name"));
				dto.setEmail(rs.getString("email"));
				dto.setPwd(rs.getString("pwd"));
				dto.setGender(rs.getString("gender"));
				dto.setDel(rs.getString("del"));
				dto.setRegdate(rs.getString("regdate"));
				return dto;
			}
		};
		
		list=jt.query(sql.toString(), rowMapper);
		
	}catch(Exception e) {
		
	}
	return list;
}

	@Override
	public int editById(User3DTO uDto) {
		int cnt = 0;
		try {
			sql = new StringBuilder();
			sql.append(" UPDATE user3 ");
			sql.append(" SET name=? " );
			sql.append(" , pwd=? " );
			sql.append(" , gender=? " );
			sql.append(" WHERE no=? " );
			
			cnt = jt.update(sql.toString(), uDto.getName(), uDto.getPwd(), uDto.getGender(), uDto.getNo());
			
		}catch(Exception e) {
			System.out.println("user3 수정 실패 : " + e);
		}
		return cnt;
	}

	@Override
	public int delete(int no) {
		int cnt = 0;
		try {
			sql = new StringBuilder();
			sql.append(" UPDATE user3 SET del='0' " );
			sql.append(" WHERE no=? ");
			cnt = jt.update(sql.toString(), no);
			
		}catch(Exception e) {
			System.out.println("user3 삭제 실패 : " + e);
		}
		return cnt;
	}

	@Override
	public int save(User3DTO uDto) {
		//System.out.println(uDto);
		int cnt=0;
		try {
			sql=new StringBuilder();
			
			sql.append(" INSERT INTO user3(no, name, email, pwd, gender) ");
			sql.append(" VALUES(user3_seq.nextval, ?, ?, ?, ?) ");
			
			cnt=jt.update(sql.toString(), uDto.getName(), uDto.getEmail(), uDto.getPwd(), uDto.getGender());
			
		}catch(Exception e) {
			System.out.println("user3 등록 실패 : " + e);
		}
		return cnt;
	}
}
