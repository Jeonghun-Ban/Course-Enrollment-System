package Enrollment;

import java.io.IOException;
import java.util.Vector;

import Cource.ELecture;

public class CBasket {

	public DAOBasket dAOBasket;
	
	public CBasket() {
		this.dAOBasket = new DAOBasket();
	}
	public Vector<ELecture> add(Vector<ELecture> lectures, String id) throws IOException {
		// TODO Auto-generated method stub
		return dAOBasket.add(lectures, id);
	}

}
