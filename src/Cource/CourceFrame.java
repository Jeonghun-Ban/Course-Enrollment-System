package Cource;

import javax.swing.JFrame;

public class CourceFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	
	private SelectionPanel selectionPanel;
	
	public CourceFrame(String id) {
		super("명지대학교 수강신청 시스템 | "+id+"님 안녕하세요?");
		
		this.selectionPanel = new SelectionPanel();
		this.add(selectionPanel);
		
		this.setSize(650, 500); // x,y축
		this.setLocationRelativeTo(null);
	}
}
