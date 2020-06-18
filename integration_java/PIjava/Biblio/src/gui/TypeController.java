/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entity.Type;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import service.TypeService;

/**
 * FXML Controller class
 *
 * @authornada
 */
public class TypeController implements Initializable {

        
    @FXML
    private AnchorPane anchorpane_parent;


    @FXML
    private AnchorPane anchorpane_left;
    

    @FXML
    private JFXTextField txt_libelle;
     @FXML
    private JFXTextField txt_description;
    @FXML
    private JFXButton btn_ajouter;

    @FXML
    private AnchorPane anchorpane_right;

    @FXML
    private TableView<Type> tabview;

    @FXML
    private TableColumn<Type,Integer> column_id;

    @FXML
    private TableColumn<Type, String> column_libelle;
   
    @FXML
    private ImageView exit;
    @FXML
    private TableColumn<Type, String> column_description;
    @FXML
    private Label label_title;




    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AfficherType();
        SupprimerType();
        tabview.setEditable(true);
        column_id.setVisible(true);
        column_libelle.setCellFactory(TextFieldTableCell.forTableColumn());
    }    
        @FXML
    void AjouterType(ActionEvent event) {
        TypeService ty = new TypeService();
   
        //ObservableList<Type> types = FXCollections.observableArrayList(ty.AfficherType());
        String Lib = txt_libelle.getText();
        String description = txt_description.getText();
        Type t = new Type(Lib,description);
        if(txt_libelle.getText().isEmpty() || txt_description.getText().isEmpty())
        { 
             Alert alert = new Alert(AlertType.ERROR, "Oops Something went wrong ..", ButtonType.OK, ButtonType.NO, ButtonType.CANCEL);
                alert.showAndWait(); 
           
        }
        else{
           ty.AjouterType(t);
            Refresh();
        }
        
    }
    
    public void AfficherType() {
        TypeService ty = new TypeService();
   
        ObservableList<Type> types = FXCollections.observableArrayList(ty.AfficherType());
        tabview.setItems(types);
         System.out.println(ty.AfficherType());
        column_id.setCellValueFactory(new PropertyValueFactory<>("idL"));
        column_libelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        column_description.setCellValueFactory(new PropertyValueFactory<>("description"));
    }
    
    public void SupprimerType() {
         TypeService ty = new TypeService();
   
      
        tabview.setOnMouseClicked((event) -> {
            if (event.getButton() == MouseButton.PRIMARY) {

            } else if (event.getButton() == MouseButton.SECONDARY) {
                Type x = tabview.getSelectionModel().getSelectedItem();

                Alert alert = new Alert(AlertType.NONE, "Voulez vous supprimer " + x.getLibelle() +  " ?", ButtonType.OK, ButtonType.NO, ButtonType.CANCEL);
                alert.showAndWait();

                if (alert.getResult() == ButtonType.OK) {
                    //do stuff
                    ty.SupprimerType(x);
                    Refresh();
                }
            }
        });

    }
    
     @FXML
    void ModifierType(TableColumn.CellEditEvent edittedCell) {
        TypeService ty = new TypeService();     
        Type x = tabview.getSelectionModel().getSelectedItem();

        x.setLibelle(edittedCell.getNewValue().toString());
        ty.ModifierType(x);
     Refresh();
    }
    

    private void Refresh() {
        TypeService ty = new TypeService();     
       ObservableList<Type> types = FXCollections.observableArrayList(ty.AfficherType());
       types.clear();
      AfficherType();
    }


        @FXML
    private void clear(MouseEvent event) {
        txt_libelle.clear();
        txt_description.clear();
    }
    
    
    @FXML
    void exitApplication(MouseEvent event) {
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
