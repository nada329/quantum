/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import DB.DataSource;
import entity.Type;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author nada
 */
public class TypeService {
    
    Connection cn = DataSource.getInstance().getCnx();
    
    public boolean AjouterType(Type t) {
       
        String requete = "INSERT INTO `categorie` (`nom`, `discreption`) VALUES ( '"+t.getLibelle()+"', '"+t.getDescription()+"')";
        System.out.println(requete);
        boolean x = false;
        try {
            Statement st = cn.createStatement();
            
            st.executeUpdate(requete);
            x=true;
           // etat.setText("Données Insérées avec success");
           // etat.setTextFill(Color.GREEN);
            //refresh();
        } catch (SQLException e) {
            //etat.setText("Impossible d'insérer ces données");
            //etat.setTextFill(Color.RED);
           
        }
return x;
    }
    public ArrayList<Type> AfficherType(){
         ArrayList<Type> types= new ArrayList<>();
        String requete = "SELECT * FROM categorie";
        try {
            
            Statement st = cn.createStatement();
            ResultSet x = st.executeQuery(requete);
            while (x.next()) {
                Type t = new Type();
                t.setIdL(x.getInt(1));
                t.setLibelle(x.getString(2));
                t.setDescription(x.getString(3));
                types.add(t);

              

            }
            //System.out.println(cours);
        } catch (SQLException ex) {
            System.out.println("ok");
        }
        return types;
    }
    public void SupprimerType(Type t){
        
        String requete = "delete from categorie where id = " + t.getIdL() + "";
            try {
                Statement st = cn.createStatement();
                st.executeUpdate(requete);
                

            } catch (SQLException e) {
               
            }
    }
    public void ModifierType(Type c){
         String requete = "update categorie set nom = '" + c.getLibelle() +  "' where id = " + c.getIdL() + "";
        try {
            Statement st = cn.createStatement();
            st.executeUpdate(requete);
          

        } catch (SQLException e) {
            
        }
    }
    
}
