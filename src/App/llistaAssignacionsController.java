/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

import Classes.Assignacio;
import Classes.Assignatura;
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
public class llistaAssignacionsController implements Initializable {

    @FXML
    private TableView<Assignatura> tableView;
    @FXML
    private TableColumn<Assignatura, String> idAssignacio;
    @FXML
    private TableColumn<Assignatura, String> anyAssignacio;
    @FXML
    private TableColumn<Assignatura, String> nom_professor;

    @FXML
    private TableColumn<Assignatura, String> nom_assignatura;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Set uop the columns in the table
        idAssignacio.setCellValueFactory(new PropertyValueFactory<>("id"));
        anyAssignacio.setCellValueFactory(new PropertyValueFactory<>("any"));
        //TODO verificacion de creditos a int
        nom_professor.setCellValueFactory(new PropertyValueFactory<>("id_professor"));
        nom_assignatura.setCellValueFactory(new PropertyValueFactory<>("id_Assignatura"));

        //Load data
        tableView.setItems(getAssignacions());
    }

    public ObservableList getAssignacions() {
        ObservableList<Assignacio> assignacions = FXCollections.observableArrayList();
        List llistaAssignacions = basedadesSqlite.obtenirAssignacions();
        for (int i = 0; i < llistaAssignacions.size(); i++) {
            //Array amb objectes assignacions
            Assignacio assignacio = (Assignacio) llistaAssignacions.get(i);
            assignacions.add(assignacio);
        }

        return assignacions;
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

    public void changeToAvaluacionsScene(ActionEvent event) throws IOException {
        Parent professors = FXMLLoader.load(getClass().getResource("llistaAvaluacions.fxml"));
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
    public void changeToAfegirAssignacionsScene(ActionEvent event) throws IOException {
        Parent professors = FXMLLoader.load(getClass().getResource("afegirAssignacions.fxml"));
        Scene professorsScene = new Scene(professors, 1000, 700);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(professorsScene);
        window.show();
    }
}
