package Cource;

import javax.swing.JFrame;

public class CourceFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	
	private SelectionPanel selectionPanel;
	
	public CourceFrame() {
		super("�������б� ���� ���� ������");
		this.selectionPanel = new SelectionPanel();
		this.add(selectionPanel);
		
		this.setSize(800, 600); // x,y��
		this.setLocationRelativeTo(null);
	}
}
