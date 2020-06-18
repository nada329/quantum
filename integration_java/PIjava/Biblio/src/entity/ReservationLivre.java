/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Smirani
 */
public class ReservationLivre {
        private int id;
    private int id_eleve;
    private int id_livre;
    private int duree;
    private String state;

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    public ReservationLivre() {
    }

    public ReservationLivre(int id, int id_eleve, int id_livre) {
        this.id = id;
        this.id_eleve = id_eleve;
        this.id_livre = id_livre;
    }

    public ReservationLivre(int id, int id_eleve, int id_livre, int duree, String state) {
        this.id = id;
        this.id_eleve = id_eleve;
        this.id_livre = id_livre;
        this.duree = duree;
        this.state = state;
    }

    public ReservationLivre(int id_eleve, int id_livre) {
        this.id_eleve = id_eleve;
        this.id_livre = id_livre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_eleve() {
        return id_eleve;
    }

    public void setId_eleve(int id_eleve) {
        this.id_eleve = id_eleve;
    }

    public int getId_livre() {
        return id_livre;
    }

    public void setId_livre(int id_livre) {
        this.id_livre = id_livre;
    }

    @Override
    public String toString() {
        return "ReservationLivre{" + "id=" + id + ", id_eleve=" + id_eleve + ", id_livre=" + id_livre + ", duree=" + duree + ", state=" + state + '}';
    }

    

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ReservationLivre other = (ReservationLivre) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.id_eleve != other.id_eleve) {
            return false;
        }
        if (this.id_livre != other.id_livre) {
            return false;
        }
        return true;
    }
    
    
    
}
