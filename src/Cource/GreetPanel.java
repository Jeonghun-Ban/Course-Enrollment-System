package Cource;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class GreetPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	JTextArea greet;
	JButton logout;
	
	public GreetPanel(String name) {
		greet = new JTextArea("�ȳ��ϼ���? "+name+"�� �������б� ������û �ý����Դϴ�.");
		logout = new JButton("�α׾ƿ�");
	}
	
}
