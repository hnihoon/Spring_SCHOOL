package kr.co.itwill;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloSpringbean {

	public static void main(String[] args) {
		// 참조 http://pretyimo.cafe24.com/lectureRead.do?lectureno=261
		// DI(Dependency Injection) 의존성 주입
		// -> 설정 파일(xml) 또는 어노테이션을 통해 객체간 의존 관계 설정
		
		ApplicationContext context = new ClassPathXmlApplicationContext("springbean.xml");
		
		IHello hello = null;
		hello = (IHello) context.getBean("msgEN"); //스프링 빈을 불러와서 객체 생성
		hello.sayHello("TOM");
	}

}
