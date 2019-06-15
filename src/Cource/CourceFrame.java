package Cource;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JFrame;

import Enrollment.CApply;
import Enrollment.CBasket;
import Enrollment.EnrollBtnPanel;
import Enrollment.EnrollmentPanel;

public class CourceFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	// UI
	public SelectionPanel selectionPanel;
	private EnrollBtnPanel enrollBtnPanel;
	private EnrollmentPanel enrollmentPanel;
	private ActionListener actionListener;
	private MouseListener mouseListener;

	private String id; // 아이디
	
	private CBasket cBasket;
	private CApply cApply;
	
	// 선택된 패널
	private boolean lecture = false;
	private boolean basket = false;
	private boolean apply = false;
	
	// 선택한 강좌 리스트
	Vector<ELecture> lectures;

	public CourceFrame(String id) {
		this.id = id;
		cBasket = new CBasket();
		cApply = new CApply();

		this.setTitle("명지대학교 수강신청 시스템 | " + this.id + "님 안녕하세요?");
		this.actionListener = new ActionHandler();
		this.mouseListener = new MouseHandler();

		this.selectionPanel = new SelectionPanel(mouseListener);
		this.selectionPanel.setPreferredSize(new Dimension(1000, 380));
		this.enrollBtnPanel = new EnrollBtnPanel(actionListener);
		this.enrollBtnPanel.setPreferredSize(new Dimension(1000, 50));
		this.enrollmentPanel = new EnrollmentPanel(id, cBasket, cApply, mouseListener);
		this.enrollmentPanel.setPreferredSize(new Dimension(1000, 420));

		this.add(selectionPanel);
		this.add(enrollBtnPanel);
		this.add(enrollmentPanel);

		this.setLayout(new FlowLayout());
		this.setSize(1200, 1000); // x,y축
		this.setMinimumSize(new Dimension(650, 0));
		this.setLocationRelativeTo(null);

	}

	public void addLectures(String opt) {
		try {
			if (opt.equals("basket")) {
				if(lecture) {
					lectures = this.selectionPanel.lecture.getSelectedLectures();
				} else if(apply) {
					lectures = this.enrollmentPanel.applyTable.getSelectedLectures();
					this.deleteLectures();
				}
				cBasket.add(lectures, id);
				Vector<ELecture> storedLectures = cBasket.show(id);
				this.enrollmentPanel.basketTable.refresh(storedLectures);
				
			} else if (opt.equals("apply")) {
				if(lecture) {
					lectures = this.selectionPanel.lecture.getSelectedLectures();
				} else if(basket) {
					lectures = this.enrollmentPanel.basketTable.getSelectedLectures();
					this.deleteLectures();
				}
				cApply.add(lectures, id);
				Vector<ELecture> storedLectures = cApply.show(id);
				this.enrollmentPanel.applyTable.refresh(storedLectures);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deleteLectures() {
		
		if(basket) {
			lectures = this.enrollmentPanel.basketTable.getSelectedLectures();
			try {
				cBasket.delete(lectures, id);
				Vector<ELecture> storedLectures = cBasket.show(id);
				this.enrollmentPanel.basketTable.refresh(storedLectures);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(apply) {
			lectures = this.enrollmentPanel.applyTable.getSelectedLectures();
			try {
				cApply.delete(lectures, id);
				Vector<ELecture> storedLectures = cApply.show(id);
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
			lecture = true; basket = false; apply = false;
		} else if (source == this.enrollmentPanel.basketTable) {
			this.selectionPanel.lecture.clearSelection();
			this.enrollmentPanel.applyTable.clearSelection();
			lecture = false; basket = true; apply = false;
		} else if (source == this.enrollmentPanel.applyTable) {
			this.selectionPanel.lecture.clearSelection();
			this.enrollmentPanel.basketTable.clearSelection();
			lecture = false; basket = false; apply = true;
		}

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
