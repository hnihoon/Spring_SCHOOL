package kr.co.itwill.user3;

public class ResultDTO {

	private Boolean state;
	private Object result;
	private String message;
	
	public ResultDTO() {}

	public Boolean getState() {
		return state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ResultDTO [state=" + state + ", result=" + result + ", message=" + message + "]";
	}
	
}
