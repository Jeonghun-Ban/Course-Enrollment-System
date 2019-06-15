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
	
	public void add(String fileName, Vector<ELecture> lectures, String id) throws IOException {
		// TODO Auto-generated method stub
		dAOBasket.add(fileName, lectures, id);
	}
	
	public Vector<ELecture> show(String fileName, String id) throws FileNotFoundException {
		return dAOBasket.show(fileName, id);
	}

}
