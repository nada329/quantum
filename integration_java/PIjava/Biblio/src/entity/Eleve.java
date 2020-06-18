package entity;

public class Eleve {
	
	private int id;
	private String nom;
	private String prenom;
	private String datenaissance;
	private Groupe groupe;
	
	public Eleve(String nom, String prenom, String datenaissance, Groupe groupe) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.datenaissance = datenaissance;
		this.groupe = groupe;
	}
	
	public Eleve() {
		// TODO Auto-generated constructor stub
	}

	public Eleve(int id, String nom, String prenom) {
		this.id=id;
		this.nom = nom;
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getDatenaissance() {
		return datenaissance;
	}
	public void setDatenaissance(String datenaissance) {
		this.datenaissance = datenaissance;
	}
	public Groupe getGroupe() {
		return groupe;
	}
	public void setGroupe(Groupe groupe) {
		this.groupe = groupe;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	

}
