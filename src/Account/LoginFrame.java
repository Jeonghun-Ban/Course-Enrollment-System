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
	private JPanel idPanel, pwPanel, btnPanel;//�г�...
	private JButton button1,button2;//��ư
	private ActionListener actionListener;
	
	public LoginFrame() {
		super("�������б� ������û �α���");
		this.actionListener = new ActionHandler(); 
		
		// �����̳�
		Container container = getContentPane();
		container.setLayout(new FlowLayout());
	
		// �̹���
		EtchedBorder eborder = new EtchedBorder();
		Icon logo = new ImageIcon("image/logo.jfif");
		image = new JLabel(logo);
		image.setBorder(eborder);
		
		// ��
		label = new JLabel("ID�� PW�� �Է��ϼ���.");

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
		
		// �α��� ȸ������ �г�
		btnPanel = new JPanel();
		button1 = new JButton("�α���");
		button2 = new JButton("ȸ������");
		button1.addActionListener(actionListener);
		btnPanel.add(button1);
		btnPanel.add(button2);
		
		// �����̳ʿ� ������Ʈ �߰�
		container.add(image);
		container.add(label);
		container.add(idPanel);
		container.add(pwPanel);
		container.add(btnPanel);
		
		this.setSize(300, 420); // x,y��
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
