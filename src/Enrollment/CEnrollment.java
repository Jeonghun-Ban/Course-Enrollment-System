package Enrollment;

import java.io.IOException;
import java.util.Vector;

import Cource.ELecture;

public class CEnrollment {

	public DAOEnrollment dAOBasket;
	
	public CEnrollment() {
		this.dAOBasket = new DAOEnrollment();
	}
	public Vector<ELecture> add(Vector<ELecture> lectures, String id) throws IOException {
		// TODO Auto-generated method stub
		return dAOBasket.add(lectures, id);
	}

}
