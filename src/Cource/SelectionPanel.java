package Cource;

import java.awt.Dimension;
import java.io.FileNotFoundException;

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
	
	public SelectionPanel() {
		this.listSelectionListener = new ListSelectionHandler();
		
		JScrollPane scrollpane = new JScrollPane();
		this.campus = new DirectoryList(this.listSelectionListener);
		scrollpane.setViewportView(this.campus);
		scrollpane.setPreferredSize(new Dimension(150,150));
		this.add(scrollpane);
		
		scrollpane = new JScrollPane();
		this.college = new DirectoryList(this.listSelectionListener);
		scrollpane.setViewportView(this.college);
		scrollpane.setPreferredSize(new Dimension(150,150));
		this.add(scrollpane);
		
		scrollpane = new JScrollPane();
		this.department = new DirectoryList(this.listSelectionListener);
		scrollpane.setViewportView(this.department);
		scrollpane.setPreferredSize(new Dimension(150,150));
		this.add(scrollpane);
		
		this.lecture = new LectureTable();
		this.add(this.lecture);
		
		this.refresh(null);
		
	}
	
	private void refresh(Object source) {
		try {
		    if (source == null) {
		    	String fileName = this.campus.refresh("root");
		    	fileName = this.college.refresh(fileName);
				fileName = this.department.refresh(fileName);
		    } else if(source == this.campus) {
				String fileName = this.campus.getSelectedFileName();
				fileName = this.college.refresh(fileName);
				fileName = this.department.refresh(fileName);
			} else if(source == this.college) {
				String fileName = this.college.getSelectedFileName();
				fileName = this.department.refresh(fileName);
			} else if(source == this.department) {
				String fileName = this.department.getSelectedFileName();
	
			}
//				else lecture table
			
		}catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	}
private class ListSelectionHandler implements ListSelectionListener{
		
		@Override
		public void valueChanged(ListSelectionEvent event) {
			// TODO Auto-generated method stub
			refresh(event.getSource());
		}
		
	}
}
