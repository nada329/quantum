package application.Entite;

public class Cours {
	
	private int id;
	private String libelle;
	private String nom;
	private String image;
	private Prof prof;
	
	
	
	public Cours(String libelle, String nom, String image,Prof prof) {
		super();
		this.libelle = libelle;
		this.nom = nom;
		this.prof=prof;
		this.image = image;
	}
	
	
	public Cours(int id,String libelle, String nom, String image,Prof prof) {
		super();
		this.id=id;
		this.libelle = libelle;
		this.nom = nom;
		this.prof=prof;
		this.image = image;
	}
	
	
	public Cours() {
		// TODO Auto-generated constructor stub
	}


	public Prof getProf() {
		return prof;
	}


	public void setProf(Prof prof) {
		this.prof = prof;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}


	@Override
	public String toString() {
		return "Cours [id=" + id + ", libelle=" + libelle + ", nom=" + nom + ", image=" + image + ", prof=" + prof
				+ "]";
	}
	
	
	
	
	
	

}
