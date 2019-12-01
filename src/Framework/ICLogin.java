package Framework;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

import Account.InvalidUserException;

public interface ICLogin extends Remote {
	public void authenticate(String userid, String password) throws FileNotFoundException, InvalidUserException, RemoteException;
	public boolean validId(String id) throws FileNotFoundException, RemoteException;
	public String getName() throws RemoteException;
	public void addAccount(String id, String pw, String name) throws IOException, RemoteException;
	
}
