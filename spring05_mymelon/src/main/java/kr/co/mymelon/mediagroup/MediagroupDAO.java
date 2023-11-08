package kr.co.mymelon.mediagroup;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

//@Service
//@Repository 모델클래스 지정. 스프링 컨테이너(톰캣)가 자동으로 객체 생성해 준다.
@Repository
public class MediagroupDAO {

	// DBOpen dbopen = new DBOpen(); 와 동일한 형태
	// @Autowired 스프링컨테이너가 생성해 준 객체를 연결
	@Autowired
	private JdbcTemplate jt; // application.properties에서 DB연결에 관련된 정보를 가져온다.
	/*
	 * 1.JdbcTemplate클래스 2.기본 자바JDBC를 좀 더 편리하게 사용할 수 있다. 3.JdbcTemplate =
	 * DriverManager + Connection + Statement + ResultSet 기능을 해준다. 4.JDBC를 위한 툴.
	 * JDBC프로그래밍에 특화되어 있다. ● @Autowired -> Spring이 필요시 자동으로 객체를 생성하여 필드(Instance
	 * variable, 객체 변수)에 할당함 ● @Repository -> DAO를 스프링에 인식시키기 위해서 주로 사용하며, 해당 클래스를
	 * 객체로 만들어 준다 -> 모델클래스로 지정하면 스프링컨테이너에서 관리를 해줌 -> DAO관련 빈을 자동 등록 대상으로 만들때 사용한다
	 * 
	 * ● RowMapper = PreparedStatement + ResultSet
	 * 
	 */

	StringBuilder sql = null;

	public MediagroupDAO() {
		System.out.println("-----MediagroupDAO() 객체 생성됨");
	}

	// 비지니스 로직 구현

	public int create(MediagroupDTO dto) {
		int cnt = 0;
		try {
			sql = new StringBuilder();

			sql.append(" INSERT INTO mediagroup(mediagroupno, title) ");
			sql.append(" VALUES( mediagroup_seq.nextval, ? )");

			// SQL문 (insert, update, delete) 실행
			cnt = jt.update(sql.toString(), dto.getTitle());

		} catch (Exception e) {
			System.out.println("미디어그룹등록실패 : " + e);
		}
		return cnt;
	}

	public List<MediagroupDTO> list() {
		List<MediagroupDTO> list = null;
		try {
			sql = new StringBuilder();
			sql.append(" SELECT mediagroupno, title ");
			sql.append(" FROM mediagroup ");
			sql.append(" ORDER BY mediagroupno DESC ");

			RowMapper<MediagroupDTO> rowMapper = new RowMapper<MediagroupDTO>() {

				@Override
				public MediagroupDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
					MediagroupDTO dto = new MediagroupDTO();
					dto.setMediagroupno(rs.getInt("mediagroupno"));
					dto.setTitle(rs.getString("title"));
					return dto;
				}
			};

			list = jt.query(sql.toString(), rowMapper);

		} catch (Exception e) {
			System.out.println("미디어그룹목록실패 : " + e);
		}
		return list;
	}

	public int totalRowCount() {
		int cnt = 0;
		try {
			sql = new StringBuilder();
			sql.append(" SELECT COUNT(*) FROM mediagroup ");
			// SELECT를 실행했을 때 하나의 객체(object) 결과 값이 나올 때 사용
			cnt = jt.queryForObject(sql.toString(), Integer.class);
		} catch (Exception e) {
			System.out.println("전체 총 갯수 : " + e);
		}
		return cnt;
	}

	public int delete(int mediagroupno) {
		int cnt = 0;
		try {
			sql = new StringBuilder();

			sql.append(" DELETE FROM mediagroup ");
			sql.append(" WHERE mediagroupno = ? ");

			cnt = jt.update(sql.toString(), mediagroupno);

		} catch (Exception e) {
			System.out.println("미디어그룹등록실패 : " + e);
		}
		return cnt;
	}

	public MediagroupDTO read(int mediagroupno) {
		MediagroupDTO dto = null;
		try {

			sql = new StringBuilder();
			sql.append(" SELECT mediagroupno, title ");
			sql.append(" FROM mediagroup ");
			sql.append(" WHERE mediagroupno = " + mediagroupno);

			RowMapper<MediagroupDTO> rowMapper = new RowMapper<MediagroupDTO>() {

				@Override
				public MediagroupDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
					MediagroupDTO dto = new MediagroupDTO();
					dto.setMediagroupno(rs.getInt("mediagroupno"));
					dto.setTitle(rs.getString("title"));
					return dto;
				}
			};

			dto = jt.queryForObject(sql.toString(), rowMapper);

		} catch (Exception e) {
			System.out.println("미디어그룹 상세보기 실패 : " + e);
		}
		return dto;
	}
	
	public int update(MediagroupDTO dto) {
		int cnt = 0;
		try {
			sql = new StringBuilder();
			
			sql.append(" UPDATE mediagroup ");
			sql.append(" set title = ? ");
			sql.append(" where mediagroupno = ? ");			
			
			cnt = jt.update(sql.toString(), dto.getTitle(), dto.getMediagroupno());
			
		} catch(Exception e) {
			System.out.println("미디어그룹 수정에 실패했습니다 : " + e);
		}
		return cnt;
	}
	
	public List<MediagroupDTO> list2(int start, int end){
		List<MediagroupDTO> list=null;
		try {
			sql = new StringBuilder();
			
			sql.append(" SELECT AA.* ");
			sql.append(" FROM( ");
			sql.append("       SELECT ROWNUM as RNUM, BB.* ");
			sql.append("       FROM( ");
			sql.append("            SELECT mediagroupno, title ");
			sql.append("            FROM mediagroup ");
			sql.append("            ORDER BY mediagroupno DESC " );
			sql.append("            )BB ");
			sql.append("     )AA ");
			sql.append(" WHERE AA.RNUM>=" + start + " AND AA.RNUM<= " + end);
			
			RowMapper<MediagroupDTO> rowMapper = new RowMapper<MediagroupDTO>(){
				
				@Override
				public MediagroupDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
					MediagroupDTO dto = new MediagroupDTO();
					dto.setMediagroupno(rs.getInt("mediagroupno"));
					dto.setTitle(rs.getString("title"));
					return dto;
				}
			};
			
			list=jt.query(sql.toString(), rowMapper);
				
			}catch(Exception e) {
				System.out.println("미디어그룹페이징 실패 : " + e);
		}
		return list;
	}
}








