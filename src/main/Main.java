package main;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.swing.JFrame;

import Account.LoginFrame;
import Account.LoginOption;
import Cource.CourceFrame;
import Framework.ICLogin;
import Framework.Launcher;

public class Main {

	private static String[] option;
	
	private static final LoginOption loginOption = new LoginOption();

	private static LoginFrame loginFrame;
	private static CourceFrame courceFrame;
	private static InputStream inputStream = null;
	private static OutputStream outputStream = null;
	
	private static String id;

	private static String pw;
	
	public static void main(String[] args) {
		try {
			Connector.initialize();
			option = loginOption.get();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		

		if(option[0].equals("auto")) {
			
			id = option[1];
			pw = option[2];
			
			
			try {
				// 로그인
				Class<?> iCLogin = ICLogin.class;
				Method method = iCLogin.getMethod("authenticate", String.class, String.class);
				Connector.invoke(new Launcher(iCLogin.getSimpleName(), method.getName(), method.getParameterTypes(), new Object[]{id, pw.toString()}));
				
				method = iCLogin.getMethod("getName");
				CurrentUser.name = (String) Connector.invoke(new Launcher(iCLogin.getSimpleName(), method.getName(), method.getParameterTypes(), new Object[]{}));
				method = iCLogin.getMethod("getMajor");
				CurrentUser.major = (String) Connector.invoke(new Launcher(iCLogin.getSimpleName(), method.getName(), method.getParameterTypes(), new Object[]{}));
				method = iCLogin.getMethod("getCredit");
				CurrentUser.credit = (int) Connector.invoke(new Launcher(iCLogin.getSimpleName(), method.getName(), method.getParameterTypes(), new Object[]{}));
				CurrentUser.id = id;
				
			} catch (InvocationTargetException | NoSuchMethodException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
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
