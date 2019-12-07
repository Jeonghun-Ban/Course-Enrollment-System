package Cource;

import java.io.Serializable;

public class EDirectory implements Serializable { 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int number;
	private String name;
	private String hyperLink;
	
	public int getNumber() {
		return this.number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHyperLink() {
		return this.hyperLink;
	}
	public void setHyperLink(String hyperLink) {
		this.hyperLink = hyperLink;
	}
	
}
