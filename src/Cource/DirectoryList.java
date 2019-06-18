package Cource;

import java.io.FileNotFoundException;
import java.util.Vector;

import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;

public class DirectoryList extends JList<String> {
	private static final long serialVersionUID = 1L;

	private CDirectory cDirectory;
	private Vector<EDirectory> eDirectories;
	Vector<String> listData;
	
	public DirectoryList(ListSelectionListener listSelectionListener) {
		this.cDirectory = new CDirectory();
		
		this.listData= new Vector<String>();
		this.setListData(this.listData);
		
		this.addListSelectionListener(listSelectionListener);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
	}
	public String getSelectedFileName() {
		// TODO Auto-generated method stub
		int selectedIndex = this.getSelectedIndex();
		return this.eDirectories.get(selectedIndex).getHyperLink();
	}
	
	public String refresh(String fileName) throws FileNotFoundException {
		this.eDirectories = this.cDirectory.getItems("data/"+fileName);
		
		this.listData.clear();
		for(EDirectory eDirectory: eDirectories) {
			this.listData.add(eDirectory.getName());
		}
		
		this.setSelectedIndex(0);
		this.updateUI();
		
		return this.eDirectories.get(0).getHyperLink();
	}
	
}
