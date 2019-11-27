package Framework;

import java.io.FileNotFoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

import Cource.ELecture;

public interface ICLecture extends Remote{
	public Vector<ELecture> getItems(String filename) throws FileNotFoundException, RemoteException;
}
