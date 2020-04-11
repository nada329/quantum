package application.Entite;

public class Groupe {
	
	private int id;
	private String nom;
	private String salle;
	private String specialite;
	
	
	
	public Groupe(String nom, String salle, String specialite) {
		super();
		this.nom = nom;
		this.salle = salle;
		this.specialite = specialite;
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getSalle() {
		return salle;
	}
	public void setSalle(String salle) {
		this.salle = salle;
	}
	public String getSpecialite() {
		return specialite;
	}
	public void setSpecialite(String specialite) {
		this.specialite = specialite;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	

}
