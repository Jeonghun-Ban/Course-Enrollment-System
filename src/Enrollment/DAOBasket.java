package Enrollment;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

import Cource.ELecture;

public class DAOBasket {

	public Vector<ELecture> storedLectures = new Vector<>();

	public void add(Vector<ELecture> lectures, String id) throws IOException {
		// TODO Auto-generated method stub

		// file write
		FileWriter fw = new FileWriter("data/basket" + id, true);
		for (ELecture lecture : lectures) {
			fw.write(lecture.getNumber() + " " + lecture.getName() + " " + lecture.getProfessor() + " "
					+ lecture.getCredit() + " " + lecture.getTime() + "\r\n");
		}
		fw.close();

	}

	public Vector<ELecture> show(String id) throws FileNotFoundException {
		storedLectures.removeAllElements();
		Scanner scanner = new Scanner(new FileReader("data/basket" + id));
		while (scanner.hasNext()) {
			ELecture storedLecture = new ELecture();
			storedLecture.read(scanner);
			storedLectures.add(storedLecture);
		}
		scanner.close();

		return storedLectures;
	}

	public void delete(Vector<ELecture> lectures, String id) throws IOException {
		for (ELecture lecture : lectures) {
			storedLectures.remove(lecture);
		}
		
		FileWriter fw = new FileWriter("data/basket" + id, false);
		for (ELecture storedLecture : storedLectures) {
			fw.write(storedLecture.getNumber() + " " + storedLecture.getName() + " " + storedLecture.getProfessor() + " "
					+ storedLecture.getCredit() + " " + storedLecture.getTime() + "\r\n");
		}
		fw.close();
	}


}
