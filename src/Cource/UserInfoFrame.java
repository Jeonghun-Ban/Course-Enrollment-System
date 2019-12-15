package Cource;

import java.awt.FlowLayout;

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
		id.setText(CurrentUser.id);
		name = new JLabel();
		name.setText(CurrentUser.name);
		major = new JLabel();
		major.setText(CurrentUser.major);
		credit = new JLabel();
		credit.setText(Integer.toString(CurrentUser.credit));
		
		this.add(id);
		this.add(name);
		this.add(major);
		this.add(credit);
		
		this.setLayout(new FlowLayout());
	}
	
	

}
