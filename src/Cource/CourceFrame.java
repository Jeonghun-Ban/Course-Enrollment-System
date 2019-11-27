package Cource;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import Account.LoginFrame;
import Enrollment.EnrollBtnPanel;
import Enrollment.EnrollmentPanel;
import Framework.ICApply;
import Framework.ICBasket;
import Framework.ICLogin;
import main.Constant;

public class CourceFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	// UI
	private GreetPanel greetPanel;
	public SelectionPanel selectionPanel;
	private EnrollBtnPanel enrollBtnPanel;
	private EnrollmentPanel enrollmentPanel;
	private ActionListener actionListener;
	private MouseListener mouseListener;

	private String id; // 아이디

	private ICBasket iCBasket;
	private ICApply iCApply;

	// 선택된 패널
	private boolean lecture = false;
	private boolean basket = false;
	private boolean apply = false;

	// 선택한 강좌 리스트
	Vector<ELecture> lectures;
	Vector<ELecture> storedLectures;

	public CourceFrame(String id, String name) {

		this.id = id;

		try {
			iCBasket = (ICBasket) Constant.registry.lookup("iCBasket");
			iCApply = (ICApply) Constant.registry.lookup("iCApply");
		} catch (RemoteException | NotBoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		this.setTitle("명지대학교 수강신청 시스템");
		// 아이콘 이미지
		File icon = new File("image/icon.gif");
		try {
			Image img = ImageIO.read(icon);
			setIconImage(img);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		this.actionListener = new ActionHandler();
		this.mouseListener = new MouseHandler();

		this.greetPanel = new GreetPanel(name, actionListener);
		this.greetPanel.setPreferredSize(new Dimension(1000, 50));
		this.selectionPanel = new SelectionPanel(mouseListener);
		this.selectionPanel.setPreferredSize(new Dimension(1000, 370));
		this.enrollmentPanel = new EnrollmentPanel(id, iCBasket, iCApply, mouseListener);
		this.enrollmentPanel.setPreferredSize(new Dimension(600, 460));
		this.enrollBtnPanel = new EnrollBtnPanel(actionListener);
		this.enrollBtnPanel.setPreferredSize(new Dimension(1000, 50));

		this.setLayout(new FlowLayout());
		
		this.add(greetPanel);
		this.add(selectionPanel);
		this.add(enrollmentPanel);
		this.add(enrollBtnPanel);

		
		this.setSize(1200, 1000); // x,y축
		this.setMinimumSize(new Dimension(650, 0));
		this.setLocationRelativeTo(null);

	}

	public void addLectures(String opt) {
		try {
			if (opt.equals("basket")) {
				if (lecture) {
					lectures = this.selectionPanel.lecture.getSelectedLectures();
				} else if (apply) {
					lectures = this.enrollmentPanel.applyTable.getSelectedLectures();
					this.deleteLectures();
				}
				Vector<ELecture> applyLectures = iCApply.show(id);
				iCBasket.add(lectures, applyLectures, id);
				storedLectures = iCBasket.show(id);
				this.enrollmentPanel.basketTable.refresh(storedLectures);

			} else if (opt.equals("apply")) {
				if (lecture) {
					lectures = this.selectionPanel.lecture.getSelectedLectures();
				} else if (basket) {
					lectures = this.enrollmentPanel.basketTable.getSelectedLectures();
					this.deleteLectures();
				}
				Vector<ELecture> basketLectures = iCBasket.show(id);
				iCApply.add(lectures, basketLectures, id);
				storedLectures = iCApply.show(id);
				this.enrollmentPanel.applyTable.refresh(storedLectures);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deleteLectures() {

		if (basket) {
			lectures = this.enrollmentPanel.basketTable.getSelectedLectures();
			try {
				iCBasket.delete(lectures, id);
				storedLectures = iCBasket.show(id);
				this.enrollmentPanel.basketTable.refresh(storedLectures);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (apply) {
			lectures = this.enrollmentPanel.applyTable.getSelectedLectures();
			try {
				iCApply.delete(lectures, id);
				storedLectures = iCApply.show(id);
				this.enrollmentPanel.applyTable.refresh(storedLectures);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private void setFocus(Object source) {
		if (source == this.selectionPanel.lecture) {
			this.enrollmentPanel.basketTable.clearSelection();
			this.enrollmentPanel.applyTable.clearSelection();
			lecture = true;
			basket = false;
			apply = false;
		} else if (source == this.enrollmentPanel.basketTable) {
			this.selectionPanel.lecture.clearSelection();
			this.enrollmentPanel.applyTable.clearSelection();
			lecture = false;
			basket = true;
			apply = false;
		} else if (source == this.enrollmentPanel.applyTable) {
			this.selectionPanel.lecture.clearSelection();
			this.enrollmentPanel.basketTable.clearSelection();
			lecture = false;
			basket = false;
			apply = true;
		}

	}

	public void logout() {
		ICLogin iCLogin = null;
		try {
			iCLogin = (ICLogin) Constant.registry.lookup("iCLogin");
		} catch (AccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NotBoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		LoginFrame loginFrame = new LoginFrame(iCLogin);
		loginFrame.setVisible(true);
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			iCLogin.setOption("null", "null", "null");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dispose();
	}

	private class ActionHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getActionCommand() == "basket") {
				addLectures("basket");
			} else if (e.getActionCommand() == "apply") {
				addLectures("apply");
			} else if (e.getActionCommand() == "delete") {
				deleteLectures();
			} else if (e.getActionCommand() == "logout") {
				logout();
			}
		}

	}

	private class MouseHandler implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			setFocus(e.getSource());
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
			setFocus(e.getSource());
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}
}
