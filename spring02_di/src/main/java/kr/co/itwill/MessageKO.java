package kr.co.itwill;

public class MessageKO implements IHello{
	//내 클래스의 부모가 인터페이스
	public MessageKO() {
		System.out.println("MessageKo 호출됨");
	}
	
	@Override
	public void sayHello(String name) {
		System.out.println("안녕하세요~~" + name + "님");
	}

	
}
