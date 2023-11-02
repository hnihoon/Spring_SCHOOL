package kr.co.itwill;

public class HelloPOJO {

	public static void main(String[] args) {
		//POJO (Plain Old Java Object)
		//-> new연산자를 활용한 객체 생성
		
		IHello hello = null;
		
		hello = new MessageKO(); //POJO방식의 객체 생성
		hello.sayHello("손흥민");
	}

}
