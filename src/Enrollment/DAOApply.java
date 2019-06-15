package Enrollment;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

import Cource.ELecture;

public class DAOApply {

	public Vector<ELecture> storedLectures = new Vector<>();

	public void add(Vector<ELecture> lectures, Vector<ELecture> basketLectures, String id) throws IOException {
		// TODO Auto-generated method stub

		// 중복 강의리스트 삭제
		for (int i = 0; i < storedLectures.size(); i++) {
			ELecture storedLecture = storedLectures.get(i);
			for (int j = 0; j < lectures.size(); j++) {
				ELecture lecture = lectures.get(j);
				if (lecture.getNumber() == storedLecture.getNumber()) {
					lectures.remove(lecture);
				}
			}
		}
		
		for (int i = 0; i < basketLectures.size(); i++) {
			ELecture basketLecture = basketLectures.get(i);
			for (int j = 0; j < lectures.size(); j++) {
				ELecture lecture = lectures.get(j);
				if (lecture.getNumber() == basketLecture.getNumber()) {
					lectures.remove(lecture);
				}
			}
		}

		// file write
		for (ELecture lecture : lectures) {
			FileWriter fw = new FileWriter("data/apply" + id, true);
			fw.write(lecture.getNumber() + " " + lecture.getName() + " " + lecture.getProfessor() + " "
					+ lecture.getCredit() + " " + lecture.getTime() + "\r\n");
			fw.close();
		}

	}

	public Vector<ELecture> show(String id) throws FileNotFoundException {
		storedLectures.removeAllElements();
		Scanner scanner = new Scanner(new FileReader("data/apply" + id));
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

		FileWriter fw = new FileWriter("data/apply" + id, false);
		for (ELecture storedLecture : storedLectures) {
			fw.write(storedLecture.getNumber() + " " + storedLecture.getName() + " " + storedLecture.getProfessor()
					+ " " + storedLecture.getCredit() + " " + storedLecture.getTime() + "\r\n");
		}
		fw.close();
	}

}
