package application.Entite;

import java.util.ArrayList;

public class Prof {

	private int id;
	private String nomProf;
	private ArrayList<Cours> cours = new ArrayList<Cours>();
	private ArrayList<Questions> questions = new ArrayList<Questions>();
	
	public ArrayList<Questions> getQuestions() {
		return questions;
	}

	public void setQuestions(ArrayList<Questions> questions) {
		this.questions = questions;
	}

	public ArrayList<Cours> getCours() {
		return cours;
	}

	public void setCours(ArrayList<Cours> cours) {
		this.cours = cours;
	}

	public Prof(String nomProf) {
		super();
		this.nomProf = nomProf;
	}
	
	
	
	public Prof(int id, String nomProf) {
		super();
		this.id = id;
		this.nomProf = nomProf;
	}

	public Prof() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNomProf() {
		return nomProf;
	}
	public void setNomProf(String nomProf) {
		this.nomProf = nomProf;
	}
	
	
	
}
