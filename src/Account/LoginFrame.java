package Account;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import Cource.CourceFrame;
import Framework.ICLogin;

public class LoginFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private JLabel label, image, idLb, pwLb;
	private JTextField idField;
	private JPasswordField pwField;
	private JPanel textPanel, checkPanel;// 패널...
	private JButton loginBtn, registerBtn;// 버튼
	private JCheckBox checkId, checkLogin;
	
	private boolean idSelect;
	private String[] option;
	private ICLogin iCLogin;

	private ActionListener actionListener;
	private CourceFrame courceFrame;
	private RegisterFrame registerFrame;
	
	public LoginFrame(ICLogin iCLogin) {
		this.actionListener = new ActionHandler();
		this.iCLogin = iCLogin;
		
		try {
			option = iCLogin.getOption();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.setTitle("로그인");
		this.setLayout(new FlowLayout());

		// 폰트
		Font font = new Font("고딕", Font.TRUETYPE_FONT, 15);

		// 아이콘 이미지
		File icon = new File("image/icon.gif");
		try {
			Image img = ImageIO.read(icon);
			setIconImage(img);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 로고 이미지
		ImageIcon logo = new ImageIcon("image/logo.jfif");
		Image logoImg = logo.getImage();
		Image setSizeImg = logoImg.getScaledInstance(350, 350, Image.SCALE_SMOOTH);
		ImageIcon newLogo = new ImageIcon(setSizeImg);
		image = new JLabel(newLogo);

		// 라벨
		label = new JLabel("ID와 PW를 입력하세요.");
		label.setFont(font);

		textPanel = new JPanel();
		// id
		idLb = new JLabel("   ID: ");
		idLb.setFont(font);
		idField = new JTextField(14);
		
		// 아이디가 저장되어 있는 경우, 아이디 불러오기
		if(option[0].equals("아이디저장")) {
			idField.setText(option[1]);
			idSelect = true;
		}
		
		textPanel.add(idLb);
		textPanel.add(idField);
		// pw
		pwLb = new JLabel("PW: ");
		pwLb.setFont(font);
		pwField = new JPasswordField(14);
		pwField.registerKeyboardAction(this.actionListener, "login", KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),
				JComponent.WHEN_FOCUSED);
		textPanel.add(pwLb);
		textPanel.add(pwField);

		textPanel.setPreferredSize(new Dimension(220, 60));

		// 체크박스
		checkPanel = new JPanel();
		checkId = new JCheckBox("아이디 저장");
		checkLogin = new JCheckBox("로그인 정보 저장");
		checkId.setSelected(idSelect);
		checkPanel.add(checkId);
		checkPanel.add(checkLogin);

		// 로그인 회원가입 패널
		loginBtn = new JButton("로그인");
		loginBtn.setActionCommand("login");
		loginBtn.addActionListener(actionListener);
		loginBtn.setPreferredSize(new Dimension(100, 50));

		registerBtn = new JButton("회원가입");
		registerBtn.setActionCommand("register");
		registerBtn.addActionListener(actionListener);
		registerBtn.setBorderPainted(false);
		registerBtn.setContentAreaFilled(false);
		registerBtn.setFocusPainted(false);

		// 프레임에 컴포넌트 추가
		this.add(image);
		this.add(textPanel);
		this.add(loginBtn);
		this.add(checkPanel);
		this.add(registerBtn);
		this.add(label);

		this.setSize(400, 540); // x,y축
		this.setResizable(false);
		this.setLocationRelativeTo(null);

	}

	private class ActionHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getActionCommand() == "login") {
				String id = idField.getText();
				String pw = "";
				// password char[] to String
				for (char cha : pwField.getPassword()) {
					Character.toString(cha);
					pw += cha;
				}

				try {
					iCLogin.authenticate(id, pw);
					String name = iCLogin.getName();
					// 로그인이 되었을 시 실행되는 코드
					if(checkLogin.isSelected()) {
						iCLogin.setOption("자동로그인", id, name);
					}else if(checkId.isSelected()) {
						iCLogin.setOption("아이디저장", id, "null");
					}else {
						iCLogin.setOption("null", "null", "null");
					}
					
					courceFrame = new CourceFrame(id, name);
					courceFrame.setVisible(true);
					courceFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

					dispose();
				} catch (InvalidUserException | IOException e1) {
					// TODO Auto-generated catch block
					label.setText(e1.getMessage());
					label.setForeground(Color.RED);
				}

			} else if(e.getActionCommand() == "register") {
				registerFrame = new RegisterFrame(iCLogin);
				registerFrame.setVisible(true);
				Point point = getLocation();
				registerFrame.setLocation(point.x+390, point.y);
				
			}
		}

	}

}
