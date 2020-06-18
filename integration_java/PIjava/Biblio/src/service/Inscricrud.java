
package service;

import DB.DataSource;
import gui.AuthentifactionController;
import entity.Event;
import entity.Inscri;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Inscricrud {

    private static Inscricrud instance;
    private Statement st;
    private ResultSet rs;

    public Inscricrud() {
    	DataSource cs = DataSource.getInstance();
        try {
            st = cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Inscricrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Inscricrud getInstance() {
        if (instance == null) {
            instance = new Inscricrud();
        }
        return instance;
    }
    DataSource myc = DataSource.getInstance();
    Connection cnx = myc.getCnx();

    public void ajouterinscri(Inscri e) {

        String req = "INSERT INTO inscription_event (username,nom,prenom,numtel,nomevent) VALUES (?,?,?,?,?)";

        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, AuthentifactionController.username);
            pst.setString(2, e.getNom());
            pst.setString(3, e.getPrenom());
            pst.setString(4, e.getNumtel());
            pst.setString(5, e.getNomevent());

            pst.executeUpdate();
            System.out.println("Ajout Complete");
        } catch (SQLException ex) {
            System.out.println("erreur d'insertion");
            System.out.println(ex.getMessage());

        }
    }

   

    public List<Inscri> displayInscri(String username) {
        List<Inscri> myList = new ArrayList<>();
        DataSource cs = DataSource.getInstance();
String nom = "nur" ;
         try {
            String requete = "select * from inscription_event where username=" + "'" + username + "'";
            Statement st = (Statement) cs.getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {
                Inscri p = new Inscri();
                p.setNom(rs.getString("nom"));
                p.setPrenom(rs.getString("prenom"));
                p.setNumtel(rs.getString("numtel"));
                p.SetNomevent(rs.getString("nomevent"));
                System.out.println("TEST /");
                System.out.println(rs.getString("prenom"));
                System.out.println(rs.getString("nom"));
                myList.add(p);
                System.out.println(rs.getString("prenom"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

}
