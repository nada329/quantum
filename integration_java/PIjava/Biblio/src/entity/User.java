/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author Smirani
 */
public class User {
    private static final Map<Integer, User> USERS = new HashMap<>();
    private int id;
    private String username;
    private String username_canonical;
    private String email;
    private String email_canonical;
    private String password;
     
    private int enabled;
    //salt
    private Date last_login;
        private String roles;

    private String nom;
    private String prenom;
    private String phone;
 

    public User() {
    }

    public User(int id) {
        this.id = id;
    }
    
    
      public static User of(int id) {
        User user = USERS.get(id);
        if (user == null) {
            user = new User(id);
            USERS.put(id, user);
        }
        return user;
    }

    public User(int id, String username, String username_canonical, String email, String email_canonical, String password, int enabled, Date last_login, String roles, String phone) {
        this.id = id;
        this.username = username;
        this.username_canonical = username_canonical;
        this.email = email;
        this.email_canonical = email_canonical;
        this.password = password;
        this.enabled = enabled;
        this.last_login = last_login;
        this.roles = roles;
        this.phone = phone;
    }

    public User(int id, String username, String username_canonical, String email, String email_canonical, String password, int enabled, Date last_login, String roles, String nom, String prenom, String phone) {
        this.id = id;
        this.username = username;
        this.username_canonical = username_canonical;
        this.email = email;
        this.email_canonical = email_canonical;
        this.password = password;
        this.enabled = enabled;
         this.last_login=last_login;
        this.roles = roles;
        this.nom = nom;
        this.prenom = prenom;
        this.phone = phone;
       
    }
    
    

    public User(int id, String username, String username_canonical, String email, String email_canonical, String password, String roles, String nom, String prenom) {
        this.id = id;
        this.username = username;
        this.username_canonical = username_canonical;
        this.email = email;
        this.email_canonical = email_canonical;
        this.password = password;
        this.roles = roles;
        this.nom = nom;
        this.prenom = prenom;
        this.phone = phone;
     
    }

    public User(int id, String username, String username_canonical, String email, String email_canonical, String password, int enabled, String roles,String phone) {
        this.id = id;
        this.username = username;
        this.username_canonical = username_canonical;
        this.email = email;
        this.email_canonical = email_canonical;
        this.password = password;
        this.enabled = enabled;
        this.roles = roles;
       
        this.phone = phone;
    }
    
    public User(int id, String username, String username_canonical, String email, String email_canonical, String password, int enabled, String roles) {
        this.id = id;
        this.username = username;
        this.username_canonical = username_canonical;
        this.email = email;
        this.email_canonical = email_canonical;
        this.password = password;
        this.enabled = enabled;
        this.roles = roles;
       

    }

    public User(String username, String username_canonical, 
            String nom, String prenom, String roles, String phone) {
         
        this.username = username;
        this.username_canonical = username_canonical;
    
        this.roles = roles;
        this.nom = nom;
        this.prenom = prenom;
        this.phone = phone;
     
     
    }

    public User(String username, int id , String password, String nom, String prenom, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
    }
    
    


   
	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername_canonical() {
        return username_canonical;
    }

    public void setUsername_canonical(String username_canonical) {
        this.username_canonical = username_canonical;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail_canonical() {
        return email_canonical;
    }

    public void setEmail_canonical(String email_canonical) {
        this.email_canonical = email_canonical;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public Date getLast_login() {
        return last_login;
    }

    public void setLast_login(Date last_login) {
        this.last_login = last_login;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Usereeeeeeeeeeeeeeee{" + "id=" + id + ", username=" + username + ", username_canonical=" + username_canonical + ", email=" + email + ", email_canonical=" + email_canonical + ", password=" + password + ", enabled=" + enabled + ", last_login=" + last_login + ", roles=" + roles + ", nom=" + nom + ", prenom=" + prenom + ", phone=" + phone + '}';
    }
}
