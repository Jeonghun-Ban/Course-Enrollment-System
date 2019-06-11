package Enrollment;

import java.io.FileNotFoundException;
import java.util.Vector;

import Cource.ELecture;

public class CBasket {
	
	DAOBasket dAOBasket;
	
	public CBasket() {
		dAOBasket = new DAOBasket();
	}

	public Vector<ELecture> show(String id) throws FileNotFoundException {
		return dAOBasket.show(id);
	}
}
