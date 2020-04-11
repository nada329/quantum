package application.Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import application.Entite.Questions;
import application.Entite.Reponse;
import application.utils.DataBase;

public class ServiceQuizz {
	
	 private Connection con;
	    private Statement ste;
	   
	    ServiceCours sc = new ServiceCours();
	    
	    public ServiceQuizz() {
	    	con = DataBase.getInstance().getConnection();
		}
	    
	public void ajouter_Question(Questions r) throws SQLException
	{
		PreparedStatement pre=con.prepareStatement ("INSERT INTO questions(question) values(?)");
	       pre.setString(1, r.getQuestion());
	       pre.executeUpdate();
	}
	
	public List<Questions> ListeQuestions() throws SQLException {
		List<Questions> arr = new ArrayList<>();
		ste=con.createStatement();
		ResultSet rs = ste.executeQuery("select * from questions");
		
		while(rs.next())
		{
			int id = rs.getInt(1);
			String question=rs.getString(2);
			Questions c = new Questions(id,question);
			arr.add(c);
		}
			return arr;
	}
	
	
	public void ajouter_Reponse(String question,Reponse r) throws SQLException
	{
		Questions q = sc.rechercheQuestion(question);
		Reponse rep = new Reponse(q.getId(), r.getValeur(), r.getReponse());
		PreparedStatement pre=con.prepareStatement("INSERT INTO reponse(question,valeur,reponse) values(?,?,?)");
		pre.setInt(1, rep.getQuestion());
		pre.setString(2, rep.getValeur());
		pre.setString(3, rep.getReponse());
		pre.executeUpdate();
	}
	

}
