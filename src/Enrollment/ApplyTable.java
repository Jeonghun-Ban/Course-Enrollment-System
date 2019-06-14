package Enrollment;

import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Cource.ELecture;

public class ApplyTable extends JTable{
	private static final long serialVersionUID = 1L;

	// service
	Vector<ELecture> storedLectures;
	// model
	String[] header = { "강좌번호", "강좌명", "교수명", "학점", "시간" };
	private DefaultTableModel model;

	public ApplyTable(String id, CEnrollment cEnrollment, MouseListener mouseListener) {
		//mouseListener
		this.addMouseListener(mouseListener);
		
		// set model
		this.model = new DefaultTableModel(null, header) {
			// 수정 금지 기능
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false;
			}
		};

		this.setModel(model);
		
		try {
			storedLectures = cEnrollment.show("apply", id);
			
			for (ELecture lecture : storedLectures) {
				Vector<String> row = new Vector<>();
				row.add(Integer.toString(lecture.getNumber()));
				row.add(lecture.getName());
				row.add(lecture.getProfessor());
				row.add(Integer.toString(lecture.getCredit()));
				row.add(lecture.getTime());
				this.model.addRow(row);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void refresh(Vector<ELecture> lectures) {
		// TODO Auto-generated method stub
		
		this.model.setRowCount(0);
		
		for (ELecture lecture : lectures) {
			Vector<String> row = new Vector<>();
			row.add(Integer.toString(lecture.getNumber()));
			row.add(lecture.getName());
			row.add(lecture.getProfessor());
			row.add(Integer.toString(lecture.getCredit()));
			row.add(lecture.getTime());
			this.model.addRow(row);
		}
		
		this.updateUI();
	}
}
