package kr.co.itwill.user3;

public class User3DTO {

	private int no;
	private String name;
	private String email;
	private String pwd;
	private String gender;
	private String del;
	private String regdate;
	
	public User3DTO() {}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDel() {
		return del;
	}

	public void setDel(String del) {
		this.del = del;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return "User3DTO [no=" + no + ", name=" + name + ", email=" + email + ", pwd=" + pwd + ", gender=" + gender
				+ ", del=" + del + ", regdate=" + regdate + "]";
	}
	
}
