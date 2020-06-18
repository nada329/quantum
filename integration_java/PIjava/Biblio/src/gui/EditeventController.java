
package gui;

import service.Eventcrud;
import entity.Event;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class EditeventController implements Initializable {

    @FXML
    private Button btnedit;
    @FXML
    private Button btnret;
    ObservableList<String> catlist = FXCollections.observableArrayList("Sport", "Divertissements", "Culturel", "Excursions");
    @FXML
    private TextField nomEvent;
    @FXML
    private DatePicker dateEvent;
    @FXML
    private TextField nb_p;
    @FXML
    private TextField prix;
    @FXML
    private TextArea description;
    @FXML
    private TextField adresse_event;
    @FXML
     private ComboBox type_event;

    String nomEventrecup = AffichereventController.nomEventrecup;
    Date dateEventrecup =  (Date) AffichereventController.dateEventrecup;
    Integer nb_precup = AffichereventController.nb_precup;
    String prixrecup = AffichereventController.prixrecup;
    String descriptionrecup = AffichereventController.descriptionrecup;
    String adresse_eventrecup = AffichereventController.adresse_eventrecup;
    String typeEventrecup = AffichereventController.type_eventrecup;
    //String imagerecup = AffichereventController.imagerecup;
    @FXML
    private ImageView dt;
    @FXML
    private ImageView imageEventPreview;
    @FXML
    private Label errorsImage;
    @FXML
    private Button importerimage;
    
 private String imageEvent;
 List<String> listFichier;
   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nomEvent.setText(nomEventrecup);
       dateEvent.setValue(LocalDate.now());
        nb_p.setText(String.valueOf(nb_precup));
        prix.setText(prixrecup);
        description.setText(descriptionrecup);
        adresse_event.setText(adresse_eventrecup);
        type_event.setValue("Sport");
        type_event.setItems(catlist);
        //image.setText(imagerecup);
        
        
        imageEvent = null;
        listFichier = new ArrayList<>();
        listFichier.add(".jpg");
        listFichier.add(".jpeg");

    }

    @FXML
    private void editevent(ActionEvent event) {
        String Nom = nomEvent.getText();
        // Float Prix = Float.parseFloat(prix.getText());
        Integer NbP = Integer.parseInt(nb_p.getText());
        String Prix = prix.getText();
        String Description = description.getText();
        String Adresse = adresse_event.getText();
        String Type = type_event.getSelectionModel().getSelectedItem().toString();
        //String Image = image.getText();
        LocalDate D_event = dateEvent.getValue();
        Date daten = Date.valueOf(D_event);

        LocalDate a = LocalDate.now();			// date courante
        Date datesys = Date.valueOf(a);
        if (datesys.compareTo(daten) > 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Date");
            alert.setContentText("Verifier votre date!");
            alert.showAndWait();
        } else {
            Event e = new Event(Nom, daten,NbP,Prix,Description, Adresse,  Type,imageEvent );

            Eventcrud udao = Eventcrud.getInstance();
            udao.updateref(e);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Modification Event");
            alert.setContentText("Event a ete modifier avec succes!");

            alert.showAndWait();
        }

    }

    @FXML
    private void retour(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/event/menu.fxml"));
            Stage stage = (Stage) btnret.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjouteventController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void importerEventImage(ActionEvent event) throws FileNotFoundException, IOException {
        //        FileChooser f = new FileChooser();
//        f.getExtensionFilters().add(new FileChooser.ExtensionFilter("jpeg,png files", listFichier));
//        File fc = f.showOpenDialog(null);
//        if (f != null) {
//            System.out.println(fc.getName());
//            imgG = fc.getAbsoluteFile().toURI().toString();
//            Image i = new Image(imgG);
//            imageEventPreview.setImage(i);
//        }
//        fc.exists();

 FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", listFichier));
        File f = fc.showOpenDialog(null);
        if (f != null) {

            errorsImage.setText("Image selectionnée" + f.getAbsolutePath());
            InputStream is = null;
            OutputStream os = null;
            try {
                is = new FileInputStream(new File(f.getAbsolutePath()));
//             System.out.println("Working Directory = " +
//              System.getProperty("user.dir"));
//            System.out.println("nomfichier"+f.getName());
                os = new FileOutputStream(new File("./src/img/" + f.getName()));
                byte[] buffer = new byte[1024];
                int length;
                while ((length = is.read(buffer)) > 0) {
                    os.write(buffer, 0, length);
                }

            } finally {
                is.close();
                os.close();
            }

            File file = new File("./src/img/" + f.getName());
//            System.out.println(file.toURI().toString());
            imageEventPreview.setImage(new Image(file.toURI().toString()));
            imageEvent = f.getName();
        } else if (f == null) {
            errorsImage.setText("Erreur chargement de l'image");
        }
    }
    

}
