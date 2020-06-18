/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;


import DB.DataSource;
import entity.User;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import java.sql.PreparedStatement;
/**
 *
 * @author ramij
 */
public class ServiceLivreurAdmin {
    private Connection con;
    private Statement ste;
   
    public ServiceLivreurAdmin() {
          con = DataSource.getInstance().getCnx();
    //private final static String UPLOAD_PATH = "C:\\3A13\\Java\\pi\\src\\ressources\\";

    

    }
    
    
    
    
   public SessionUser  Connecter(String password,String username){
       try {
         ste=con.createStatement();
        
         
     
    ResultSet rs=ste.executeQuery("select e.* from fos_user e where username='"+username+"'");
     while (rs.next()) { 
         String passwordd =rs.getString("password");
         int idfos=rs.getInt(1);
         
                SessionUser su;
               
                if(BCrypt.checkpw(password,passwordd)){
                    String roles=rs.getString("roles");
                    String email=rs.getString("email");
                    Timestamp lastLog=rs.getTimestamp("last_login");
                    if(roles.contains("ADMIN")){
                        su=new SessionUser(idfos, "admin",username,email,lastLog);
                        return su;
                    }
                    if(roles.contains("EVENT")){
                        su=new SessionUser(idfos, "event",username,email,lastLog);
                        return su;
                    }
                    if(roles.contains("ENSEIGNANT"))
                    {
                    	 su=new SessionUser(idfos, "enseignant",username,email,lastLog);
                         return su;
                    }
                        else{
                         su=new SessionUser(idfos, "eleve",username,email,lastLog);
                        return su;
                
                    }
                }}
     } catch (SQLException ex) {
         System.out.println("probleme de connexion");
     }
    return null; 
    } 
   
     public boolean verifUsername(String username) {
        try {
            ste = con.createStatement();
            
            ResultSet rs = ste.executeQuery("select e.* from fos_user e where username='" + username +"'");
          
            while(rs.next()){
               return false;
             
            }
            
        } catch (SQLException sq) {
            return false;
        }
        return true;
    }
   
   public void ajouter(User t) {
        try {   
        
        String requeteInsert = "INSERT INTO fos_user (username,username_canonical,email,email_canonical,enabled,password,roles) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement pre=con.prepareStatement(requeteInsert);
     
        String password= BCrypt.hashpw(t.getPassword(), BCrypt.gensalt());
        
        pre.setString(1, t.getUsername());
        pre.setString(2, t.getUsername());
        pre.setString(3, t.getEmail());
        pre.setString(4, t.getEmail());
        pre.setBoolean(5, true);
        pre.setString(6, password);
        pre.setString(7, "a:0:{}");
        //pre.setString(8, "");
        pre.executeUpdate();
    
     } catch (SQLException ex) {
         
         System.out.println("erruer ajout");
     }
                    
    }
   
   
  
   public class SessionUser{
       private int idFos;
       private String typeFos;
       private String username;
       private String email;
       private Timestamp lastLogin;

       public SessionUser(){
           
       }
        public SessionUser(int idFos, String typeFos,String username, String email, Timestamp lastlogin) {
            this.idFos = idFos;
            this.typeFos = typeFos;
            this.username=username;
            this.lastLogin=lastlogin;
            this.email=email;                    
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Timestamp getLastLogin() {
            return lastLogin;
        }

        public void setLastLogin(Timestamp lastLogin) {
            this.lastLogin = lastLogin;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public int getIdFos() {
            return idFos;
        }

        public void setIdFos(int idFos) {
            this.idFos = idFos;
        }

        public String getTypeFos() {
            return typeFos;
        }

        public void setTypeFos(String typeFos) {
            this.typeFos = typeFos;
        }
       
   }
   
}
