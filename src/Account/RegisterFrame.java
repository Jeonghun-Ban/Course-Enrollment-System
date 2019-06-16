package Account;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

public class RegisterFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private JTextField idField, nameField;
	private JPasswordField pwField, rePwField;
	private JButton submit;
	private JLabel idLabel, pwLabel, rePwLabel, nameLabel, alert;
	private JPanel registerPanel;

	private DocumentListener documentListener;
	private ActionListener actionListener;

	private CLogin cLogin;
	String message = "�Ʒ� ���� ��� �Է����ּ���.";
	String id, pw, rePw, name;

	private boolean validId, validPw;

	public RegisterFrame(CLogin cLogin) {

		this.cLogin = cLogin;

		this.setTitle("ȸ������");
		this.setSize(400, 545);
		this.setLayout(new BorderLayout());

		Font font = new Font("���", Font.BOLD, 15);
		// ������ �̹���
		File icon = new File("image/icon.gif");
		try {
			Image img = ImageIO.read(icon);
			setIconImage(img);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		documentListener = new DocumentHandler();
		actionListener = new ActionHandler();

		alert = new JLabel(message);
		alert.setHorizontalAlignment(SwingConstants.CENTER);
		alert.setFont(font);

		registerPanel = new JPanel();
		registerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		registerPanel.setLayout(new GridLayout(12, 1));

		// id �г�
		idLabel = new JLabel("���̵�");
		idField = new JTextField();
		idField.getDocument().addDocumentListener(documentListener);

		// pw
		pwLabel = new JLabel("��й�ȣ");
		pwField = new JPasswordField();
		pwField.getDocument().addDocumentListener(documentListener);
		rePwLabel = new JLabel("��й�ȣ Ȯ��");
		rePwField = new JPasswordField();
		rePwField.getDocument().addDocumentListener(documentListener);

		// name
		nameLabel = new JLabel("�̸�");
		nameField = new JTextField();
		nameField.registerKeyboardAction(this.actionListener, "submit", KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),
				JComponent.WHEN_FOCUSED);
		nameField.getDocument().addDocumentListener(documentListener);

		// add component in registerPanel
		registerPanel.add(idLabel);
		registerPanel.add(idField);
		registerPanel.add(pwLabel);
		registerPanel.add(pwField);
		registerPanel.add(rePwLabel);
		registerPanel.add(rePwField);
		registerPanel.add(nameLabel);
		registerPanel.add(nameField);

		submit = new JButton("����");
		submit.setActionCommand("submit");
		submit.addActionListener(actionListener);
		submit.setEnabled(false);

		JScrollPane scrollpane = new JScrollPane();
		scrollpane.setViewportView(this.registerPanel);
		this.add("North", alert);
		this.add(scrollpane);
		this.add("South", submit);
	}

	public void changed(Document document) {

		// ��й�ȣ ��ȿ�� üũ
		if (document == rePwField.getDocument()) {

			pw = "";
			for (char cha : pwField.getPassword()) {
				Character.toString(cha);
				pw += cha;
			}

			rePw = "";
			for (char cha : rePwField.getPassword()) {
				Character.toString(cha);
				rePw += cha;
			}

			if (pw.equals("") || rePw.equals("")) {
				this.alert.setText("��й�ȣ�� �Է����ּ���");
				this.alert.setForeground(Color.RED);
			} else if (pw.equals(rePw) && !rePw.equals("")) {
				validPw = true;
				this.alert.setText("��й�ȣ�� Ȯ�εǾ����ϴ�.");
				this.alert.setForeground(Color.BLACK);
			} else if (!pw.equals(rePw) && !rePw.equals("")) {
				validPw = false;
				this.alert.setText("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
				this.alert.setForeground(Color.RED);
			} else {
				this.alert.setText(message);
				this.alert.setForeground(Color.BLACK);
			}
		} // ���̵� ��ȿ�� üũ
		else if (document == idField.getDocument()) {
			try {
				id = idField.getText();
				validId = this.cLogin.validId(id);
				if (!validId && !id.equals("")) {
					this.alert.setText("�̹� �����ϴ� ���̵��Դϴ�.");
					this.alert.setForeground(Color.RED);
				} else if (validId && !id.equals("")) {
					this.alert.setText("��� ������ ���̵��Դϴ�.");
					this.alert.setForeground(Color.BLACK);
				} else {
					this.alert.setText(message);
					this.alert.setForeground(Color.BLACK);
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			this.alert.setText(message);
			this.alert.setForeground(Color.BLACK);
		}

		// ���̵� ��й�ȣ ��� ��ȿ�� ��� ��ư Ȱ��ȭ
		if (validPw && validId && !nameField.getText().equals("")) {
			submit.setEnabled(true);
		} else {
			submit.setEnabled(false);
		}
	}

	public void addAccount() {
		// TODO Auto-generated method stub
		name = nameField.getText();
		try {
			cLogin.addAccount(id, pw, name);

			// ȸ�������� ������� ���� ����
			FileWriter basket = new FileWriter("data/basket" + id, false);
			basket.close();
			FileWriter apply = new FileWriter("data/apply" + id, false);
			apply.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private class DocumentHandler implements DocumentListener {

		@Override
		public void changedUpdate(DocumentEvent e) {
			// TODO Auto-generated method stub
			changed(e.getDocument());
		}

		@Override
		public void insertUpdate(DocumentEvent e) {
			// TODO Auto-generated method stub
			changed(e.getDocument());
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			// TODO Auto-generated method stub
			changed(e.getDocument());
		}

	}

	private class ActionHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getActionCommand() == "submit") {
				addAccount();
				dispose();
			}

		}

	}
}
