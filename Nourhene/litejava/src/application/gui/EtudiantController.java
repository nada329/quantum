package application.gui;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import application.Entite.Questions;
import application.Entite.Reponse;
import application.Entite.ReponseEleve;
import application.Services.ServiceCours;
import application.Services.ServiceEtudiant;
import application.Services.ServiceQuizz;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;

public class EtudiantController implements Initializable {

	@FXML
    private ComboBox<String> liste_questions1;

    @FXML
    private CheckBox rep1;

    @FXML
    private CheckBox rep3;

    @FXML
    private CheckBox rep2;
    
    @FXML
    private TextField rep1_txt;

    @FXML
    private TextField rep2_txt;

    @FXML
    private TextField rep3_txt;
    
    @FXML
    private Button envoyer_rep;
    
    @FXML
    private Label msg;

    @FXML
    private TextField score_quizz;
    
    private ServiceQuizz sq= new ServiceQuizz();
    private ServiceCours sc = new ServiceCours();
    private ServiceEtudiant se = new ServiceEtudiant();
    
    private final ObservableList<String> questions = FXCollections.observableArrayList();
    private List<Questions> listeQuestions = new ArrayList<Questions>();
    private List<Reponse> reponses = new ArrayList<Reponse>();
    private List<ReponseEleve> reponses_eleve = new ArrayList<ReponseEleve>();
    private ToggleGroup Group = new ToggleGroup();
    
    
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			listeQuestions();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@FXML
	public void listeQuestions() throws SQLException
	{
		
		listeQuestions=sq.ListeQuestions();
		
		if(listeQuestions.isEmpty())
		{
			msg.setVisible(true);
			liste_questions1.setVisible(false);
			rep1_txt.setVisible(false);
			rep2_txt.setVisible(false);
			rep3_txt.setVisible(false);
			envoyer_rep.setVisible(false);			
			
		}else
		{
			msg.setVisible(false);
			liste_questions1.setVisible(true);
			rep1_txt.setVisible(true);
			rep2_txt.setVisible(true);
			rep3_txt.setVisible(true);
			envoyer_rep.setVisible(true);
		for(Questions question:listeQuestions)
		{
			questions.add(question.getQuestion());
		}
		liste_questions1.setItems(questions);
		
		}
		
		
	}
	
	@FXML
	public void afficher_reponses() throws SQLException
	{
		reponses = sc.rechercheReponses(liste_questions1.getSelectionModel().getSelectedItem().toString());
		rep1_txt.setText(reponses.get(0).getReponse());
		rep2_txt.setText(reponses.get(1).getReponse());
		rep3_txt.setText(reponses.get(2).getReponse());
		
	}
	
	@FXML
	public void verif_reponses() throws SQLException
	{
		int score =0;
		reponses = sc.rechercheReponses(liste_questions1.getSelectionModel().getSelectedItem().toString());
			
		if(rep1.isSelected())
			{
			
				if(reponses.get(0).getValeur().equals("correct"))
				{
					score+=10;
				}
			}
			
			if(rep2.isSelected())
			{
				
				if(reponses.get(1).getValeur().equals("correct"))
				{
					score+=10;
				}
			}
			
			if(rep3.isSelected())
			{
				
				if(reponses.get(2).getValeur().equals("correct"))
				{
					score+=10;
				}
			}
			
			
			se.Ajouter_Reponse_e(new ReponseEleve(score, reponses.get(0).getQuestion(), se.rechercheEleve(1)));
			Alert a = new Alert(AlertType.INFORMATION);
			a.setTitle("Quizz");
			a.setHeaderText(null);
			a.setContentText("Reponses Enregistre, pour voir le resultat voir la page resultat quizz");
			a.showAndWait();
			afficher_score_eleve();
			rep1.setSelected(false);
			rep2.setSelected(false);
			rep3.setSelected(false);
			rep1.setDisable(false);
			rep2.setDisable(false);
			rep3.setDisable(false);
	}
	
	
	@FXML
	public void afficher_score_eleve() throws SQLException
	{
		reponses_eleve = se.reponseeleve(1);
		int score =0;
		for (ReponseEleve reponse : reponses_eleve) {
			score+=reponse.getScore();
		}
		
		score_quizz.setText(""+score);
		
	}
	
	@FXML
	public void disable1()
	{
		if(rep1.isSelected())
		{
			rep2.setDisable(true);
			rep3.setDisable(true);
		}else
		{
			rep2.setDisable(false);
			rep3.setDisable(false);
		}
		
	}
	@FXML
	public void disable2()
	{
		if(rep2.isSelected())
		{
			rep1.setDisable(true);
			rep3.setDisable(true);
		}else
		{
			rep1.setDisable(false);
			rep3.setDisable(false);
		}
		
		
	}
	@FXML
	public void disable3()
	{
		if(rep3.isSelected())
		{
			rep1.setDisable(true);
			rep2.setDisable(true);
		}else
		{
			rep1.setDisable(false);
			rep2.setDisable(false);
		}
		
	}
	
	
	

}
