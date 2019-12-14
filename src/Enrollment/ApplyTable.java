package Enrollment;

import java.awt.Color;
import java.awt.event.MouseListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Cource.ELecture;
import Framework.ICApply;
import Framework.Launcher;
import main.Connector;
import main.CurrentUser;

public class ApplyTable extends JTable {
    private static final long serialVersionUID = 1L;
    private static final Class<ICApply> icApplyClass = ICApply.class;
    private static Method applyShow;

    // service

    Vector<ELecture> lectures;
    // model
    String[] header = {"강좌번호", "강좌명", "교수명", "학점", "시간"};
    private DefaultTableModel model;

    static {
        try {
            applyShow = icApplyClass.getMethod("show", String.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public ApplyTable(String id, MouseListener mouseListener) {
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
            lectures = (Vector<ELecture>) Connector.invoke(new Launcher(icApplyClass.getSimpleName(), applyShow.getName(), applyShow.getParameterTypes(), new Object[]{id}));
			this.refresh(lectures);

        } catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

    public Vector<ELecture> getSelectedLectures() {
        // TODO Auto-generated method stub
        Vector<ELecture> selectedLectures = new Vector<>();
        int[] selectedIndex = this.getSelectedRows();
        for (int index : selectedIndex) {
            ELecture lecture = this.lectures.get(index);
            selectedLectures.add(lecture);
        }
        return selectedLectures;
    }

    public void refresh(Vector<ELecture> lectures) {
        // TODO Auto-generated method stub
        this.lectures = lectures;
        this.model.setRowCount(0);
        
        CurrentUser.apply = 0;

        for (ELecture lecture : lectures) {
            Vector<String> row = new Vector<>();
            row.add(Integer.toString(lecture.getNumber()));
            row.add(lecture.getName());
            row.add(lecture.getProfessor());
            row.add(Integer.toString(lecture.getCredit()));
            row.add(lecture.getTime());
            this.model.addRow(row);
            
            CurrentUser.apply+=lecture.getCredit();
        }

        this.updateUI();
    }
}
