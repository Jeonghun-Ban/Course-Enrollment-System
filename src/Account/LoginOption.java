package Account;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class LoginOption {
	
	private String[] option = new String[3];;
	
	public void set(String opt, String id, String name) throws IOException {
		// TODO Auto-generated method stub
		FileWriter fw = new FileWriter("loginOption", false);
		fw.write(opt+" "+id+" "+name);
		fw.close();
	}
	
	public String[] get() throws FileNotFoundException {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(new FileReader("loginOption"));
		
		while(scanner.hasNext()) {
			option[0] = scanner.next(); // 로그인 옵션
			option[1] = scanner.next(); // id
			option[2] = scanner.next(); // names
		}
		scanner.close();
		return option;
	}
}
