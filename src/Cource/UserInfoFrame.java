package Cource;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;

import main.CurrentUser;

public class UserInfoFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel id, name, major, credit;
	
	public UserInfoFrame() {
		this.setSize(400, 545);
		
		id = new JLabel();
		id.setText("아이디: " + CurrentUser.id);
		name = new JLabel();
		name.setText("이름: " + CurrentUser.name);
		major = new JLabel();
		major.setText("전공: " + CurrentUser.major);
		credit = new JLabel();
		credit.setText("이수가능학점: " + Integer.toString(CurrentUser.credit));
		
		this.add(id);
		this.add(name);
		this.add(major);
		this.add(credit);
		
		this.setTitle("유저정보");
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
	}
	
	

}
