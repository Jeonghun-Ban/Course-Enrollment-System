package Cource;

import javax.swing.JFrame;

public class CourceFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	
	private SelectionPanel selectionPanel;
	
	public CourceFrame() {
		super("명지대학교 강좌 선택 페이지");
		this.selectionPanel = new SelectionPanel();
		this.add(selectionPanel);
		
		this.setSize(800, 600); // x,y축
		this.setLocationRelativeTo(null);
	}
}
