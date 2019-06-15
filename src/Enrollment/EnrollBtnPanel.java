package Enrollment;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class EnrollBtnPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private JButton basketBtn, applyBtn, deleteBtn;

	public EnrollBtnPanel(ActionListener actionListener) {
		// 버튼 이벤트

		basketBtn = new JButton("장바구니담기");
		basketBtn.setActionCommand("basket");
		basketBtn.addActionListener(actionListener);
		this.add(basketBtn);

		applyBtn = new JButton("수강신청");
		applyBtn.setActionCommand("apply");
		applyBtn.addActionListener(actionListener);
		this.add(applyBtn);
		
		deleteBtn = new JButton("삭제");
		deleteBtn.setActionCommand("delete");
		deleteBtn.addActionListener(actionListener);
		this.add(deleteBtn);
	}

}
