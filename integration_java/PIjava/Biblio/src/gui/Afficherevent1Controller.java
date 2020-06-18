package gui;

import DB.DataSource;
import service.Eventcrud;
import entity.Event;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class Afficherevent1Controller implements Initializable {

    @FXML
    private TextField rech;
    @FXML
    private TableView<Event> tablev;
    @FXML
    private Button btnret;
    @FXML
    private Button btninsci;
    @FXML
    private Button btndisp;
    private final ObservableList<Event> datta = FXCollections.observableArrayList();
    public static String nomEventrecup;
    public static Date dateEventrecup;
    public static Integer nb_precup;
    public static String prixrecup;
    public static String descriptionrecup;
    public static String adresse_eventrecup;
    public static String type_eventrecup;
    public static String imagerecup;

    @FXML
    private TableColumn<Event, String> nomEvent;
    @FXML
    private TableColumn<Event, Date> dateEvent;
    @FXML
    private TableColumn<Event, Integer> nb_p;
    @FXML
    private TableColumn<Event, String> prix;
    @FXML
    private TableColumn<Event, String> description;
    @FXML
    private TableColumn<Event, String> adresse_event;
    @FXML
    private TableColumn<Event, String> type_event;
    @FXML
    private TableColumn<Event, String> image;

    private void settable() {

        Eventcrud e = new Eventcrud();

        ArrayList<Event> ev = (ArrayList<Event>) e.displayEvent();
        ObservableList<Event> obs = FXCollections.observableArrayList(ev);
        tablev.setItems(obs);

        nomEvent.setCellValueFactory(new PropertyValueFactory<>("nomEvent"));
        dateEvent.setCellValueFactory(new PropertyValueFactory<>("dateEvent"));
        nb_p.setCellValueFactory(new PropertyValueFactory<>("nb_p"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        adresse_event.setCellValueFactory(new PropertyValueFactory<>("adresse_event"));
        type_event.setCellValueFactory(new PropertyValueFactory<>("type_event"));
        image.setCellValueFactory(new PropertyValueFactory<>("image"));

        datta.addAll(ev);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        settable();
        FilteredList<Event> filteredData = new FilteredList<>(datta, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        rech.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Event -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (Event.getNomEvent().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } else if (Event.getAdresse_event().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else {
                    return false; // Does not match.
                }
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Event> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tablev.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tablev.setItems(sortedData);
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
            Logger.getLogger(AjouteventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void inscri(ActionEvent event) throws IOException {

        Event E = tablev.getSelectionModel().getSelectedItem();

        Afficherevent1Controller.nomEventrecup = E.getNomEvent();

        Parent root = FXMLLoader.load(getClass().getResource("inscrievent.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void display(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("afficherinscri.fxml"));
            Stage stage = (Stage) btndisp.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Afficherevent1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
