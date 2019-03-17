/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

import Classes.Assignatura;
import Database.basedades;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author Víctor
 */
public class llistaAssignaturesController implements Initializable {

    @FXML
    private TableView<Assignatura> tableView;
    @FXML
    private TableColumn<Assignatura, String> idAssignatura;
    @FXML
    private TableColumn<Assignatura, String> nomAssignatura;
    @FXML
    private TableColumn<Assignatura, String> descripcioAssignatura;

    @FXML
    private TableColumn<Assignatura, String> creditsAssignatura;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Set uop the columns in the table
        idAssignatura.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomAssignatura.setCellValueFactory(new PropertyValueFactory<>("nom"));
        //TODO verificacion de creditos a int
        descripcioAssignatura.setCellValueFactory(new PropertyValueFactory<>("descripcio"));
        creditsAssignatura.setCellValueFactory(new PropertyValueFactory<>("credits"));

        //Load data
        tableView.setItems(getAssignatures());
    }

    public ObservableList getAssignatures() {
        ObservableList<Assignatura> assignatures = FXCollections.observableArrayList();
        List llistaAssignatures = basedades.obtenirAssignatures();
        for (int i = 0; i < llistaAssignatures.size(); i++) {
            //Array amb les dades del professor
            Assignatura assignatura = (Assignatura) llistaAssignatures.get(i);
            assignatures.add(assignatura);
        }

        return assignatures;
    }

    public void changeToAlumnesScene(ActionEvent event) throws IOException {
        Parent alumnes = FXMLLoader.load(getClass().getResource("sb.fxml"));
        Scene alumnesScene = new Scene(alumnes, 1000, 700);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(alumnesScene);
        window.show();
    }

    public void changeToafegirAssignatures(ActionEvent event) throws IOException {
        Parent afegirAlumne = FXMLLoader.load(getClass().getResource("afegirAssignatures.fxml"));
        Scene afegirAlumneScene = new Scene(afegirAlumne, 1000, 700);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(afegirAlumneScene);
        window.show();
    }

    public void changeToProfessorsScene(ActionEvent event) throws IOException {
        Parent professors = FXMLLoader.load(getClass().getResource("llistaProfessors.fxml"));
        Scene professorsScene = new Scene(professors, 1000, 700);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(professorsScene);
        window.show();
    }
}
