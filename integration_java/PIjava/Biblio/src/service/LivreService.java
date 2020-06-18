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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Date;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author Smirani
 */
public class LivreService {
    Connection cn = DataSource.getInstance().getCnx();
    //private final static String UPLOAD_PATH = "C:\\3A13\\Java\\pi\\src\\ressources\\";

    private String getExtension(File f) {
        return f.getName().substring(f.getName().lastIndexOf(".") + 1);
    }
    /*
    private String uploadFile(File f) {
        String fileExtension = getExtension(f);
        Date now = Calendar.getInstance().getTime();
        String uploadedFilename = String.valueOf(now.getTime()) + "." + fileExtension;

        File uploaded = new File(UPLOAD_PATH + uploadedFilename);
        try {
            FileInputStream fis = new FileInputStream(f);
            FileOutputStream fos = new FileOutputStream(uploaded);

            byte[] buffer = new byte[1024];
            int length;

            while ((length = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, length);
            }
            fis.close();
            fos.close();
            return uploadedFilename;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "";
        }
    }
      */  
    public boolean AjouterLivre(Livre l){
        
        
        String query = "insert into livre (id_categorie,nom,resume,photo,auteur,Nb_de_pages,editur,quantity) values('" + l.getId_type().getIdL()+ "','" +l.getNom()+ "','" +l.getDescription()+ "','" +l.getImage()+ "','" +l.getAuteur()+ "','" +l.getNbPersonnes()+ "','" +l.getEditeur()+ "','" +l.getQuantite()+ "')";
        System.out.println(query);
        boolean x = false;
        try {
            
            Statement st = cn.createStatement();System.out.println("l ajout mchéé");
            st.executeUpdate(query);System.out.println("l ajout mchéé");
            x=true;System.out.println("l ajout mchéé");
        } catch (SQLException e) {
            
            }
return x;
    }
    
  
    public ArrayList<Livre> AfficherLivre() {
        ArrayList<Livre> livres = new ArrayList<>();
        String requete = "(select * from livre)";
        try {
            Statement st = cn.createStatement();
            ResultSet x = st.executeQuery(requete);
            while (x.next()) {
                livres.add(recupereResultat(x));
            }
        } catch (SQLException ex) {
            System.out.println("Errrrrrrrrrrrrrr");
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
    
            
    public void update(Livre l) {

        String requete = "UPDATE `livre` SET `nom` = '" + l.getNom() + "', `resume` = '" + l.getDescription() + "', `auteur` = '" + l.getAuteur() + "', `id_categorie` = '" + l.getId_type().getIdL() + "', `quantity` = '" + l.getQuantite()+ "', `photo` = '" + l.getImage() + "', `editur` = '" + l.getEditeur()+ "' WHERE `livre`.`id` = '" + l.getId() + "'";
        try {
            Statement st = cn.createStatement();
            st.executeUpdate(requete);
            AfficherLivre();         
        } catch (SQLException e) {
        }
    }
    
    public void delete(int id) {
      try {
          PreparedStatement pt = cn.prepareStatement("delete from livre where id =?");
          pt.setInt(1,id);
          pt.execute();
      } catch (SQLException ex) {
          Logger.getLogger(LivreService.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
   
        public static boolean TxtFiNotEmpty(TextField tf){
        boolean b = false;
        if (tf.getText().length()!=0 || !tf.getText().isEmpty()) {
            b=true;            
        }
    return b;
}
    public static boolean TxtFiNotEmpty(TextField tf, Label lb, String  ErrorMsg){
        boolean b = true;
        String msg=null;
        tf.getStyleClass().remove("error");
        if (!TxtFiNotEmpty(tf)) {
            b=false;
            msg= ErrorMsg;
            tf.getStyleClass().add("error");
        }
        lb.setText(msg);
    return b;        
    }
    
    public static boolean TxtFiNumbre(TextField tf){
        boolean b = false;
        if (tf.getText().matches("([0-9]+(\\.[0-9]+)?)+")) {
            b=true;            
        }
    return b;
}
    public static boolean TxtFiNumber(TextField tf, Label lb, String  ErrorMsg){
        boolean b = true;
        String msg=null;
         tf.getStyleClass().remove("error");
        if (!TxtFiNumbre(tf) || !TxtFiNotEmpty(tf)) {
            b=false;
            msg= ErrorMsg;
            lb.setText(msg);
            tf.getStyleClass().add("error");
        }
        lb.setText(msg);
    return b;        
    }

    public long recuperer_livre(Livre l) {
        ArrayList<Livre> livres = new ArrayList<Livre>();
        livres = this.AfficherLivre();
        long id = -1;
        for (int i = 0; i < livres.size(); i++) {
            if ((livres.get(i).getId()==l.getId())&&(livres.get(i).getDescription().equals(l.getDescription())) && (livres.get(i).getNom().equals(l.getNom()))) {
                //System.out.println("exist");
                id = livres.get(i).getId();
                break;
            }

        }
        return id;
    }
    
   
}
