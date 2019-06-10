package Cource;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class SelectionPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private DirectoryList campus;
	private DirectoryList college;
	private DirectoryList department;
	private LectureTable lecture;
	private ListSelectionListener listSelectionListener;
	private ActionListener actionListener;

	private JButton basketBtn, applyBtn;

	public SelectionPanel() {
		this.listSelectionListener = new ListSelectionHandler();

		JScrollPane scrollpane = new JScrollPane();
		this.campus = new DirectoryList(this.listSelectionListener);
		scrollpane.setViewportView(this.campus);
		this.add(scrollpane);

		scrollpane = new JScrollPane();
		this.college = new DirectoryList(this.listSelectionListener);
		scrollpane.setViewportView(this.college);
		this.add(scrollpane);

		scrollpane = new JScrollPane();
		this.department = new DirectoryList(this.listSelectionListener);
		scrollpane.setViewportView(this.department);
		this.add(scrollpane);

		scrollpane = new JScrollPane();
		this.lecture = new LectureTable();
		scrollpane.setViewportView(this.lecture);
		scrollpane.setPreferredSize(new Dimension(600, 200));
		this.add(scrollpane);

		// 버튼 이벤트
		this.actionListener = new ActionHandler();

		basketBtn = new JButton("장바구니담기");
		basketBtn.setActionCommand("basket");
		basketBtn.addActionListener(actionListener);
		this.add(basketBtn);

		applyBtn = new JButton("수강신청");
		applyBtn.setActionCommand("apply");
		applyBtn.addActionListener(actionListener);
		this.add(applyBtn);

		this.refresh(null);

	}

	private void refresh(Object source) {
		try {
			if (source == null) {
				String fileName = this.campus.refresh("root");
				fileName = this.college.refresh(fileName);
				fileName = this.department.refresh(fileName);
				this.lecture.refresh(fileName);
			} else if (source == this.campus) {
				String fileName = this.campus.getSelectedFileName();
				fileName = this.college.refresh(fileName);
				fileName = this.department.refresh(fileName);
				this.lecture.refresh(fileName);
			} else if (source == this.college) {
				String fileName = this.college.getSelectedFileName();
				fileName = this.department.refresh(fileName);
				this.lecture.refresh(fileName);
			} else if (source == this.department) {
				String fileName = this.department.getSelectedFileName();
				this.lecture.refresh(fileName);
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Vector<ELecture> getItem() {
		return this.lecture.getSelectedLectures();
	}
	
	private class ListSelectionHandler implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent event) {
			// TODO Auto-generated method stub
			refresh(event.getSource());
		}

	}
	
	private class ActionHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getActionCommand() == "basket") {
				
			} else if (e.getActionCommand() == "apply") {
				
			}
		}
	
	}
}
