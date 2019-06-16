package Account;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class RegisterFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private JTextField idField, nameField;
	private JPasswordField pwField, rePwField;
	private JButton confirm;
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

		this.setTitle("ȸ������ ��");
		this.setSize(400, 545);
		this.setLayout(new BorderLayout());

		Font font = new Font("���", Font.BOLD, 15);

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

		confirm = new JButton("����");
		confirm.setEnabled(false);
		confirm.addActionListener(actionListener);

		JScrollPane scrollpane = new JScrollPane();
		scrollpane.setViewportView(this.registerPanel);
		this.add("North", alert);
		this.add(scrollpane);
		this.add("South", confirm);
	}

	public void changed() {

		id = idField.getText();
		
		pw = "";
		// password char[] to String
		for (char cha : pwField.getPassword()) {
			Character.toString(cha);
			pw += cha;
		}

		rePw = "";
		// password char[] to String
		for (char cha : rePwField.getPassword()) {
			Character.toString(cha);
			rePw += cha;
		}

		// ���̵� ��ȿ�� üũ
		if (!id.equals("")) {
			try {
				validId = this.cLogin.validId(idField.getText());
				if (!validId) {
					this.alert.setText("�̹� �����ϴ� ���̵��Դϴ�.");
				} else {
					this.alert.setText(message);
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// ��й�ȣ ��ȿ�� üũ
		if (!rePw.equals("")) {

			if (pw.equals("")) {
				this.alert.setText("��й�ȣ�� �Է��ϼ���");
			} else if (pw.equals(rePw)) {
				validPw = true;
			} else {
				validPw = false;
				this.alert.setText("��й�ȣ�� ���� �ʽ��ϴ�.");
			}
		}

		// ���̵� ��й�ȣ ��� ��ȿ�� ��� ��ư Ȱ��ȭ
		if (validPw && validId && !nameField.getText().equals("")) {
			confirm.setEnabled(true);
		} else {
			confirm.setEnabled(false);
		}
	}

	public void addAccount() {
		// TODO Auto-generated method stub
		name = nameField.getText();
		System.out.println(name);
		try {
			cLogin.addAccount(id, pw, name);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private class DocumentHandler implements DocumentListener {

		@Override
		public void changedUpdate(DocumentEvent e) {
			// TODO Auto-generated method stub
			changed();
		}

		@Override
		public void insertUpdate(DocumentEvent e) {
			// TODO Auto-generated method stub
			changed();
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			// TODO Auto-generated method stub
			changed();
		}

	}

	private class ActionHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			addAccount();
			dispose();
		}

	}
}
