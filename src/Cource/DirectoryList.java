package Cource;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Vector;

import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;

import Framework.ICDirectory;
import Framework.Launcher;
import main.Connector;

public class DirectoryList extends JList<String> {
    private static final long serialVersionUID = 1L;
    private static final Class<ICDirectory> iCDirectoryCLASS = ICDirectory.class;
    private static Method getItemsMethod;

    private Vector<EDirectory> eDirectories;
    Vector<String> listData;

    static {
        try {
            getItemsMethod = iCDirectoryCLASS.getMethod("getItems", String.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public DirectoryList(ListSelectionListener listSelectionListener) {

        this.listData = new Vector<>();
        this.setListData(this.listData);

        this.addListSelectionListener(listSelectionListener);
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    public String getSelectedFileName() {
        // TODO Auto-generated method stub
        int selectedIndex = this.getSelectedIndex();
        return this.eDirectories.get(selectedIndex).getHyperLink();
    }

    public String refresh(String fileName) {
        try {
            this.eDirectories = (Vector<EDirectory>) Connector.invoke(new Launcher(iCDirectoryCLASS.getSimpleName(), getItemsMethod.getName(), getItemsMethod.getParameterTypes(), new Object[]{fileName}));
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        this.listData.clear();

        for (EDirectory eDirectory : eDirectories) {
            this.listData.add(eDirectory.getName());
        }

        this.setSelectedIndex(0);
        this.updateUI();

        return this.eDirectories.get(0).getHyperLink();
    }

    public String getMajor(String fileName, boolean remove) throws FileNotFoundException {
        try {
            this.eDirectories = (Vector<EDirectory>) Connector.invoke(new Launcher(iCDirectoryCLASS.getSimpleName(), getItemsMethod.getName(),
                    getItemsMethod.getParameterTypes(), new Object[]{fileName}));
            if (remove) {
                this.eDirectories.remove(0);
            }
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        this.listData.clear();

        for (EDirectory eDirectory : eDirectories) {
            this.listData.add(eDirectory.getName());
        }

        this.setSelectedIndex(0);
        this.updateUI();

        return this.eDirectories.get(0).getHyperLink();
    }
}
