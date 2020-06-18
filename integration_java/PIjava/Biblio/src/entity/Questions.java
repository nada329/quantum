package entity;

import java.util.ArrayList;

public class Questions {
	
	private int id;
	private String question;
	private ArrayList<Reponse> reponses = new ArrayList<Reponse>();
	
	
	
	
	public Questions(int id, String question) {
		super();
		this.id = id;
		this.question = question;
	}
	
	
	public Questions(String question) {
		super();
		this.question = question;
	}


	public Questions() {
		// TODO Auto-generated constructor stub
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public ArrayList<Reponse> getReponses() {
		return reponses;
	}
	public void setReponses(ArrayList<Reponse> reponses) {
		this.reponses = reponses;
	}
	
	

}
