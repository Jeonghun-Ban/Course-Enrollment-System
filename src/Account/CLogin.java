package Account;
import java.io.FileNotFoundException;

public class CLogin {

	private ELogin eLogin;
	public CLogin() {
		this.eLogin = new ELogin();
	}
	public void authenticate(String userid, String password) 
			throws FileNotFoundException, InvalidUserException {
		// TODO Auto-generated method stub
		this.eLogin.authenticate(userid, password);
		return;
		
	}
	
}
