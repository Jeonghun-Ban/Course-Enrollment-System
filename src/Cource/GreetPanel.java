package Cource;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GreetPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	JLabel greet;
	JButton logout;
	
	public GreetPanel(String name, ActionListener actionListener) {
		greet = new JLabel("�ȳ��ϼ���? "+name+"�� �������б� ������û �ý����Դϴ�.");
		logout = new JButton("�α׾ƿ�");
		logout.setFocusPainted(false);
		logout.setActionCommand("logout");
		logout.addActionListener(actionListener);
		
		this.add(greet);
		this.add(logout);
	}
	
}
