/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

import Classes.Avaluacio;
import Database.basedadesSqlite;
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
    private TableView<Avaluacio> tableView;
    @FXML
    private TableColumn<Avaluacio, String> idAvaluacio;
    @FXML
    private TableColumn<Avaluacio, String> assignaturaAvaluacio;
    @FXML
    private TableColumn<Avaluacio, String> estudiantAvaluacio;

    @FXML
    private TableColumn<Avaluacio, String> notaAvaluacio;
    @FXML
    private TableColumn<Avaluacio, String> anyAvaluacio;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Set uop the columns in the table
        idAvaluacio.setCellValueFactory(new PropertyValueFactory<>("id"));
        estudiantAvaluacio.setCellValueFactory(new PropertyValueFactory<>("estudiant"));
        assignaturaAvaluacio.setCellValueFactory(new PropertyValueFactory<>("assignatura"));
        notaAvaluacio.setCellValueFactory(new PropertyValueFactory<>("nota"));
        anyAvaluacio.setCellValueFactory(new PropertyValueFactory<>("any"));
        //Load data
        tableView.setItems(getNotes());
    }

    public ObservableList getNotes() {
        ObservableList<Avaluacio> avaluacions = FXCollections.observableArrayList();
        List llistaAvaluacions = basedadesSqlite.obtenirAvaluacions();
        for (int i = 0; i < llistaAvaluacions.size(); i++) {
            //Array amb les dades del professor
            Avaluacio avaluacio = (Avaluacio) llistaAvaluacions.get(i);
            avaluacions.add(avaluacio);
        }

        return avaluacions;
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
    
    public void changeToAfegirAvaluacions(ActionEvent event) throws IOException {
        Parent avaluacions = FXMLLoader.load(getClass().getResource("afegirAvaluacions.fxml"));
        Scene avaluacionsScene = new Scene(avaluacions, 1000, 700);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(avaluacionsScene);
        window.show();
    }

}
