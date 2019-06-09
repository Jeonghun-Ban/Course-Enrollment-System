package Account;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import control.CLogin;

public class LoginFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	
	private JLabel label, image, idLb, pwLb;
	private JTextField idField;
	private JPasswordField pwField;
	private JPanel idPanel, pwPanel, btnPanel;//패널...
	private JButton button1,button2;//버튼
	private ActionListener actionListener;
	
	public LoginFrame() {
		super("명지대학교 수강신청 로그인");
		this.actionListener = new ActionHandler(); 
		
		// 컨테이너
		Container container = getContentPane();
		container.setLayout(new FlowLayout());
	
		// 이미지
		EtchedBorder eborder = new EtchedBorder();
		Icon logo = new ImageIcon("image/logo.jfif");
		image = new JLabel(logo);
		image.setBorder(eborder);
		
		// 라벨
		label = new JLabel("ID와 PW를 입력하세요.");

		// id
		idPanel = new JPanel();
		idLb = new JLabel("  ID: ");
		idField = new JTextField(10);
		idPanel.add(idLb);
		idPanel.add(idField);
		
		// pw
		pwPanel = new JPanel();
		pwLb = new JLabel("PW: ");
		pwField = new JPasswordField(10);
		pwPanel.add(pwLb);
		pwPanel.add(pwField);
		
		// 로그인 회원가입 패널
		btnPanel = new JPanel();
		button1 = new JButton("로그인");
		button2 = new JButton("회원가입");
		button1.addActionListener(actionListener);
		btnPanel.add(button1);
		btnPanel.add(button2);
		
		// 컨테이너에 컴포넌트 추가
		container.add(image);
		container.add(label);
		container.add(idPanel);
		container.add(pwPanel);
		container.add(btnPanel);
		
		this.setSize(300, 420); // x,y축
		this.setLocationRelativeTo(null);

	}
	
	private class ActionHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == button1) {
				CLogin cLogin = new CLogin();
				String id = idField.getText();
				String pw = "";
				// password char[] to String
				for (char cha : pwField.getPassword()) {
					Character.toString(cha);
					pw += cha;
				}
					
				try {
					cLogin.authenticate(id, pw);
				} catch (FileNotFoundException | InvalidUserException e1) {
					// TODO Auto-generated catch block
					label.setText(e1.getMessage());
			        label.setForeground(Color.RED);
				}
				
			}
		}
		
	}
	
}
