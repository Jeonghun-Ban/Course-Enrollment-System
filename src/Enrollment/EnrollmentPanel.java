package Enrollment;

import java.awt.Dimension;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class EnrollmentPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	public BasketTable basketTable;
	public ApplyTable applyTable;
	
	public EnrollmentPanel(String id, CBasket cBasket, CApply cApply, MouseListener mouseListener) {
		JScrollPane scrollpane = new JScrollPane();
		basketTable = new BasketTable(id, cBasket, mouseListener);
		scrollpane.setViewportView(this.basketTable);
		scrollpane.setPreferredSize(new Dimension(600, 200));
		this.add(scrollpane);
		
		scrollpane = new JScrollPane();
		applyTable = new ApplyTable(id, cApply, mouseListener);
		scrollpane.setViewportView(this.applyTable);
		scrollpane.setPreferredSize(new Dimension(600, 200));
		this.add(scrollpane);
	}
	
}
