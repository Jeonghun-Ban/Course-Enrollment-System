package Framework;
import java.io.FileNotFoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

import Cource.EDirectory;

public interface ICDirectory extends Remote {
	public Vector<EDirectory> getItems(String filename) throws FileNotFoundException, RemoteException;
}
