package Account;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
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
import javax.swing.border.EtchedBorder;

import Cource.CourceFrame;

public class LoginFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private JLabel label, image, idLb, pwLb;
	private JTextField idField;
	private JPasswordField pwField;
	private JPanel textPanel, checkPanel;// �г�...
	private JButton loginBtn, registerBtn;// ��ư
	private JCheckBox checkId, checkLogin;
	
	private boolean idSelect;
	private String[] option;
	private CLogin cLogin;
	private String name;

	private ActionListener actionListener;
	private CourceFrame courceFrame;
	
	public LoginFrame(CLogin cLogin) {
		this.actionListener = new ActionHandler();
		this.cLogin = cLogin;
		this.name = cLogin.getName();
		
		try {
			option = cLogin.getOption();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		this.setTitle("�α���");
		this.setLayout(new FlowLayout());

		// ��Ʈ
		Font font = new Font("���", Font.TRUETYPE_FONT, 15);

		// ������ �̹���
		File icon = new File("image/icon.gif");
		try {
			Image img = ImageIO.read(icon);
			setIconImage(img);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// �ΰ� �̹���
		ImageIcon logo = new ImageIcon("image/logo.jfif");
		Image logoImg = logo.getImage();
		Image setSizeImg = logoImg.getScaledInstance(350, 350, Image.SCALE_SMOOTH);
		ImageIcon newLogo = new ImageIcon(setSizeImg);
		image = new JLabel(newLogo);

		// ��
		label = new JLabel("ID�� PW�� �Է��ϼ���.");
		label.setFont(font);

		textPanel = new JPanel();
		// id
		idLb = new JLabel("   ID: ");
		idLb.setFont(font);
		idField = new JTextField(14);
		
		// ���̵� ����Ǿ� �ִ� ���, ���̵� �ҷ�����
		if(option[0].equals("���̵�����")) {
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

		// üũ�ڽ�
		checkPanel = new JPanel();
		checkId = new JCheckBox("���̵� ����");
		checkLogin = new JCheckBox("�α��� ���� ����");
		checkId.setSelected(idSelect);
		checkPanel.add(checkId);
		checkPanel.add(checkLogin);

		// �α��� ȸ������ �г�
		loginBtn = new JButton("�α���");
		loginBtn.setActionCommand("login");
		loginBtn.addActionListener(actionListener);
		loginBtn.setPreferredSize(new Dimension(100, 50));

		registerBtn = new JButton("ȸ������");
		registerBtn.setBorderPainted(false);
		registerBtn.setContentAreaFilled(false);
		registerBtn.setFocusPainted(false);

		// �����ӿ� ������Ʈ �߰�
		this.add(image);
		this.add(textPanel);
		this.add(loginBtn);
		this.add(checkPanel);
		this.add(registerBtn);
		this.add(label);

		this.setSize(400, 540); // x,y��
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
					cLogin.authenticate(id, pw);
					// �α����� �Ǿ��� �� ����Ǵ� �ڵ�
					if(checkLogin.isSelected()) {
						cLogin.setOption("�ڵ��α���", id, name);
					}else if(checkId.isSelected()) {
						cLogin.setOption("���̵�����", id, "null");
					}else {
						cLogin.setOption("null", "null", "null");
					}
					
					courceFrame = new CourceFrame(id, cLogin, name);
					courceFrame.setVisible(true);
					courceFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

					dispose();
				} catch (InvalidUserException | IOException e1) {
					// TODO Auto-generated catch block
					label.setText(e1.getMessage());
					label.setForeground(Color.RED);
				}

			}
		}

	}

}
