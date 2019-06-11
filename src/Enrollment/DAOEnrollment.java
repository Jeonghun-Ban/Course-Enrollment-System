package Enrollment;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

import Cource.ELecture;

public class DAOEnrollment {

	public Vector<ELecture> storedLectures;

	public Vector<ELecture> add(String fileName, Vector<ELecture> lectures, String id) throws IOException {
		// TODO Auto-generated method stub

		// file write
		for (ELecture lecture : lectures) {	
			FileWriter fw = new FileWriter("data/" + fileName + id, true);
			fw.write(lecture.getNumber() + " " + lecture.getName() + " " + lecture.getProfessor() + " "
					+ lecture.getCredit() + " " + lecture.getTime() + "\r\n");
			fw.close();
		}
		
		// read storedLectures
		storedLectures = new Vector<>();
		Scanner scanner = new Scanner(new FileReader("data/"+ fileName + id));
		while (scanner.hasNext()) {
			ELecture storedLecture = new ELecture();
			storedLecture.read(scanner);
			storedLectures.add(storedLecture);
		}
		scanner.close();
		// vector return
		return storedLectures;
	}

}
