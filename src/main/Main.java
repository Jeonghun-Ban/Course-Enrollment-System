package main;

import java.io.FileNotFoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import javax.swing.JFrame;

import Account.LoginFrame;
import Account.LoginOption;
import Cource.CourceFrame;
import Framework.ICLogin;

public class Main {

	private static String[] option;
	
	static ICLogin iCLogin = null;
	static LoginOption loginOption = new LoginOption();
	
	static LoginFrame loginFrame;
	static CourceFrame courceFrame;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			
		try {
			Constant.registry = LocateRegistry.getRegistry("localhost");
			iCLogin = (ICLogin) Constant.registry.lookup("iCLogin");
			option = loginOption.get();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(option[0].equals("자동로그인")) {
			// 현재 유저 클래스 정의
			try {
				CurrentUser.id =option[1];
				CurrentUser.name = option[2];
				CurrentUser.credit = iCLogin.getCredit();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			courceFrame = new CourceFrame();
			courceFrame.setVisible(true);
			courceFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}else {
			loginFrame = new LoginFrame(iCLogin);
			loginFrame.setVisible(true);
			loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // close button func//
		}
		
	}

}
