package Enrollment;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;

import Cource.ELecture;

public class CApply {

	public DAOApply dAOApply;
	
	public CApply() {
		this.dAOApply = new DAOApply();
	}
	
	public void add(Vector<ELecture> lectures, String id) throws IOException {
		// TODO Auto-generated method stub
		dAOApply.add(lectures, id);
	}
	
	public Vector<ELecture> show(String id) throws FileNotFoundException {
		return dAOApply.show(id);
	}

	public void delete(Vector<ELecture> lectures, String id) throws IOException {
		// TODO Auto-generated method stub
		dAOApply.delete(lectures, id);
	}
}
