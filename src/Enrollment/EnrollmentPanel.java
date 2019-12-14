package Enrollment;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Framework.ICApply;
import Framework.ICBasket;

public class EnrollmentPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	public BasketTable basketTable;
	public ApplyTable applyTable;
	public JLabel basket, apply;
	
	public EnrollmentPanel(String id, ICBasket iCBasket, ICApply iCApply, MouseListener mouseListener) {
		
		this.setLayout(new FlowLayout());
		
		basket = new JLabel("장바구니");
		this.add(basket);
		
		JScrollPane scrollpane = new JScrollPane();
		basketTable = new BasketTable(id, iCBasket, mouseListener);
		scrollpane.setViewportView(this.basketTable);
		scrollpane.setPreferredSize(new Dimension(600, 200));
		this.add(scrollpane);
		
		apply = new JLabel("수강신청");
		this.add(apply);
		
		scrollpane = new JScrollPane();
		applyTable = new ApplyTable(id, iCApply, mouseListener);
		scrollpane.setViewportView(this.applyTable);
		scrollpane.setPreferredSize(new Dimension(600, 200));
		this.add(scrollpane);
	}
	
}
