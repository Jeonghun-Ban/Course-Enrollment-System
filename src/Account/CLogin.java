package Account;
import java.io.FileNotFoundException;
import java.io.IOException;

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
	
	public boolean validId(String id) throws FileNotFoundException {
		return eLogin.validId(id);
	}
	
	public String getName() {
		return eLogin.getName();
	}
	
	public void setOption(String opt, String id, String name) throws IOException {
		eLogin.setOption(opt, id, name);
	}
	
	public String[] getOption() throws FileNotFoundException {
		return eLogin.getOption();
	}
	
	public void addAccount(String id, String pw, String name) throws IOException {
		eLogin.addAccount(id, pw, name);
	}
	
}
