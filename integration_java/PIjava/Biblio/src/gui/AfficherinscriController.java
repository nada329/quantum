package gui;

import DB.DataSource;
import service.Eventcrud;
import service.Inscricrud;
import entity.Event;
import entity.Inscri;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class AfficherinscriController implements Initializable {

    @FXML
    private TableView<Inscri> tablev;
    @FXML
    private Button btnretour;

    private final ObservableList<Inscri> datta = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Inscri, String> nominsc;
    //@FXML
   // private TableColumn<Inscri, String> prenominsc;
    @FXML
    private TableColumn<Inscri, String> numtelinsc;
    @FXML
    private TableColumn<Inscri, String> nomeventinsc;

    private void settable() {

        Inscricrud e = new Inscricrud();
        String username = FXMLDocumentController.usernamerecup;
        ArrayList<Inscri> ev = (ArrayList<Inscri>) e.displayInscri(username);
        ObservableList<Inscri> obs = FXCollections.observableArrayList(ev);

        tablev.setItems(obs);
        nominsc.setCellValueFactory(new PropertyValueFactory<>("nom"));
//        prenominsc.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        numtelinsc.setCellValueFactory(new PropertyValueFactory<>("numtel"));
        nomeventinsc.setCellValueFactory(new PropertyValueFactory<>("nomevent"));
        datta.addAll(ev);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        settable();
    }

    @FXML
    private void retour(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("afficherevent1.fxml"));
            Stage stage = (Stage) btnretour.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AfficherinscriController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
