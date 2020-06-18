/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import DB.DataSource;
import entity.Livre;
import entity.ReservationLivre;
import entity.Type;
import entity.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import org.joda.time.DateTime;
import org.joda.time.Instant;

/**
 *
 * @author Smirani
 */
public class ReservationService {
    
    Connection cn = DataSource.getInstance().getCnx();
    
    public void reserver_livre(Livre l,  User e,String a,int b) {
        try {
            //ajouter les reservations
            Date date1 = new Date(Calendar.getInstance().getTime().getTime());
            
            String query = "INSERT INTO `reservation`(`date`,`state`,`duree`,`id_Livre`,`id_User`) VALUES (?,?,?,?,?)";

            LivreService se = new LivreService();
            MyServices su = new MyServices();
            PreparedStatement pst = cn.prepareStatement(query);
            pst.setDate(1,date1);
            pst.setString(2,a);
            pst.setInt(3,b);
            pst.setLong(4, se.recuperer_livre(l));
            pst.setLong(5, e.getId());
            //reccuperer le nombre des participants  
            String query2 = "select `quantity`, `Nb_de_pages` from `livre` WHERE id=?  ";
            PreparedStatement st = cn.prepareStatement(query2);
            st.setLong(1, se.recuperer_livre(l));
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                l.setQuantite(rs.getInt("quantity"));
                l.setNbPersonnes(rs.getInt("Nb_de_pages"));
                //incrémenter le nombre des participants
                String querry1 = "UPDATE `livre` SET `quantity` =? WHERE id=?";
                PreparedStatement pst1 = cn.prepareStatement(querry1);
                pst1.setInt(1, l.getQuantite()- 1);
                pst1.setLong(2, se.recuperer_livre(l));
                pst1.executeUpdate();
            }

            pst.executeUpdate();
            System.out.println("Votre empreinte du Livre "+l.getNom()+ " a été effectuée avec succés ! \n");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la reservation \n " + ex.getMessage());
        }

    }

    
    public void rendre_livre(Livre e, User u) {

        try {
            ReservationLivre r = new ReservationLivre();
            LivreService se = new LivreService();
            MyServices su = new MyServices();

            //reccuperer le nbre de places dispo
            
            /*String query3 = "select `quantity` ,`Nb_de_pages` from `livre` WHERE id=? ";
            PreparedStatement st3 = cn.prepareStatement(query3);
            st3.setLong(1, se.recuperer_livre(e));
            ResultSet rs3 = st3.executeQuery();
            while (rs3.next()) {
                e.setQuantite(rs3.getInt("quantity"));
                //System.out.println(e.getQuantite());

                //diminuer nbre de place dispo 
                String querry1 = "UPDATE `livre` SET `quantity`=?  WHERE id=?";
                PreparedStatement pst1 = cn.prepareStatement(querry1);
                pst1.setInt(1, e.getQuantite()+ 1);
                pst1.setLong(2, se.recuperer_livre(e));
                pst1.executeUpdate();
            }  */          
            System.out.println("liiiivre : "+e);
            System.out.println("user :: "+u);
            // System.out.println(v.getNb_place_dispo());
            String query = "DELETE FROM `reservation` WHERE id_Livre=? and id_User=? ";
            PreparedStatement pst = cn.prepareStatement(query);
            pst.setLong(1, e.getId());
            pst.setLong(2, u.getId());
            pst.executeUpdate();
            System.out.println("Votre Réservation a été annulée ! \n");
        } catch (SQLException ex) {
            System.out.println("erreur lors de l'annulation \n " + ex.getMessage());

        }

    }
    
    
    public long recuperer_reservation(Livre e, User u) {
        long id = -1;
        ArrayList<ReservationLivre> reservations = new ArrayList<ReservationLivre>();
        reservations = this.afficher_Reservation();
        for (int i = 0; i < reservations.size(); i++) {
            if ((reservations.get(i).getId_livre()== e.getId()) && (reservations.get(i).getId_eleve()== u.getId())) {
                id = reservations.get(i).getId();
                System.out.println(id);
                break;
            }
        }
        return id;
    }
    
    
    public ArrayList<ReservationLivre> afficher_Reservation() {

        ArrayList<ReservationLivre> list = new ArrayList<ReservationLivre>();
        String querry = "SELECT * FROM reservation ";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(querry);
            ReservationLivre r = new ReservationLivre();
            while (rs.next()) {
                ReservationLivre e = new ReservationLivre(
                        rs.getInt(1),
                        rs.getInt(6),
                        rs.getInt(5),
                        rs.getInt(4),
                        rs.getString(3)
                );
                list.add(e);
                
            }
            System.out.println(list);
        } catch (SQLException ex) {
            System.out.println("Erreur lors d'extraction des données \n" + ex.getMessage());
        }

        return list;
    }
    
    public ArrayList<Livre> DisplayAllReserver() {
        ArrayList<ReservationLivre> list =  new ArrayList<>();
        list = this.afficher_Reservation();
        ArrayList<Livre> livres = new ArrayList<>();
        
        for(int i=0; i< list.size(); i++){
            
            String requete = "select * from livre where id="+list.get(i).getId_livre();
            try {
                Statement st = cn.createStatement();
                ResultSet x = st.executeQuery(requete);
                while (x.next()) {
                    livres.add(recupereResultat(x));
                }
            } catch (SQLException ex) {
                System.out.println("Errrrrrrrrrrrrrr");
            }
        }
        return livres;
    }
    //Taffichi par client 
    
    public ArrayList<Livre> DisplayLivreReserver(User e) {
        ArrayList<ReservationLivre> list =  new ArrayList<>();
        list = this.afficher_Reservation();
        ArrayList<Livre> livres = new ArrayList<>();
        System.out.println("hehi rak behi hedha howa eluser" + e);
        for(int i=0; i< list.size(); i++){
            if(e.getId()==list.get(i).getId_eleve()){
            String requete = "select * from livre where id="+list.get(i).getId_livre();
            try {
                Statement st = cn.createStatement();
                ResultSet x = st.executeQuery(requete);
                while (x.next()) {
                    livres.add(recupereResultat(x));
                }
            } catch (SQLException ex) {
                System.out.println("Errrrrrrrrrrrrrr");
            }
        }
        }
        return livres;
    }

       public Livre recupereResultat(ResultSet x) {
        Livre l = new Livre();
        try {          
                l.setId(x.getInt("id"));
                l.setNom(x.getString("nom"));
                l.setDescription(x.getString("resume"));
                l.setAuteur(x.getString("auteur"));
                l.setId_type(retournerType(x.getInt("id_categorie")));
                l.setQuantite(x.getInt("quantity"));
                l.setNbPersonnes(x.getInt("Nb_de_pages"));
                l.setEditeur(x.getString("editur"));
                l.setImage("file:C:\\wamp64\\www\\Lite\\web\\uploads\\images\\livres\\"+x.getString("photo"));       
        } catch (SQLException ex) {

        }

        return l;
    }
    
    
    public Type retournerType(int id) {
        try {
            PreparedStatement pt = cn.prepareStatement("select * from categorie where id=?");
            pt.setInt(1, id);
            ResultSet ty = pt.executeQuery();
            while (ty.next()) {
                return recupereType(ty);
            }
        } catch (SQLException ex) {

        }
        return null;
    }
    
    
    public Type recupereType(ResultSet x) {
        Type q = new Type();
        try {
            q.setIdL(x.getInt("id"));
            q.setDescription(x.getString("discreption"));

        } catch (SQLException ex) {

        }

        return q;
    }

}