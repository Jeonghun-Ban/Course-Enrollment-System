package Enrollment;

import java.awt.Color;
import java.awt.event.MouseListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Cource.ELecture;
import Framework.ICBasket;
import Framework.Launcher;
import main.Connector;
import main.CurrentUser;

public class BasketTable extends JTable {
	private static final long serialVersionUID = 1L;
	private static final Class<ICBasket> icBasketClass = ICBasket.class;
	private static Method basketShow;

	// service

	Vector<ELecture> lectures;
	// model
	String[] header = { "강좌번호", "강좌명", "교수명", "학점", "시간" };
	private DefaultTableModel model;

	static {
		try {
			basketShow = icBasketClass.getMethod("show", String.class);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}

	public BasketTable(String id, MouseListener mouseListener) {
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

		this.getTableHeader().setReorderingAllowed(false); // 컬럼 이동 금지
		this.getTableHeader().setResizingAllowed(false); // 컬럼 사이즈 조정 금지

		this.setModel(model);
		this.setBackground(Color.LIGHT_GRAY);

		this.setAutoCreateRowSorter(true);

		try {
			lectures = (Vector<ELecture>) Connector.invoke(new Launcher(icBasketClass.getSimpleName(), basketShow.getName(), basketShow.getParameterTypes(), new Object[]{id}));
			this.refresh(lectures);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	public Vector<ELecture> getSelectedLectures() {
		// TODO Auto-generated method stub
		Vector<ELecture> selectedLectures = new Vector<>();
		int[] selectedIndex = this.getSelectedRows();
		for (int i=0; i<selectedIndex.length; i++) {
			ELecture lecture = this.lectures.get(selectedIndex[i]);
			selectedLectures.add(lecture);
		}
		return selectedLectures;
	}
	
	public void refresh(Vector<ELecture> lectures) {
		// TODO Auto-generated method stub
		this.lectures = lectures;
		this.model.setRowCount(0);
		
		CurrentUser.basket = 0;
		
		for (ELecture lecture : lectures) {
			Vector<String> row = new Vector<>();
			row.add(Integer.toString(lecture.getNumber()));
			row.add(lecture.getName());
			row.add(lecture.getProfessor());
			row.add(Integer.toString(lecture.getCredit()));
			row.add(lecture.getTime());
			this.model.addRow(row);
			
			CurrentUser.basket+=lecture.getCredit();
		}
		
		this.updateUI();
	}
}
