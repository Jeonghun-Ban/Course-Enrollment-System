package Enrollment;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class EnrollmentPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	public BasketTable basketTable;
	public ApplyTable applyTable;
	
	public EnrollmentPanel(String id) {
		JScrollPane scrollpane = new JScrollPane();
		basketTable = new BasketTable(id);
		scrollpane.setViewportView(this.basketTable);
		scrollpane.setPreferredSize(new Dimension(600, 200));
		this.add(scrollpane);
		
		scrollpane = new JScrollPane();
		applyTable = new ApplyTable(id);
		scrollpane.setViewportView(this.applyTable);
		scrollpane.setPreferredSize(new Dimension(600, 200));
		this.add(scrollpane);
	}
	
}
