package Account;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ELogin {
	private String userId;
	private String password;
	
	public void read(Scanner scanner) {
		this.userId = scanner.next();
		this.password = scanner.next();
	}

	public void authenticate(String userid, String password) throws FileNotFoundException, InvalidUserException {
		// TODO Auto-generated method stub
			Scanner scanner = new Scanner(new File("./data/login"));
			
			while(scanner.hasNext()) {
				this.read(scanner);
				if (this.userId.equals(userid)&&this.password.equals(password)) {
					scanner.close();
					return;
				}
			}
			// invalid userId or password
			scanner.close();
			throw new InvalidUserException(); // throw
	}

}
