package application.Entite;

public class Reponse {
	
	private int id;
	private int question;
	private String valeur;
	private String reponse;
	
	
	
	public Reponse(String valeur, String reponse) {
		super();
		this.valeur = valeur;
		this.reponse = reponse;
	}
	
	
	public Reponse(int question, String valeur, String reponse) {
		super();
		this.question = question;
		this.valeur = valeur;
		this.reponse = reponse;
	}


	public Reponse() {
		// TODO Auto-generated constructor stub
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getValeur() {
		return valeur;
	}
	public void setValeur(String valeur) {
		this.valeur = valeur;
	}
	public String getReponse() {
		return reponse;
	}
	public void setReponse(String reponse) {
		this.reponse = reponse;
	}
	
	public int getQuestion() {
		return question;
	}
	public void setQuestion(int question) {
		this.question = question;
	}


	@Override
	public String toString() {
		return "Reponse [id=" + id + ", question=" + question + ", valeur=" + valeur + ", reponse=" + reponse + "]";
	}
	
	
	

}
