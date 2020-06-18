package gui;

import DB.DataSource;
import service.Inscricrud;
import entity.Inscri;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class InscrieventController implements Initializable {

    @FXML
    private Button btnretour;
    public static String usernamerecup = AuthentifactionController.username;
    public static String nomeventinscrecup = Afficherevent1Controller.nomEventrecup;
    public static String email_recup = "nourhene.errd@gmil.com";
    @FXML
    private Button btnsignupp;
    @FXML
    private TextField nominsc;
    @FXML
    private TextField prenominsc;
    @FXML
    private TextField numtelinsc;
    @FXML
    private TextField nomeventinsc;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nomeventinsc.setText(nomeventinscrecup);
    }

    public void countrows() throws Exception {
        int s = 0;
        DataSource cs = DataSource.getInstance();
        String req = "SELECT * FROM inscription_event where username ='" +usernamerecup + "'";
        try {
            Statement st = (Statement) cs.getCnx().createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                s++;
            }
            if (s >= 3) {
                String mess = email_recup;
                sendmail.sendmail(mess);
                System.out.println(s);
            }

        } catch (SQLException ex) {
            System.out.println("erreur count");

        }
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
            Logger.getLogger(InscrieventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    @FXML
    private void signupp(ActionEvent event) throws Exception {
        String NomEvent = nomeventinsc.getText();
        String Nom = nominsc.getText();
        String Prenom = prenominsc.getText();
        String Numtel = numtelinsc.getText();
        Inscri e = new Inscri(usernamerecup,Nom, Prenom, Numtel, NomEvent);
        Inscricrud udao = Inscricrud.getInstance();
        udao.ajouterinscri(e);
        String mess = email_recup;
        sendmail.sendmail(mess);

        countrows();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ajout Event");
        alert.setContentText("Event a ete ajouté avec succes!");

        alert.showAndWait();
    }
}
