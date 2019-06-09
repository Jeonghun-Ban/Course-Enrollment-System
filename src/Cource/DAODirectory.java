package Cource;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Vector;

public class DAODirectory {
	
	// file open
	public Vector<EDirectory> getItems(String filename) throws FileNotFoundException {
		Vector<EDirectory> eDirectories = new Vector<EDirectory>();
		
		// file open
		Scanner scanner = new Scanner(new FileReader(filename));
		
		while(scanner.hasNext()) {
			EDirectory eDirectory = new EDirectory();
			eDirectory.read(scanner);
			eDirectories.add(eDirectory);
		}
		// invalid userId or password
		scanner.close();
		return eDirectories;
	}

}
