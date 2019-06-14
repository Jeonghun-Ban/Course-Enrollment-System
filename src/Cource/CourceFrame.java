package Cource;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JFrame;

import Enrollment.CEnrollment;
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
	private CEnrollment cEnrollment;

	public CourceFrame(String id) {
		this.id = id;
		cEnrollment = new CEnrollment();

		this.setTitle("명지대학교 수강신청 시스템 | " + this.id + "님 안녕하세요?");
		this.actionListener = new ActionHandler();
		this.mouseListener = new MouseHandler();

		this.selectionPanel = new SelectionPanel(mouseListener);
		this.selectionPanel.setSize(650, 500);
		this.enrollBtnPanel = new EnrollBtnPanel(actionListener);
		this.enrollBtnPanel.setSize(100, 100);
		this.enrollmentPanel = new EnrollmentPanel(id, cEnrollment, mouseListener);
		this.enrollmentPanel.setSize(650, 500);

		this.add(selectionPanel);
		this.add(enrollBtnPanel);
		this.add(enrollmentPanel);

		this.setLayout(new FlowLayout());
		this.setSize(1200, 1000); // x,y축
		this.setLocationRelativeTo(null);

	}

	public void addLectures(String opt) {
		Vector<ELecture> lectures = this.selectionPanel.lecture.getSelectedLectures();
		try {
			if (opt.equals("basket")) {
				cEnrollment.add("basket", lectures, id);
				Vector<ELecture> storedLectures = cEnrollment.show("basket", id);
				this.enrollmentPanel.basketTable.refresh(storedLectures);
			} else if (opt.equals("apply")) {
				cEnrollment.add("apply", lectures, id);
				Vector<ELecture> storedLectures = cEnrollment.show("apply", id);
				this.enrollmentPanel.applyTable.refresh(storedLectures);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void setFocus(Object source) {
		if( source == this.selectionPanel.lecture) {
			this.enrollmentPanel.basketTable.clearSelection();
			this.enrollmentPanel.applyTable.clearSelection();
		} else if (source == this.enrollmentPanel.basketTable) {
			this.selectionPanel.lecture.clearSelection();
			this.enrollmentPanel.applyTable.clearSelection();
		} else if(source == this.enrollmentPanel.applyTable){
			this.selectionPanel.lecture.clearSelection();
			this.enrollmentPanel.basketTable.clearSelection();
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
