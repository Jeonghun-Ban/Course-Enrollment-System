package Account;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;

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

import Framework.ICLogin;

public class RegisterFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private JTextField idField, nameField, majorField;
	private JPasswordField pwField, rePwField;
	private JButton majorBtn, submit;
	private JLabel idLabel, pwLabel, rePwLabel, nameLabel, majorLabel, alert;
	private JPanel registerPanel;

	private DocumentListener documentListener;
	private ActionListener actionListener;
	private MouseListener mouseListener;

	private ICLogin iCLogin;
	String message = "아래 폼을 모두 입력해주세요.";
	String id, pw, rePw, name, major;
	int credit;

	private boolean validId, validPw;
	
	private SelectionFrame selectionFrame;

	public RegisterFrame(ICLogin iCLogin) {

		this.iCLogin = iCLogin;

		this.setTitle("회원가입");
		this.setSize(400, 545);
		this.setLayout(new BorderLayout());

		Font font = new Font("고딕", Font.BOLD, 15);
		// 아이콘 이미지
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
		mouseListener = new MouseHandler();
		
		alert = new JLabel(message);
		alert.setHorizontalAlignment(SwingConstants.CENTER);
		alert.setFont(font);

		registerPanel = new JPanel();
		registerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		registerPanel.setLayout(new GridLayout(12, 1));

		// id 패널
		idLabel = new JLabel("아이디");
		idField = new JTextField();
		idField.getDocument().addDocumentListener(documentListener);

		// pw
		pwLabel = new JLabel("비밀번호");
		pwField = new JPasswordField();
		pwField.getDocument().addDocumentListener(documentListener);
		rePwLabel = new JLabel("비밀번호 확인");
		rePwField = new JPasswordField();
		rePwField.getDocument().addDocumentListener(documentListener);

		// name
		nameLabel = new JLabel("이름");
		nameField = new JTextField();
		nameField.getDocument().addDocumentListener(documentListener);

		// major
		majorLabel = new JLabel("전공");
		majorField = new JTextField();
		majorField.getDocument().addDocumentListener(documentListener);
		majorField.setEditable(false);
		majorField.registerKeyboardAction(this.actionListener, "submit", KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),
				JComponent.WHEN_FOCUSED);
				
		// add component in registerPanel
		registerPanel.add(idLabel);
		registerPanel.add(idField);
		registerPanel.add(pwLabel);
		registerPanel.add(pwField);
		registerPanel.add(rePwLabel);
		registerPanel.add(rePwField);
		registerPanel.add(nameLabel);
		registerPanel.add(nameField);
		registerPanel.add(majorLabel);
		registerPanel.add(majorField);
		
		majorBtn = new JButton("전공 선택");
		majorBtn.setActionCommand("major");
		majorBtn.addActionListener(actionListener);
		
		registerPanel.add(majorBtn);

		submit = new JButton("제출");
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
		
		major = majorField.getText();
		
		// 비밀번호 유효성 체크
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
				this.alert.setText("비밀번호를 입력해주세요");
				this.alert.setForeground(Color.RED);
			} else if (pw.equals(rePw) && !rePw.equals("")) {
				validPw = true;
				this.alert.setText("비밀번호가 확인되었습니다.");
				this.alert.setForeground(Color.BLACK);
			} else if (!pw.equals(rePw) && !rePw.equals("")) {
				validPw = false;
				this.alert.setText("비밀번호가 일치하지 않습니다.");
				this.alert.setForeground(Color.RED);
			} else {
				this.alert.setText(message);
				this.alert.setForeground(Color.BLACK);
			}
		} // 아이디 유효성 체크
		else if (document == idField.getDocument()) {
			try {
				id = idField.getText();
				validId = this.iCLogin.validId(id);
				if (!validId && !id.equals("")) {
					this.alert.setText("이미 존재하는 아이디입니다.");
					this.alert.setForeground(Color.RED);
				} else if (validId && !id.equals("")) {
					this.alert.setText("사용 가능한 아이디입니다.");
					this.alert.setForeground(Color.BLACK);
				} else {
					this.alert.setText(message);
					this.alert.setForeground(Color.BLACK);
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			this.alert.setText(message);
			this.alert.setForeground(Color.BLACK);
		}

		// 아이디 비밀번호 모두 유효한 경우 버튼 활성화
		if (validPw && validId && !nameField.getText().equals("") && !majorField.getText().equals("")) {
			submit.setEnabled(true);
		} else {
			submit.setEnabled(false);
		}
	}

	public void addAccount() {
		// TODO Auto-generated method stub
		name = nameField.getText();
		// 임시로 수강가능학점 18로 지정
		credit = 18;
		try {
			iCLogin.addAccount(id, pw, name, major, credit);
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
			if (e.getActionCommand() == "major") {
				selectionFrame = new SelectionFrame(mouseListener);
				selectionFrame.setVisible(true);
				Point point = getLocation();
				selectionFrame.setLocation(point.x, point.y);
			} else if (e.getActionCommand() == "submit") {
				addAccount();
				dispose();
			}  

		}

	}
	
	// 전공 선택 이벤트
	private class MouseHandler implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			major = selectionFrame.getMajor();
			majorField.setText(major);
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}
}
