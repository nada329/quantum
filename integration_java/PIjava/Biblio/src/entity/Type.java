/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Objects;

/**
 *
 * @author nada
 */
public class Type {
    
    private int idL;
    private String libelle;
    private String description;

    public Type(String Lib, String description) {
       this.libelle=Lib;
       this.description=description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
     public Type() {
    }

    @Override
    public String toString() {
        return "Type{" + "idL=" + idL + ", libelle=" + libelle + ", description=" + description + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Type other = (Type) obj;
        if (this.idL != other.idL) {
            return false;
        }
        if (!Objects.equals(this.libelle, other.libelle)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        return true;
    }

    public Type(int idL, String libelle, String description) {
        this.idL = idL;
        this.libelle = libelle;
        this.description = description;
    }

    

    public Type(String libelle) {
        this.libelle = libelle;
    }
    
     public int getIdL() {
        return idL;
    }

    public void setIdL(int idL) {
        this.idL = idL;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    
    
    
}
