/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.security.MessageDigest;
import sun.misc.BASE64Encoder;
import java.net.URL;
import java.util.ResourceBundle;

import entity.Eleve;
import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import service.MyServices;
import service.ServiceLivreurAdmin;

/**
 * FXML Controller class
 *
 * @author ramij
 */
public class AuthentifactionController implements Initializable {

    //@FXML
    //private Text TitreAuthentification;
    @FXML
    private AnchorPane BackTextGroupBox;

    @FXML
    private StackPane GroupBox;

    @FXML
    private TextField UserName;

    @FXML
    private PasswordField Mdp;

    @FXML
    private Text erreurText;

    ServiceLivreurAdmin userDao = new ServiceLivreurAdmin();
    @FXML
    private Hyperlink Inscrire;

    public static int idUser = 0;
    public static Eleve userConnecter;
    public static String username="";
	
   
   

    MyServices myServices = new MyServices();
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    void HandleConnexionAction(ActionEvent event) throws IOException {
        ServiceLivreurAdmin.SessionUser connexion = userDao.Connecter(Mdp.getText(), UserName.getText());
        if (connexion != null) {

            if (connexion.getTypeFos().equals("admin")) {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("Livre.fxml"));
                Parent root = loader.load();
                   idUser=connexion.getIdFos();
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                // stage.show();
                Stage ThisStage = (Stage) erreurText.getScene().getWindow();

                stage.setTitle("Entrepot");

                stage.initStyle(StageStyle.TRANSPARENT);
                scene.setFill(Color.TRANSPARENT);
                stage.show();

                ThisStage.close();
            }
            
            if (connexion.getTypeFos().equals("enseignant")) {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("Cours.fxml"));
                Parent root = loader.load();
                   idUser=connexion.getIdFos();
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                // stage.show();
                Stage ThisStage = (Stage) erreurText.getScene().getWindow();

                stage.setTitle("Entrepot");

                stage.initStyle(StageStyle.TRANSPARENT);
                scene.setFill(Color.TRANSPARENT);
                stage.show();

                ThisStage.close();
            }
            

            if (connexion.getTypeFos().equals("event")) {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));
                Parent root = loader.load();
                idUser=connexion.getIdFos();
                userConnecter= myServices.chercherEleveById(idUser);
                username=UserName.getText();
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                // stage.show();
                Stage ThisStage = (Stage) erreurText.getScene().getWindow();

                stage.show();

                ThisStage.close();
            }
 
            if (connexion.getTypeFos().equals("eleve")) {

            	
            	
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Acceuil.fxml"));
                Parent root = loader.load();
                idUser=connexion.getIdFos();
                userConnecter= myServices.chercherEleveById(idUser);
                username=UserName.getText();
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                // stage.show();
                Stage ThisStage = (Stage) erreurText.getScene().getWindow();

                stage.show();

                ThisStage.close();
            }

        } else {
            erreurText.setVisible(true);
            Mdp.setText("");
        }
    }

    @FXML
    private void HandleInscrireConnexion(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Inscrire.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        ((Stage) erreurText.getScene().getWindow()).close();

        // stage.show();
    }

}
