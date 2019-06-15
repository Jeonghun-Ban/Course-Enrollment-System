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
		for (ELecture lecture : lectures) {
			FileWriter fw = new FileWriter("data/basket" + id, true);
			fw.write(lecture.getNumber() + " " + lecture.getName() + " " + lecture.getProfessor() + " "
					+ lecture.getCredit() + " " + lecture.getTime() + "\r\n");
			fw.close();
		}

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
		storedLectures = this.show(id);
		for (ELecture lecture : lectures) {
			System.out.println(lecture.getName());
			for (ELecture storedLecture : storedLectures) {
				
				if (lecture.getNumber() == storedLecture.getNumber()) {
					storedLectures.remove(storedLecture);
				}
			}
		}

		// file write
		for (ELecture lecture : storedLectures) {
			
			FileWriter fw = new FileWriter("data/basket" + id);
			fw.write(lecture.getNumber() + " " + lecture.getName() + " " + lecture.getProfessor() + " "
					+ lecture.getCredit() + " " + lecture.getTime() + "\r\n");
			fw.close();
		}

	}


}
