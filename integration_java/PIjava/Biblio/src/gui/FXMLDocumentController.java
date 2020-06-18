
package gui;

import DB.DataSource;
import entity.User;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class FXMLDocumentController implements Initializable {

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button btnsignin;
    @FXML
    private Button btnsignup;
    
    public static String usernamerecup;
    public static String username_cononicalrecup;
    public static String emailrecup;
    public static String email_canonicalrecup;
    public static int enabledrecup;
    public static String passwordrecup;
    public static String rolesrecup;

  

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }

    @FXML
    private void signin(ActionEvent event) {
        String Username = username.getText();
        String Password = password.getText();
        User e = new User();
        

        String req = "SELECT * FROM fos_user WHERE `username`='" + Username + "' AND `password`='" + Password + "'";
        PreparedStatement stm;

        try {
            stm = DataSource.getInstance().getCnx().prepareStatement(req);
            ResultSet rs = stm.executeQuery(req);
            if (rs.next() == true) {
                FXMLDocumentController.rolesrecup = rs.getString("roles");
String est_admin;
        //   int est_admin = rs.getInt("status");
                est_admin = rolesrecup;

               FXMLDocumentController.usernamerecup = rs.getString("username");
                FXMLDocumentController.username_cononicalrecup = rs.getString("username_canonical");
                FXMLDocumentController.emailrecup = rs.getString("email");
                FXMLDocumentController.email_canonicalrecup = rs.getString("email_canonical");
                FXMLDocumentController.enabledrecup = rs.getInt("enabled");
               FXMLDocumentController.passwordrecup = rs.getString("password");
        
                
             
         

              if ("admin".equals(est_admin)) {


                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("/event/menu.fxml"));
                        Stage stage = (Stage) btnsignin.getScene().getWindow();
                        stage.close();
                        Scene scene = new Scene(root);

                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    try {
                        usernamerecup = rs.getString("username");
                        Parent root = FXMLLoader.load(getClass().getResource("/event/afficherevent1.fxml"));
                        Stage stage = (Stage) btnsignin.getScene().getWindow();
                        stage.close();
                        Scene scene = new Scene(root);

                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Informations incorrectes");
                alert.setContentText("\"Vérifiez votre identifiant  et votre mot de passe\"!");
                alert.showAndWait();
                password.clear();

            }

        } catch (SQLException ex) {

            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    @FXML
    private void signup(ActionEvent event) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/event/inscription.fxml"));
            Stage stage = (Stage) btnsignup.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
