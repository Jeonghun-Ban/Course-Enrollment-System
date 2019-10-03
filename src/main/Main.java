package main;

import java.io.FileNotFoundException;

import javax.swing.JFrame;

import Account.CLogin;
import Account.LoginFrame;
import Cource.CourceFrame;

public class Main {

	private static CLogin cLogin;
	private static String[] option;
	
	static LoginFrame loginFrame;
	static CourceFrame courceFrame;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		cLogin = new CLogin();
		try {
			option = cLogin.getOption();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(option[0].equals("자동로그인")) {
			courceFrame = new CourceFrame(option[1], cLogin, option[2]);
			courceFrame.setVisible(true);
			courceFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}else {
			loginFrame = new LoginFrame(cLogin);
			loginFrame.setVisible(true);
			loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // close button func//
		}
		
	}

}
