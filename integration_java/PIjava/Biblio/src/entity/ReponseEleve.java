package entity;

public class ReponseEleve {
	
	private int id;
	private int score;
	private int id_question;
	private Eleve eleve;
	
	
	
	
	public ReponseEleve(int score, int id_question, Eleve eleve) {
		super();
		this.score = score;
		this.id_question = id_question;
		this.eleve = eleve;
	}
	
	

	public ReponseEleve() {
		// TODO Auto-generated constructor stub
	}
	
	

	public int getId_question() {
		return id_question;
	}

	public void setId_question(int id_question) {
		this.id_question = id_question;
	}

	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public Eleve getEleve() {
		return eleve;
	}
	public void setEleve(Eleve eleve) {
		this.eleve = eleve;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	

}
