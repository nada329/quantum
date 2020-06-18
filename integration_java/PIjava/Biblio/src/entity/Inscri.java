
package entity;

public class Inscri {

    private String nom;
    private String username;
    private String prenom;
    private String numtel;
    private String nomevent;

    public Inscri() {
    }

    public Inscri(String username, String nom, String prenom, String numtel, String nomevent) {
        this.username = username;
        this.nom = nom;
        this.prenom = prenom;
        this.numtel = numtel;
        this.nomevent = nomevent;
    }

    public Inscri(String nom, String prenom, String numtel, String nomevent) {
        this.nom = nom;
        this.prenom = prenom;
        this.numtel = numtel;
        this.nomevent = nomevent;
    }

    
    public String getNom() {
        return nom;
    }
   
    public void setNom(String nom) {
        this.nom = nom;
    }   

    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

   
    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String Prenom) {
        this.prenom = prenom;
    }

    public String getNumtel() {
        return numtel;
    }

    public void setNumtel(String numtel) {
        this.numtel = numtel;
    }

    public String getNomevent() {
        return nomevent;
    }

    public void SetNomevent(String nomevent) {
        this.nomevent = nomevent;
    }

}
