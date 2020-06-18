
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MenuController implements Initializable {

    @FXML
    private Button btnaff;
    @FXML
    private Button btnret;
    @FXML
    private Button btnaj;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

  

    @FXML
    private void affevent(ActionEvent event) {
        
         try {
                        Parent root = FXMLLoader.load(getClass().getResource("afficherevent.fxml"));
                        Stage stage = (Stage) btnaff.getScene().getWindow();
                        stage.close();
                        Scene scene = new Scene(root);

                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
                    }
    }

    @FXML
    private void ret(ActionEvent event) {
         try {
            Parent root = FXMLLoader.load(getClass().getResource("Authentifaction.fxml"));
            Stage stage = (Stage) btnret.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



    @FXML
    private void ajoutereve(ActionEvent event) {
         try {
                        Parent root = FXMLLoader.load(getClass().getResource("ajoutevent.fxml"));
                        Stage stage = (Stage) btnaff.getScene().getWindow();
                        stage.close();
                        Scene scene = new Scene(root);

                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
                    }
    }
    
}
