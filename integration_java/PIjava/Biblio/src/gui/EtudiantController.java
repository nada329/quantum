package gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.acl.Group;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import com.sun.xml.internal.txw2.Document;

import entity.Questions;
import entity.Reponse;
import entity.ReponseEleve;
import service.ServiceCours;
import service.ServiceEtudiant;
import service.ServiceQuizz;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
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
    private TextField question_quizz;

    @FXML
    private Text msg_info;
    
    
    private ServiceQuizz sq= new ServiceQuizz();
    private ServiceCours sc = new ServiceCours();
    private ServiceEtudiant se = new ServiceEtudiant();
    
    private final ObservableList<String> questions = FXCollections.observableArrayList();
    private List<Questions> listeQuestions = new ArrayList<Questions>();
    private List<Reponse> reponses = new ArrayList<Reponse>();
    private List<ReponseEleve> reponses_eleve = new ArrayList<ReponseEleve>();
    private ToggleGroup Group = new ToggleGroup();
    private static int ind=0;
    String filename = "Resultat.pdf";
    
    AuthentifactionController auth = new AuthentifactionController();
    int u = auth.idUser;
    
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
		try {
			listeQuestions();
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@FXML
	public void listeQuestions() throws SQLException, IOException
	{
		
		listeQuestions=sq.ListeQuestions();
		
		if(listeQuestions.isEmpty())
		{
			msg.setVisible(true);
			question_quizz.setVisible(false);
			rep1_txt.setVisible(false);
			rep2_txt.setVisible(false);
			rep3_txt.setVisible(false);
			envoyer_rep.setVisible(false);			
			
		}else
		{
			msg.setVisible(false);
			question_quizz.setVisible(true);
			rep1_txt.setVisible(true);
			rep2_txt.setVisible(true);
			rep3_txt.setVisible(true);
			envoyer_rep.setVisible(true);
			question_quizz.setText(listeQuestions.get(ind).getQuestion());
			afficher_reponses();
			
			
		
		}
		
		
	}
	
	@FXML
	public void afficher_reponses() throws SQLException, IOException
	{
		if(ind<listeQuestions.size())
		{
			
		
		reponses = sc.rechercheReponses(listeQuestions.get(ind).getQuestion());
		question_quizz.setText(listeQuestions.get(ind).getQuestion());
		rep1_txt.setText(reponses.get(0).getReponse());
		rep2_txt.setText(reponses.get(1).getReponse());
		rep3_txt.setText(reponses.get(2).getReponse());
		}else
		{
			Alert a = new Alert(AlertType.INFORMATION);
			a.setTitle("Quizz");
			a.setHeaderText(null);
			a.setContentText("vous avez répondu à toutes les questions, vous pouvez maintenant consulter votre score");
			a.showAndWait();
			afficher_score_eleve();
		}
		
		
	}
	
	@FXML
	public void verif_reponses() throws SQLException, IOException
	{
		int score=0;
		
		reponses = sc.rechercheReponses(listeQuestions.get(ind).getQuestion());
		if(ind<reponses.size())
		{
			
		
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
			
			
			se.Ajouter_Reponse_e(new ReponseEleve(score, reponses.get(0).getQuestion(), se.rechercheEleve(u)));
			Alert a = new Alert(AlertType.INFORMATION);
			a.setTitle("Quizz");
			a.setHeaderText(null);
			a.setContentText("Reponses du question: "+ind+" ont etait Enregistre");
			a.showAndWait();
			rep1.setSelected(false);
			rep2.setSelected(false);
			rep3.setSelected(false);
			rep1.setDisable(false);
			rep2.setDisable(false);
			rep3.setDisable(false);
			ind++;
			afficher_reponses();
			
			
		}
		
		
	}
	
	
	@FXML
	public void afficher_score_eleve() throws SQLException, IOException
	{
		reponses_eleve = se.reponseeleve(u);
		int score =0;
		for (ReponseEleve reponse : reponses_eleve) {
			score+=reponse.getScore();
			System.out.println(score);
		}
		
		 	String Titre="*******Resultat Quizz******";
	        String message = "Etudiant : "+AuthentifactionController.userConnecter.getNom()+" "+AuthentifactionController.userConnecter.getPrenom();
	        String sc ="Score du Quizz: "+score;
	        
	        PDDocument doc = new PDDocument();
	        try {
	        	
	        	File file = new File(System.getProperty("user.dir")+"\\Resultat.pdf");
	            PDDocument document = PDDocument.load(file);
	            document.removePage(0);
	            document.save(file);
	        	
	            PDPage page = new PDPage();
	            doc.addPage(page);
	            
	            PDFont font = PDType1Font.COURIER;
	 
	            PDPageContentStream contents = new PDPageContentStream(doc, page);
	            contents.beginText();
	            contents.setFont(font, 30);
	            contents.setLeading(14.5f);	           
	            contents.newLineAtOffset(25, 725);
	            
	            contents.showText(Titre);
	            contents.newLine();
	            contents.newLine();
	            contents.newLine();
	            contents.newLine();
	            contents.newLine();
	            contents.showText(message);
	            contents.newLine();
	            contents.newLine();
	            contents.newLine();
	            contents.newLine();
	            contents.showText(sc);
	            
	            contents.endText();
	            contents.close();
	            
	            
	            doc.save(filename);
	            
	            
	        }
	        finally {
	            doc.close();
	            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/C", "explorer "+System.getProperty("user.dir")+"\\Resultat.pdf");
	            processBuilder.start();
	        }
	        
		
		
		
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
