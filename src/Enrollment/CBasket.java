package Enrollment;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;

import Cource.ELecture;

public class CBasket {

	public DAOBasket dAOBasket;
	
	public CBasket() {
		this.dAOBasket = new DAOBasket();
	}
	
	public void add(Vector<ELecture> lectures, Vector<ELecture> applyLectures, String id) throws IOException {
		// TODO Auto-generated method stub
		dAOBasket.add(lectures, applyLectures, id);
	}
	
	public Vector<ELecture> show(String id) throws FileNotFoundException {
		return dAOBasket.show(id);
	}

	public void delete(Vector<ELecture> lectures, String id) throws IOException {
		// TODO Auto-generated method stub
		dAOBasket.delete(lectures, id);
	}

}
