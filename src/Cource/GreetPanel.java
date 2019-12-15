package Cource;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.CurrentUser;

public class GreetPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	JLabel greet;
	JButton logout, info;
	
	public GreetPanel(ActionListener actionListener) {
		
		greet = new JLabel("안녕하세요? "+ CurrentUser.name +"님 명지대학교 수강신청 시스템입니다.");
		
		logout = new JButton("로그아웃");
		logout.setFocusPainted(false);
		logout.setActionCommand("logout");
		logout.addActionListener(actionListener);
		
		logout = new JButton("로그아웃");
		logout.setFocusPainted(false);
		logout.setActionCommand("logout");
		logout.addActionListener(actionListener);
		
		info = new JButton("유저정보");
		info.setFocusPainted(false);
		info.setActionCommand("info");
		info.addActionListener(actionListener);
		
		this.add(greet);
		this.add(logout);
		this.add(info);
		
	}
	
}
