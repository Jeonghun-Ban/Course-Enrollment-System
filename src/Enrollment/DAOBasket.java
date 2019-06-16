package Enrollment;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JOptionPane;

import Cource.ELecture;

public class DAOBasket {

	private Vector<ELecture> storedLectures = new Vector<>();
	private boolean envalidLecture = false;

	public void add(Vector<ELecture> lectures, Vector<ELecture> applyLectures, String id) throws IOException {
		// TODO Auto-generated method stub

		// 중복 강의리스트 삭제
		for (int i = 0; i < storedLectures.size(); i++) {
			ELecture storedLecture = storedLectures.get(i);
			for (int j = 0; j < lectures.size(); j++) {
				ELecture lecture = lectures.get(j);
				if (lecture.getName().equals(storedLecture.getName())) {
					lectures.remove(lecture);
					envalidLecture = true;
				}
			}
		}
		
		for (int i = 0; i < applyLectures.size(); i++) {
			ELecture applyLecture = applyLectures.get(i);
			for (int j = 0; j < lectures.size(); j++) {
				ELecture lecture = lectures.get(j);
				if (lecture.getName().equals(applyLecture.getName())) {
					lectures.remove(lecture);
					envalidLecture = true;
				}
			}
		}

		if(envalidLecture) {
			JOptionPane.showMessageDialog(null, "선택한 강좌 중에 이미 신청하거나 미리담은 강좌가 있습니다."
					+ "\n(중복되지 않은 강좌는 추가됩니다.)", "중복된 강의 존재", JOptionPane.ERROR_MESSAGE);
		}
		
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
		// 선택한 객체 storedLectures에서 삭제
		for (ELecture lecture : lectures) {
			storedLectures.remove(lecture);
		}

		FileWriter fw = new FileWriter("data/basket" + id, false);
		for (ELecture storedLecture : storedLectures) {
			fw.write(storedLecture.getNumber() + " " + storedLecture.getName() + " " + storedLecture.getProfessor()
					+ " " + storedLecture.getCredit() + " " + storedLecture.getTime() + "\r\n");
		}
		fw.close();
	}

}
