package Account;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;

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
	
	String major = null;

	public SelectionFrame(MouseListener mouseListener) {

		this.setSize(400, 545);
		this.setLayout(new FlowLayout());
		
		this.listSelectionListener = new ListSelectionHandler();
		
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

		this.refresh(null);

	}

	private void refresh(Object source) {
		try {
			if (source == null) {
				major = this.campus.getMajor("root", false);
				major = this.college.getMajor(major, true); // 교양 항목을 리스트에서 제거
				major = this.department.getMajor(major, false);
			} else if (source == this.campus) {
				major = this.campus.getSelectedFileName();
				major = this.college.getMajor(major, false);
				major = this.department.getMajor(major, true);
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
	
	private class ListSelectionHandler implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent event) {
			// TODO Auto-generated method stub
			refresh(event.getSource());
		}

	}

}
