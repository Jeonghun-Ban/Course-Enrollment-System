package Enrollment;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class EnrollBtnPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private JButton basketBtn, applyBtn, deleteBtn;

	public EnrollBtnPanel(ActionListener actionListener) {
		// ��ư �̺�Ʈ

		basketBtn = new JButton("��ٱ��ϴ��");
		basketBtn.setActionCommand("basket");
		basketBtn.addActionListener(actionListener);
		this.add(basketBtn);

		applyBtn = new JButton("������û");
		applyBtn.setActionCommand("apply");
		applyBtn.addActionListener(actionListener);
		this.add(applyBtn);
		
		deleteBtn = new JButton("����");
		deleteBtn.setActionCommand("delete");
		deleteBtn.addActionListener(actionListener);
		this.add(deleteBtn);
	}

}
