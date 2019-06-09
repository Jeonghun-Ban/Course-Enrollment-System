package Account;

import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

public class LoginFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	
	JLabel label, image, idLb, pwLb;
	JTextField id;
    JPasswordField pw;
    JPanel idPanel, pwPanel, btnPanel;//패널...
    JButton button1,button2;//버튼
	
	
	public LoginFrame() {
		super("명지대학교 수강신청 로그인");
	
		// 컨테이너
		Container container = getContentPane();
		container.setLayout(new FlowLayout());
	
		// 이미지
		EtchedBorder eborder = new EtchedBorder();
		Icon logo = new ImageIcon("image/logo.jfif");
		image = new JLabel(logo);
		image.setBorder(eborder);
		image.setToolTipText("로고");
		
		// 라벨
		label = new JLabel("ID와 PW를 입력하세요.");

		// id
		idPanel = new JPanel();
		idLb = new JLabel("  ID: ");
		id = new JTextField(10);
		idPanel.add(idLb);
		idPanel.add(id);
		
		// pw
		pwPanel = new JPanel();
		pwLb = new JLabel("PW: ");
		pw = new JPasswordField(10);
		pwPanel.add(pwLb);
		pwPanel.add(pw);
		
		// 로그인 회원가입 패널
		btnPanel = new JPanel();
		button1 = new JButton("로그인");
		button2 = new JButton("회원가입");
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
}
