package main;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.JFrame;

import Account.LoginFrame;
import Account.LoginOption;
import Cource.CourceFrame;

public class Main {

	private static String[] option;
	
	private static final LoginOption loginOption = new LoginOption();

	private static LoginFrame loginFrame;
	private static CourceFrame courceFrame;
	private static InputStream inputStream = null;
	private static OutputStream outputStream = null;
	
	public static void main(String[] args) {
		try {
			Connector.initialize();
			option = loginOption.get();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		if(option[0].equals("auto")) {
			courceFrame = new CourceFrame();
			courceFrame.setVisible(true);
			courceFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}else {
			loginFrame = new LoginFrame();
			loginFrame.setVisible(true);
			loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // close button func//
		}
		
	}

}
