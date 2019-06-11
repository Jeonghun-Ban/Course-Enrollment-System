package Enrollment;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Vector;

import Cource.ELecture;

public class DAOInquire {

	public Vector<ELecture> storedLectures = new Vector<>();

	public Vector<ELecture> show(String fileName, String id) throws FileNotFoundException {
		Scanner scanner = new Scanner(new FileReader("data/"+ fileName + id));
		while (scanner.hasNext()) {
			ELecture storedLecture = new ELecture();
			storedLecture.read(scanner);
			storedLectures.add(storedLecture);
		}
		scanner.close();
		
		return storedLectures; 
	}

}
