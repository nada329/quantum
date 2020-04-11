package application.Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.utils.DataBase;
import application.Entite.Eleve;
import application.Entite.ReponseEleve;

import java.util.ArrayList;
import java.util.List;

public class ServiceEtudiant {
	
	private Connection con;
    private Statement ste;
    
    public ServiceEtudiant() {
    	con = DataBase.getInstance().getConnection();
	}
    
  public List<ReponseEleve> reponseeleve(int id) throws SQLException
  {
	  List<ReponseEleve> re = new ArrayList<ReponseEleve>();
	  ReponseEleve r = new ReponseEleve(); 
	  
	  ste=con.createStatement();
		ResultSet rs = ste.executeQuery("select * from reponse_eleve where eleve_id="+id);
		
		while(rs.next())
		{
			int idq = rs.getInt(2);
			int score = rs.getInt(4);
			
			r = new ReponseEleve(score, idq, rechercheEleve(1));
			re.add(r);
		}
	  
	return re;
	  
  }
  
  
  public void Ajouter_Reponse_e(ReponseEleve re) throws SQLException
  {
	  PreparedStatement pre=con.prepareStatement ("INSERT INTO reponse_eleve(questions_id,eleve_id,score) values(?,?,?)");
      pre.setInt(1, re.getId_question());
      pre.setInt(2, re.getEleve().getId());
      pre.setInt(3, re.getScore());
      pre.executeUpdate();
  }
  
  public Eleve rechercheEleve(int id) throws SQLException
  {
	  Eleve e = new Eleve();
	  ste=con.createStatement();
		ResultSet rs = ste.executeQuery("select * from eleve where id="+id);
		while(rs.next())
		{
			e.setId(rs.getInt(1));
			e.setGroupe(null);
			e.setNom(rs.getString(3));
			e.setPrenom(rs.getString(4));
			e.setDatenaissance(rs.getString(5));
			
		}
	  
	return e;
	  
  }

}
