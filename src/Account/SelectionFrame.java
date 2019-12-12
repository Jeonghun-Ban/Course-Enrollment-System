package Account;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Cource.DirectoryList;

public class SelectionFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private DirectoryList campus;
	private DirectoryList college;
	private DirectoryList department;
	
	private ListSelectionListener listSelectionListener;
	private ActionListener actionListener;
	
	String major = null;
	int credit; // 수강가능학점

	public SelectionFrame(MouseListener mouseListener) {

		this.setSize(400, 545);
		this.setLayout(new FlowLayout());
		
		this.listSelectionListener = new ListSelectionHandler();
		this.actionListener = new ActionHandler();
		
		JScrollPane scrollpane = new JScrollPane();
		this.campus = new DirectoryList(this.listSelectionListener);
		scrollpane.setViewportView(this.campus);
		scrollpane.setPreferredSize(new Dimension(350,150));
		this.add(scrollpane);

		scrollpane = new JScrollPane();
		this.college = new DirectoryList(this.listSelectionListener);
		scrollpane.setViewportView(this.college);
		scrollpane.setPreferredSize(new Dimension(350,150));
		this.add(scrollpane);
		
		scrollpane = new JScrollPane();
		this.department = new DirectoryList(this.listSelectionListener);
		this.department.addMouseListener(mouseListener);
		scrollpane.setViewportView(this.department);
		scrollpane.setPreferredSize(new Dimension(350,150));
		this.add(scrollpane);
		
		JButton submitBtn = new JButton("확인");
		submitBtn.setActionCommand("submit");
		submitBtn.addActionListener(actionListener);
		this.add(submitBtn);

		this.refresh(null);

	}

	private void refresh(Object source) {
		try {
			if (source == null) {
				major = this.campus.getMajor("root", false);
				credit=18; // 용인일 경우 18학점
				major = this.college.getMajor(major, true); // 교양 항목을 리스트에서 제거
				major = this.department.getMajor(major, false);
			} else if (source == this.campus) {
				major = this.campus.getSelectedFileName();
				if(major.equals("yongin")) {
					credit=18; // 용인일 경우 18학점
				}else {
					credit=17;
				}
				major = this.college.getMajor(major, true);
				major = this.department.getMajor(major, false);
			} else if (source == this.college) {
				major = this.college.getSelectedFileName();
				major = this.department.getMajor(major, false);
			} else if (source == this.department) {
				major = this.department.getSelectedFileName();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
		}

	}
	
	public String getMajor() {
		return major;
	}
	
	public int getCredit() {
		if(major.equals("digitalContents")||major.equals("software")) {
			credit=18; // 인문캠에서 18학점인 유일한 경우
		}
		return credit;
	}
	
	private class ActionHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getActionCommand() == "submit") {
				dispose();
			}
		}

	}

	
	private class ListSelectionHandler implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent event) {
			// TODO Auto-generated method stub
			refresh(event.getSource());
		}

	}

}
