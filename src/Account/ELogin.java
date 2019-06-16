package Account;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ELogin {
	private String userId;
	private String password;
	private String name;
	
	private String[] option;
	
	public void read(Scanner scanner) {
		this.userId = scanner.next();
		this.password = scanner.next();
		this.name = scanner.next();
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

	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	public void setOption(String opt, String id, String name) throws IOException {
		// TODO Auto-generated method stub
		FileWriter fw = new FileWriter("data/loginOption", false);
		fw.write(opt+" "+id+" "+name);
		fw.close();
	}
	
	public String[] getOption() throws FileNotFoundException {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(new FileReader("data/loginOption"));
		option = new String[3];
		
		while(scanner.hasNext()) {
			option[0] = scanner.next(); // �α��� �ɼ�
			option[1] = scanner.next(); // id
			option[2] = scanner.next(); // names
		}
		scanner.close();
		return option;
	}
}
