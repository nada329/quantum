package application.Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import application.Entite.Cours;
import application.Entite.Prof;
import application.Entite.Questions;
import application.Entite.Reponse;
import application.utils.DataBase;

public class ServiceCours implements IServices<Cours> {

	  private Connection con;
	    private Statement ste;
	    private PreparedStatement pst;
	    
	    public ServiceCours() {
	    	 con = DataBase.getInstance().getConnection();
		}
	    
		@Override
		public void ajouterCour(Cours t) throws SQLException {
			PreparedStatement pre=con.prepareStatement ("INSERT INTO COURS(prof_id,libelle,nom,image) values(?,?,?,?)");
			
		       pre.setInt(1, t.getProf().getId());
		       pre.setString(2, t.getLibelle());
		       pre.setString(3, t.getNom());
		       pre.setString(4, t.getImage());
		       pre.executeUpdate();
			
		}
		
		@Override
		public boolean updateCour(Cours t) throws SQLException {
			
			String Cour="UPDATE cours SET libelle=?, nom=?, image=? where id=?";
			try {
				pst=con.prepareStatement(Cour);
				pst.setString(1, t.getLibelle());
				pst.setString(2, t.getNom());
				pst.setString(3, t.getImage());
				pst.setInt(4, t.getId());
				pst.executeUpdate();
				return true;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				Logger.getLogger(ServiceCours.class.getName()).log(Level.SEVERE,null,e);
			}
			
			return false;
		}
		
		
		public boolean updateCourProf(int idCour,Prof p) throws SQLException {
			ste=con.createStatement();
			ResultSet rs = ste.executeQuery("select * from cours where id="+idCour);
			Prof prof = new Prof();
			while(rs.next())
			{
				prof.setId(rs.getInt(2));
				System.out.println(rs.getInt(2));
			}
			
			String req="UPDATE prof SET nom_prof=? where id=?";
			try {
				pst=con.prepareStatement(req);
				pst.setString(1, p.getNomProf());
				pst.setInt(2, prof.getId());
				pst.executeUpdate();
				return true;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				Logger.getLogger(ServiceCours.class.getName()).log(Level.SEVERE,null,e);
			}
			
			
			
			return false;
		}
		
		@Override
		public boolean deleteCour(Cours t) throws SQLException {
			String req="DELETE FROM cours where id=?";
		    
			try {
				pst=con.prepareStatement(req);
				pst.setInt(1, t.getId());
				pst.executeUpdate();

			} catch (SQLException e) {
				
				Logger.getLogger(ServiceCours.class.getName()).log(Level.SEVERE,null,e);
			}
			
			return false;
		}
		
		@Override
		public List<Cours> ListeCours() throws SQLException {
			List<Cours> arr = new ArrayList<>();
			ste=con.createStatement();
			ResultSet rs = ste.executeQuery("select * from cours");
			
			while(rs.next())
			{
				int id = rs.getInt(1);
				String libelle=rs.getString(3);
				String nom=rs.getString(4);
				String image=rs.getString(5);
				Prof p = rechercheProf(rs.getInt(2));
				Cours c = new Cours(id,libelle, nom, image, p);
				arr.add(c);
			}
				return arr;
		}
		
		
		
		public List<Prof> listeProfs() throws SQLException
		{
			List<Prof> arr = new ArrayList<>();
			ste=con.createStatement();
			ResultSet rs = ste.executeQuery("select * from prof");
			
			while(rs.next())
			{
				int id = rs.getInt(1);
				String nom=rs.getString(2);
				Prof c = new Prof(id,nom);
				arr.add(c);
			}
				return arr;
			
		}
		
		public Prof rechercheProf(String nom) throws SQLException
		{
			Prof p = new Prof();
			String req="select * from prof where nom_prof=?";
			
			pst=con.prepareStatement(req);
			pst.setString(1, nom);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next())
			{
				int id=rs.getInt(1);
				String nomp=rs.getString(2);
				p.setId(id);
				p.setNomProf(nomp);
			}
			return p;
			
		}
		
		public Prof rechercheProf(int id) throws SQLException
		{
			Prof p = new Prof();
			ste=con.createStatement();
			ResultSet rs = ste.executeQuery("select * from prof where id="+id);
			while(rs.next())
			{
				int idp=rs.getInt(1);
				String nomp=rs.getString(2);
				p.setId(idp);
				p.setNomProf(nomp);
			}
			return p;
			
		}
		
		public Questions rechercheQuestion(String question) throws SQLException
		{
			Questions q = new Questions();
			ste=con.createStatement();
			ResultSet rs = ste.executeQuery("select * from questions where question='"+question+"'");
			while(rs.next())
			{
				int id=rs.getInt(1);
				String ques = rs.getString(2);
				q.setId(id);
				q.setQuestion(ques);
			}
			return q;
		}
		
		public List<Reponse> rechercheReponses(String question) throws SQLException
		{
			Reponse q = new Reponse();
			List<Reponse> rep = new ArrayList<Reponse>();
			ste=con.createStatement();
			Statement s = con.createStatement();
			ResultSet rs = ste.executeQuery("select * from questions where question='"+question+"'");
			while(rs.next())
			{
				int id=rs.getInt(1);
					ResultSet r = s.executeQuery("select * from reponse where question="+id);
					while(r.next())
					{
						int idq = r.getInt(2);
						String valeur = r.getString(3);
						String reponse = r.getString(4);
						q = new Reponse(idq,valeur,reponse);
						rep.add(q);
						
					}
			}
			return rep;
		}

}
