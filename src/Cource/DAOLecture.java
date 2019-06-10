package Cource;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Vector;

public class DAOLecture {
	
	public Vector<ELecture> getItems(String filename) throws FileNotFoundException {
		Vector<ELecture> eItems = new Vector<ELecture>();
		
		// file open
		Scanner scanner = new Scanner(new FileReader(filename));
		
		while(scanner.hasNext()) {
			ELecture eItem = new ELecture();
			eItem.read(scanner);
			eItems.add(eItem);
		}
		// invalid userId or password
		scanner.close();
		return eItems;
	}
}
