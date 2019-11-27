package Framework;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

import Cource.ELecture;

public interface ICBasket extends Remote {
	public void add(Vector<ELecture> lectures, Vector<ELecture> applyLectures, String id) throws IOException, RemoteException;
	public Vector<ELecture> show(String id) throws FileNotFoundException, RemoteException;
	public void delete(Vector<ELecture> lectures, String id) throws IOException, RemoteException;
}
