package Enrollment;

import java.io.FileNotFoundException;
import java.util.Vector;

import Cource.ELecture;

public class CInquire {
	
	DAOInquire dAOInquire;
	
	public CInquire() {
		dAOInquire = new DAOInquire();
	}

	public Vector<ELecture> show(String fileName, String id) throws FileNotFoundException {
		return dAOInquire.show(fileName, id);
	}
}
