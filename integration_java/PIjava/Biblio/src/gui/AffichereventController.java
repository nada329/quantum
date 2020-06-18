
package gui;

import DB.DataSource;
import service.Eventcrud;
import entity.Event;
import java.awt.Dimension;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import org.controlsfx.control.Notifications;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class AffichereventController implements Initializable {

    @FXML
    private TextField rech;
    @FXML
    private TableView<Event> tablev;
    @FXML
    private Button btnret;
    @FXML
    private Button btnedit;
    @FXML
    private Button btndel;
    @FXML
    private Button btnimp1;
    
    
    private XSSFWorkbook wb;
    private XSSFSheet sheet;
     private XSSFRow header;
     
     
     private ResultSet rs=null;

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
            Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
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
    private void edit(ActionEvent event) throws IOException {

        Event E = tablev.getSelectionModel().getSelectedItem();

        AffichereventController.nomEventrecup = E.getNomEvent();
        AffichereventController.dateEventrecup = E.getDateEvent();
        AffichereventController.nb_precup = E.getNb_p();
        AffichereventController.prixrecup = E.getPrix();
        AffichereventController.descriptionrecup = E.getDescription();
        AffichereventController.adresse_eventrecup = E.getAdresse_event();
        AffichereventController.type_eventrecup = E.getType_event();
        AffichereventController.imagerecup = E.getImage();
        

        

        Parent root = FXMLLoader.load(getClass().getResource("editevent.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void del(ActionEvent event) {

        ImageIcon icon = new ImageIcon("E −\\new.PNG");
        JPanel panel = new JPanel();
        panel.setSize(new Dimension(250, 100));
        panel.setLayout(null);
        JLabel label1 = new JLabel("Delete event");
        label1.setVerticalAlignment(SwingConstants.BOTTOM);
        label1.setBounds(20, 20, 200, 30);
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label1);
        JLabel label2 = new JLabel("Do you still want to Delete it?");
        label2.setVerticalAlignment(SwingConstants.TOP);
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        label2.setBounds(20, 80, 200, 20);
        panel.add(label2);
        UIManager.put("OptionPane.minimumSize", new Dimension(400, 200));
        int res = JOptionPane.showConfirmDialog(null, panel, "File",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, icon);
        if (res == 0) {
            Event e = tablev.getSelectionModel().getSelectedItem();

            Eventcrud udao = Eventcrud.getInstance();
            udao.delete(e);
            System.out.println("Event deleted");
            Notifications.create().title("Supression").text("Event supprimé").showConfirm();
        } else if (res == 1) {
            System.out.println("Pressed No");

        } else {
            System.out.println("Pressed CANCEL");
        }
    }

    @FXML
    private void imp(ActionEvent event) throws SQLException, IOException {
        try {
            DataSource myc = DataSource.getInstance();
             Connection cnx = myc.getCnx();
            
            String query ="select * from `event` ";
            PreparedStatement pst = cnx.prepareStatement(query);
            rs = pst.executeQuery(); //rs
            System.out.println("kk");
            int i=1;
            
            wb= new XSSFWorkbook();
            System.out.println("n");
            sheet= wb.createSheet("Event Details");
            System.out.println("d");
            header = sheet.createRow(0);
            System.out.println("f");
            header.createCell(0).setCellValue("idevent");
            header.createCell(1).setCellValue("Nom Event");
            header.createCell(2).setCellValue("Date Event");
            header.createCell(3).setCellValue("Nombre de participants ");
            header.createCell(4).setCellValue("Prix");
            header.createCell(5).setCellValue("Description");
            header.createCell(6).setCellValue("Adresse Event");
            header.createCell(7).setCellValue("Type Event");

            
            System.out.println("4");
            while(rs.next()){
                System.out.println("9");
                XSSFRow row=sheet.createRow(i);
                System.out.println("o");
                row.createCell(0).setCellValue(rs.getInt("idevent"));
                System.out.println("qs");
                row.createCell(1).setCellValue(rs.getString("nomEvent"));
                row.createCell(2).setCellValue(rs.getDate("dateEvent"));
                row.createCell(3).setCellValue(rs.getInt("nb_p"));
                row.createCell(4).setCellValue(rs.getString("prix"));
                row.createCell(5).setCellValue(rs.getString("description"));
                row.createCell(6).setCellValue(rs.getString("adresse_event"));
                row.createCell(7).setCellValue(rs.getString("type_event"));                
                i++;
            }
            JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("choose title");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            System.out.println("getCurrentDirectory(): " + chooser.getCurrentDirectory());
            System.out.println("getSelectedFile() : " + chooser.getSelectedFile());
            System.out.println("dd");
            FileOutputStream fileOut = new FileOutputStream(chooser.getSelectedFile() + "/Event.xlsx");
            System.out.println("qs");
            wb.write(fileOut);
            fileOut.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("La liste des évènements en format Exel a été exporter .");
            alert.showAndWait();

            rs.close();
        } else {
             System.out.println("No Selection ");
        }         
            
            pst.close();
            rs.close();
            
        } catch (IOException ex) {
        }   
        
    }

}
