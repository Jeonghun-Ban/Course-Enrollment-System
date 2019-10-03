package Cource;

import java.awt.Color;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class LectureTable extends JTable {
	private static final long serialVersionUID = 1L;
	// service
	private CLecture cLecture;
	private Vector<ELecture> eLectures;
	// model
	String[] header = { "강좌번호", "강좌명", "교수명", "학점", "시간"};
	private DefaultTableModel model;

	public LectureTable(MouseListener mouseListener) {
		
		//mouseListener
		this.addMouseListener(mouseListener);
		// create service
		this.cLecture = new CLecture();
		// set model
		this.model = new DefaultTableModel(null, header) {
			// 수정 금지 기능
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false;
			}
		};
		
		this.getTableHeader().setReorderingAllowed(false); // 컬럼 이동 금지
		this.getTableHeader().setResizingAllowed(false); // 컬럼 사이즈 조정 금지
		
		this.setModel(model);
		this.setBackground(Color.LIGHT_GRAY);
	}

	public Vector<ELecture> getSelectedLectures() {
			// TODO Auto-generated method stub
			Vector<ELecture> selectedLectures = new Vector<>();
			int[] selectedIndex = this.getSelectedRows();
			for (int i=0; i<selectedIndex.length; i++) {
				ELecture lecture = this.eLectures.get(selectedIndex[i]);
				selectedLectures.add(lecture);
			}
			return selectedLectures;
		}

	public void refresh(String fileName) throws FileNotFoundException {
		this.eLectures = this.cLecture.getItems("data/" + fileName);

		this.model.setRowCount(0);

		for (ELecture eLecture : eLectures) {
			Vector<String> row = new Vector<>();
			row.add(Integer.toString(eLecture.getNumber()));
			row.add(eLecture.getName());
			row.add(eLecture.getProfessor());
			row.add(Integer.toString(eLecture.getCredit()));
			row.add(eLecture.getTime());
			this.model.addRow(row);
			this.setAutoCreateRowSorter(true);
		}

		this.updateUI();
	}

}
