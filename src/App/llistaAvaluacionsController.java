/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

import Classes.Assignatura;
import Classes.Avaluacio;
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
public class llistaAvaluacionsController implements Initializable {

    @FXML
    private TableView<Assignatura> tableView;
    @FXML
    private TableColumn<Assignatura, String> idMatriculacio;
    @FXML
    private TableColumn<Assignatura, String> nomAssignatura;
    @FXML
    private TableColumn<Assignatura, String> curs;
    @FXML
    private TableColumn<Assignatura, String> nota;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Set uop the columns in the table
        idMatriculacio.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomAssignatura.setCellValueFactory(new PropertyValueFactory<>("nom"));
        curs.setCellValueFactory(new PropertyValueFactory<>("curs"));
        nota.setCellValueFactory(new PropertyValueFactory<>("nota"));
        //Load data
        tableView.setItems(getNotes());
    }

    public ObservableList getNotes() {
        ObservableList<Avaluacio> assignatures = FXCollections.observableArrayList();
        List llistaAvaluacions = basedades.obtenirAvaluacions();
        for (int i = 0; i < llistaAvaluacions.size(); i++) {
            //Array amb les dades del professor
            Avaluacio avaluacio = (Avaluacio)llistaAvaluacions.get(i);
            assignatures.add(avaluacio);
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
    
     public void changeToAssignaturesScene(ActionEvent event) throws IOException {
        Parent professors = FXMLLoader.load(getClass().getResource("llistaAssignatures.fxml"));
        Scene professorsScene = new Scene(professors, 1000, 700);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(professorsScene);
        window.show();
    }


    public void changeToProfessorsScene(ActionEvent event) throws IOException {
        Parent professors = FXMLLoader.load(getClass().getResource("llistaProfessors.fxml"));
        Scene professorsScene = new Scene(professors, 1000, 700);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(professorsScene);
        window.show();
    }
     public void changeToAssignacionsScene(ActionEvent event) throws IOException {
        Parent professors = FXMLLoader.load(getClass().getResource("llistaAssignacions.fxml"));
        Scene professorsScene = new Scene(professors, 1000, 700);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(professorsScene);
        window.show();
    }
     
    
}
