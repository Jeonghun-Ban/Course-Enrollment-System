package Enrollment;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;

import Cource.ELecture;

public class CEnrollment {

	public DAOEnrollment dAOEnrollment;
	
	public CEnrollment() {
		this.dAOEnrollment = new DAOEnrollment();
	}
	
	public void add(String fileName, Vector<ELecture> lectures, String id) throws IOException {
		// TODO Auto-generated method stub
		dAOEnrollment.add(fileName, lectures, id);
	}
	
	public Vector<ELecture> show(String fileName, String id) throws FileNotFoundException {
		return dAOEnrollment.show(fileName, id);
	}

}
