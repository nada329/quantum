package gui;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import entity.Cours;
import entity.Prof;
import entity.Questions;
import entity.Reponse;
import service.ServiceCours;
import service.ServiceQuizz;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
@SuppressWarnings("rawtypes")
public class GestionCoursController implements Initializable {
	

    @FXML
    private TextField libelle_cour;

    @FXML
    private TextField nom_cour;

    @FXML
    private TextField pdf_cour;

    @FXML
    private Button import_pdf;

    @FXML
    private Button ajouter_cour;
    
    @FXML
    private TableView<Cours> listeCours;
  
    @FXML
    private TableColumn<Cours, String> Libelle;

    @FXML
    private TableColumn<Cours, String> Nom;

    @FXML
    private TableColumn<Cours, String> PDF;

    @FXML
    private TableColumn<Cours, String> Prof;
    
    @FXML
    private Button supprimer_cour;
    
    @FXML
    private TextField question;

    @FXML
    private Button ajouter_question;

    @FXML
    private ComboBox<String> liste_questions;

    @FXML
    private TextField reponse;

    @FXML
    private Button ajouter_reponse;

    @FXML
    private RadioButton reponse_incorrecte;

    @FXML
    private RadioButton reponse_correcte;
    
    @FXML
    private ComboBox<String> liste_questions1;
    
    @FXML
    private VBox groupeQ;

    @FXML
    private TextField recherche;
    
    private ServiceCours sc = new ServiceCours();
    private ServiceQuizz sq= new ServiceQuizz();
    
    final ToggleGroup group = new ToggleGroup();
    
   AuthentifactionController auth = new AuthentifactionController();
    int u = auth.idUser;
    

    
    
    private final ObservableList<Cours> data = FXCollections.observableArrayList();
    private final ObservableList<String> questions = FXCollections.observableArrayList();
    private List<Cours> Cours = new ArrayList<>();
    private List<Questions> listeQuestions = new ArrayList<Questions>();
    private List<Reponse> reponses = new ArrayList<Reponse>();
    private Prof profConnecte;
    
     
    
    
	@Override
	public void initialize(URL location, ResourceBundle resources)  {

		try {
			
			profConnecte = sc.rechercheProf(LoginController.Id_user_connecte);
			listCours();
			listeQuestions();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		FilteredList<Cours> filtreddata = new FilteredList<>(data, b -> true);
		recherche.textProperty().addListener((observable,oldValue,newValue)->{
			filtreddata.setPredicate(cours->{
				if(newValue==null || newValue.isEmpty())
				{
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();
				if(cours.getLibelle().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				}else if(cours.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				}else if(cours.getImage().toLowerCase().indexOf(lowerCaseFilter) != -1)
				{
					return true;
				}else if(cours.getProf().getNomProf().toLowerCase().indexOf(lowerCaseFilter) != -1)
				{
					return true;
				}
				else
					return false;
			});
		});
		
		SortedList<Cours> sortedData = new SortedList<>(filtreddata);
		sortedData.comparatorProperty().bind(listeCours.comparatorProperty());
		
		
	}
	
	@FXML
	public void rechercheAv() {
		
	}
	
	public void ajouterCour() throws SQLException
	{
		
		sc.ajouterCour(new Cours(libelle_cour.getText(), nom_cour.getText(),pdf_cour.getText(), profConnecte));
		listCours();
	}
	@FXML
	private String selectImage()
	{
		final FileChooser image = new FileChooser();
		image.setTitle("Choisir une image ");
		String path = image.showOpenDialog(import_pdf.getScene().getWindow()).getName();
		pdf_cour.setText(path);
		return "http://localhost/lite/"+path;
	}
	
	@FXML
	private void listCours() throws SQLException
	{
		Cours=sc.ListeCours();
	
		data.clear();
		
		for (Cours cour : Cours) {
			data.add(cour);
		}
		

		
		
		Libelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
		Nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
		PDF.setCellValueFactory(new PropertyValueFactory<>("image"));
		Prof.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getProf().getNomProf()));
		
		listeCours.setItems(data);
		
		
		listeCours.setEditable(true);
		
		Libelle.setCellFactory(TextFieldTableCell.forTableColumn());
		Nom.setCellFactory(TextFieldTableCell.forTableColumn());
		PDF.setCellFactory(TextFieldTableCell.forTableColumn());
		Prof.setCellFactory(TextFieldTableCell.forTableColumn());
		
	}
	
	@FXML
	private void supprimerCour() throws SQLException
	{
		listeCours.setItems(data);

        ObservableList<Cours> allDemandes,SingleDemandes ;
        allDemandes=listeCours.getItems();
        SingleDemandes=listeCours.getSelectionModel().getSelectedItems();
        Cours p = SingleDemandes.get(0);
        sc.deleteCour(p);
        
        SingleDemandes.forEach(allDemandes::remove);
        listCours();
	}
	
	
	@FXML
	 public void Changer_LibelleCour(TableColumn.CellEditEvent evt) throws SQLException {
	     Cours selectedCour = new Cours();
	     Prof p = new Prof(listeCours.getSelectionModel().getSelectedItem().getProf().getNomProf());
	     selectedCour.setId(listeCours.getSelectionModel().getSelectedItem().getId());
	     selectedCour.setLibelle(evt.getNewValue().toString());
	     selectedCour.setNom(listeCours.getSelectionModel().getSelectedItem().getNom());
	     selectedCour.setImage(listeCours.getSelectionModel().getSelectedItem().getImage());
	     selectedCour.setProf(p);
	     sc.updateCour(selectedCour);
	     listCours();
	 }
	
	
	@FXML
	 public void Changer_NomCour(TableColumn.CellEditEvent evt) throws SQLException {
	     Cours selectedCour = listeCours.getSelectionModel().getSelectedItem();
	     selectedCour.setNom(evt.getNewValue().toString());
	     sc.updateCour(selectedCour);
	     listCours();
	 }
	
	@FXML
	 public void Changer_PDFCour(TableColumn.CellEditEvent evt) throws SQLException {
	     Cours selectedCour = listeCours.getSelectionModel().getSelectedItem();
	     selectedCour.setImage(evt.getNewValue().toString());
	     sc.updateCour(selectedCour);
	     listCours();
	 }
	
	@FXML
	 public void Changer_NomProfCour(TableColumn.CellEditEvent evt) throws SQLException {
	     Cours selectedCour = listeCours.getSelectionModel().getSelectedItem();
	     selectedCour.getProf().setNomProf(evt.getNewValue().toString());
	     Prof p = new Prof(listeCours.getSelectionModel().getSelectedItem().getProf().getNomProf());
	     sc.updateCourProf(selectedCour.getId(), p);
	     listCours();
	 }
	
	@FXML
	public void ajouter_Question() throws SQLException
	{
		if(question.getText().isEmpty())
		{
			Alert a = new Alert(AlertType.ERROR);
			a.setTitle("Question Vide");
			a.setHeaderText(null);
			a.setContentText("Veuillez ecrire un question");
			a.showAndWait();
		}else
		{
			Questions q = new Questions(question.getText());
			sq.ajouter_Question(q);
			Alert a = new Alert(AlertType.INFORMATION);
			a.setTitle("Question");
			a.setHeaderText(null);
			a.setContentText("Question Ajoute avec succees");
			a.showAndWait();
		}
	
	}
	@FXML
	public void listeQuestions() throws SQLException
	{
		reponse_correcte.setToggleGroup(group);
		reponse_correcte.setSelected(true);
		reponse_incorrecte.setToggleGroup(group);
		
		listeQuestions=sq.ListeQuestions();
		for(Questions question:listeQuestions)
		{
			questions.add(question.getQuestion());
		}
		
		liste_questions.setItems(questions);
		liste_questions1.setItems(questions);
		
		
	}
	
	@FXML
	public void ajouter_Reponse() throws SQLException
	{
		if(reponse.getText().isEmpty())
		{
			Alert a = new Alert(AlertType.ERROR);
			a.setTitle("Reponse Vide");
			a.setHeaderText(null);
			a.setContentText("Veuillez ecrire une reponse");
			a.showAndWait();
		}else
		{
			//Questions q = new Questions(question.getText());
			//sq.ajouter_Question(q);
			if(reponse_correcte.isSelected())
			{
				Reponse r = new Reponse("correcte", reponse.getText());
				sq.ajouter_Reponse(liste_questions.getSelectionModel().getSelectedItem().toString(),r);
				
			}else
			{
				Reponse r = new Reponse("incorrecte", reponse.getText());
				sq.ajouter_Reponse(liste_questions.getSelectionModel().getSelectedItem().toString(),r);
			}
			
			
			Alert a = new Alert(AlertType.INFORMATION);
			a.setTitle("Reponse");
			a.setHeaderText(null);
			a.setContentText("Reponse Ajoute avec succees");
			a.showAndWait();
			
		}
		
	}
	
	@FXML
	public void Afficher_Questions() throws SQLException
	{
		groupeQ.getChildren().clear();
		reponses = sc.rechercheReponses(liste_questions1.getSelectionModel().getSelectedItem().toString());
		for (Reponse reponse : reponses) {
			
			groupeQ.getChildren().add(new Label("Reponse : "+reponse.getReponse()+": "+reponse.getValeur()));
		}
				
		
	}
	

}
