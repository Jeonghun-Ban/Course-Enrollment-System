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
    JPanel idPanel, pwPanel, btnPanel;//�г�...
    JButton button1,button2;//��ư
	
	
	public LoginFrame() {
		super("�������б� ������û �α���");
	
		// �����̳�
		Container container = getContentPane();
		container.setLayout(new FlowLayout());
	
		// �̹���
		EtchedBorder eborder = new EtchedBorder();
		Icon logo = new ImageIcon("image/logo.jfif");
		image = new JLabel(logo);
		image.setBorder(eborder);
		image.setToolTipText("�ΰ�");
		
		// ��
		label = new JLabel("ID�� PW�� �Է��ϼ���.");

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
		
		// �α��� ȸ������ �г�
		btnPanel = new JPanel();
		button1 = new JButton("�α���");
		button2 = new JButton("ȸ������");
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
}
