package Cource;

import java.io.FileNotFoundException;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Vector;

import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;

import Framework.ICDirectory;
import main.Constant;

public class DirectoryList extends JList<String> {
	private static final long serialVersionUID = 1L;

	private ICDirectory iCDirectory;
	private Vector<EDirectory> eDirectories;
	Vector<String> listData;
	
	public DirectoryList(ListSelectionListener listSelectionListener) {
		try {
			this.iCDirectory = (ICDirectory) Constant.registry.lookup("iCDirectory");
		} catch (AccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
		try {
			this.eDirectories = this.iCDirectory.getItems(fileName);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.listData.clear();
		for(EDirectory eDirectory: eDirectories) {
			this.listData.add(eDirectory.getName());
		}
		
		this.setSelectedIndex(0);
		this.updateUI();
		
		return this.eDirectories.get(0).getHyperLink();
	}
	
}
