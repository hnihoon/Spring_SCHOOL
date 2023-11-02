package kr.co.itwill;

import javax.swing.JOptionPane;

public class MessageEN implements IHello{

	public MessageEN() {
		System.out.println("MessageEN 호출됨");
	}
	
	@Override
	public void sayHello(String name) {
		JOptionPane.showInternalMessageDialog(null, "Hello~~" + name);
	}
	
}
