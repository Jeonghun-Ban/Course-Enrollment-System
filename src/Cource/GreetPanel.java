package Cource;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class GreetPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	JTextArea greet;
	JButton logout;
	
	public GreetPanel(String name) {
		greet = new JTextArea("안녕하세요? "+name+"님 명지대학교 수강신청 시스템입니다.");
		logout = new JButton("로그아웃");
	}
	
}
