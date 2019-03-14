/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

import Classes.*;
import Database.*;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import static javafx.application.ConditionalFeature.FXML;
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
public class sceneAlumnesController implements Initializable {

    @FXML
    private TableView<Alumne> tableView;
    @FXML
    private TableColumn<Alumne, String> nomAlumne;
    @FXML
    private TableColumn<Alumne, String> DNIAlumne;
    @FXML
    private TableColumn<Alumne, String> adrecaAlumne;
    @FXML
    private TableColumn<Alumne, String> idAlumne;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Set uop the columns in the table
        idAlumne.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomAlumne.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        DNIAlumne.setCellValueFactory(new PropertyValueFactory<>("dni"));
        adrecaAlumne.setCellValueFactory(new PropertyValueFactory<>("adreca"));

        //Load data
        tableView.setItems(getAlumnes());
    }

    public ObservableList getAlumnes() {
        ObservableList<Alumne> alumnes = FXCollections.observableArrayList();
        List llistaAlumnes = basedades.obtenirEstudiants();
        for (int i = 0; i < llistaAlumnes.size(); i++) {
            //Array amb les dades de l'alumne
            String[] alumne = (String[]) llistaAlumnes.get(i);
            String id = alumne[0];
            String nom = alumne[1];
            String dni = alumne[2];
            String adreca = alumne[3];
            alumnes.add(new Alumne(id, nom, dni, adreca));
        }

        return alumnes;
    }
    
    public void changeToafegirAlumneScene(ActionEvent event) throws IOException{
        Parent afegirAlumne = FXMLLoader.load(getClass().getResource("afegirAlumnes.fxml"));
        Scene afegirAlumneScene = new Scene(afegirAlumne, 1000, 700);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(afegirAlumneScene);
        window.show();
    }
    
    public void changeToProfessorsScene(ActionEvent event) throws IOException{
        Parent professors = FXMLLoader.load(getClass().getResource("llistaProfessors.fxml"));
        Scene professorsScene = new Scene(professors, 1000, 700);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(professorsScene);
        window.show();
    }
    
    

}
