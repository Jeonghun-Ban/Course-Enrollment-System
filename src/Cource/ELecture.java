package Cource;

import java.util.Scanner;

public class ELecture {
	private int number;
	private String name;
	private String professor;
	private int credit;
	private String time;
	
	public void read(Scanner scanner) {
		this.number = scanner.nextInt();
		this.name = scanner.next();
		this.professor = scanner.next();
		this.credit = scanner.nextInt();
		this.time = scanner.next();
	}

	public int getNumber() {
		return number;
	}

	public String getName() {
		return name;
	}


	public String getProfessor() {
		return professor;
	}


	public int getCredit() {
		return credit;
	}


	public String getTime() {
		return time;
	}
}
