package Enrollment;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Vector;

import Cource.ELecture;

public class DAOBasket {

	public Vector<ELecture> storedLectures = new Vector<>();

	public Vector<ELecture> show(String id) throws FileNotFoundException {
		Scanner scanner = new Scanner(new FileReader("data/basket" + id));
		while (scanner.hasNext()) {
			ELecture storedLecture = new ELecture();
			storedLecture.read(scanner);
			storedLectures.add(storedLecture);
		}
		scanner.close();
		
		return storedLectures; 
	}

}
