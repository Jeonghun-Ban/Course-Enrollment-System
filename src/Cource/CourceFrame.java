package Cource;

import javax.swing.JFrame;

public class CourceFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	
	private SelectionPanel selectionPanel;
	
	public CourceFrame(String id) {
		super("�������б� ������û �ý��� | "+id+"�� �ȳ��ϼ���?");
		
		this.selectionPanel = new SelectionPanel();
		this.add(selectionPanel);
		
		this.setSize(650, 500); // x,y��
		this.setLocationRelativeTo(null);
	}
}
