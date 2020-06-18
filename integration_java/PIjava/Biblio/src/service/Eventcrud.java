
package service;

import DB.DataSource;
import entity.Event;
import entity.User;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Eventcrud {

    private static Eventcrud instance;
    private Statement st;
    private ResultSet rs;
    

    public Eventcrud() {
        DataSource cs = DataSource.getInstance();
        try {
            st = cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Eventcrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Eventcrud getInstance() {
        if (instance == null) {
            instance = new Eventcrud();
        }
        return instance;
    }
    DataSource myc = DataSource.getInstance();
    Connection cnx = myc.getCnx();

    public void ajouterevent(Event e) {

        String req = "INSERT INTO event (nomEvent,dateEvent,nb_p,prix,description,adresse_event,type_event,image) VALUES (?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, e.getNomEvent());
            pst.setString(2, e.getDateEvent().toString());
            pst.setInt(3, e.getNb_p());
            pst.setString(4, e.getPrix());
            pst.setString(5, e.getDescription());
            pst.setString(6, e.getAdresse_event());
            pst.setString(7, e.getType_event());
            pst.setString(8, e.getImage());

            
            

            pst.executeUpdate();
            System.out.println("Ajout Complete");
        } catch (SQLException ex) {
            System.out.println("erreur d'insertion");
            System.out.println(ex.getMessage());

        }
    }

    public List<Event> displayEvent() {
        List<Event> myList = new ArrayList<>();
        DataSource cs = DataSource.getInstance();

        try {
            String requete = "select * from event";
            Statement st = (Statement) cs.getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {
                
                Event p = new Event();
                p.setNomEvent(rs.getString("nomEvent"));
                p.setDateEvent(rs.getDate("dateEvent"));
                p.setNb_p(rs.getInt("nb_p"));
                p.setPrix(rs.getString("prix"));
                p.setDescription(rs.getString("description"));
                p.setAdresse_event(rs.getString("adresse_event"));
                p.setType_event(rs.getString("type_event"));
                p.setImage(rs.getString("image"));
                

                myList.add(p);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

    public void delete(Event e) {
        String req = "delete from event where nomEvent ='" + e.getNomEvent()+ "'";
        if (e != null) {
            try {

                st.executeUpdate(req);

            } catch (SQLException ex) {
                Logger.getLogger(Event.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("n'existe pas");
        }
    }

    public void updateref(Event e) {
        String requete = "UPDATE event SET nomEvent=?,dateEvent=?,nb_p=?,prix=?,description=?,adresse_event=?,type_event=?,image=? WHERE nomEvent='" + e.getNomEvent()+ "';";

        try {
            
            
         
            
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, e.getNomEvent());
            pst.setString(2, e.getDateEvent().toString());
            pst.setInt(3, e.getNb_p());
            pst.setString(4, e.getPrix());
            pst.setString(5, e.getDescription());
            pst.setString(6, e.getAdresse_event());
            pst.setString(7, e.getType_event());
            pst.setString(8, e.getImage());
            
            
        System.out.println(e.getNomEvent());
        System.out.println(e.getDateEvent());
        System.out.println(e.getNb_p());
        System.out.println(e.getPrix());
        System.out.println(e.getDescription());
        System.out.println(e.getAdresse_event());
        System.out.println(e.getType_event());
        System.out.println(e.getImage());

        

            pst.executeUpdate();
            System.out.println("Event modifiée");
        } catch (SQLException ex) {
            System.out.println("erreur de modification");
            System.out.println(ex.getMessage());

        }
    }

}
