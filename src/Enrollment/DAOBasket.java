package Enrollment;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

import Cource.ELecture;

public class DAOBasket {

	public Vector<ELecture> basketItems = new Vector<ELecture>();

	public Vector<ELecture> add(Vector<ELecture> lectures, String id) throws IOException {
		// TODO Auto-generated method stub

		// file write
		for (ELecture lecture : lectures) {	
			FileWriter fw = new FileWriter("data/basket" + id, true);
			fw.write(lecture.getNumber() + " " + lecture.getName() + " " + lecture.getProfessor() + " "
					+ lecture.getCredit() + " " + lecture.getTime() + "\r\n");
			fw.close();
		}

		// read basket vector
		Scanner scanner = new Scanner(new FileReader("data/basket" + id));
		while (scanner.hasNext()) {
			ELecture basketItem = new ELecture();
			basketItem.read(scanner);
			basketItems.add(basketItem);
		}
		scanner.close();
		// vector return
		return basketItems;
	}

}
