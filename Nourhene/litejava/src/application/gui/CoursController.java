package application.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class CoursController implements Initializable {

    @FXML
    private Button gestion_cours;

    @FXML
    private Button gestion_quizz;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	}
	
	
	@FXML
	public void gotoCours() throws IOException
	{
		 	Stage stage;
	        Parent root;
	       
	        stage = (Stage) gestion_cours.getScene().getWindow();
	        root = FXMLLoader.load(getClass().getResource("Cours.fxml"));
	        stage.setScene(new Scene(root));
	}
	
	@FXML
	public void gotoEtudiant() throws IOException
	{
		 	Stage stage;
	        Parent root;
	       
	        stage = (Stage) gestion_cours.getScene().getWindow();
	        root = FXMLLoader.load(getClass().getResource("Etudiant.fxml"));
	        stage.setScene(new Scene(root));
	}
	
	

}
