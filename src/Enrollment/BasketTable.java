package Enrollment;

import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Cource.ELecture;

public class BasketTable extends JTable {
	private static final long serialVersionUID = 1L;

	// model
	String[] header = { "강좌번호", "강좌명", "교수명", "학점", "시간" };
	private DefaultTableModel model;

	public BasketTable() {
		// set model
		this.model = new DefaultTableModel(null, header) {
			// 수정 금지 기능
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false;
			}
		};

		this.setModel(model);

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
