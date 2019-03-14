/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

import static Database.basedades.afegirAssignatura;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Víctor
 */
public class afegirAssignaturesController implements Initializable {

    @FXML
    private JFXTextField nom;

    @FXML
    private JFXTextField descripcio;

    @FXML
    private JFXTextField credits;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        RequiredFieldValidator validator = new RequiredFieldValidator();

        nom.getValidators().add(validator);
        descripcio.getValidators().add(validator);
        credits.getValidators().add(validator);
        validator.setMessage("Falten valors d'entrada");

        nom.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldVAlue, Boolean newValue) {
                if (!newValue) {
                    nom.validate();
                }
            }
        });

        descripcio.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldVAlue, Boolean newValue) {
                if (!newValue) {
                    descripcio.validate();
                }
            }
        });

        credits.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldVAlue, Boolean newValue) {
                if (!newValue) {
                    credits.validate();
                }
            }
        });

    }

    public void changeToListAlumnes(ActionEvent event) throws IOException {
        Parent llistaAlumnes = FXMLLoader.load(getClass().getResource("sb.fxml"));
        Scene afegirAlumneScene = new Scene(llistaAlumnes, 1000, 700);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(afegirAlumneScene);
        window.show();
    }

    public void changeToListProfessors(ActionEvent event) throws IOException {
        Parent llistaAlumnes = FXMLLoader.load(getClass().getResource("llistaProfessors.fxml"));
        Scene afegirAlumneScene = new Scene(llistaAlumnes, 1000, 700);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(afegirAlumneScene);
        window.show();
    }
    
    public void changeToListAssignatures(ActionEvent event) throws IOException {
        Parent llistaAlumnes = FXMLLoader.load(getClass().getResource("llistaAssignatures.fxml"));
        Scene afegirAlumneScene = new Scene(llistaAlumnes, 1000, 700);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(afegirAlumneScene);
        window.show();
    }

    public void afegirAssignatures(ActionEvent event) throws IOException {
        String sNom = nom.getText();
        String sDescripcio = descripcio.getText();
        String sCredits = credits.getText();
        afegirAssignatura(sNom, sCredits, sDescripcio);
        Parent llistaAlumnes = FXMLLoader.load(getClass().getResource("llistaAssignatures.fxml"));
        Scene afegirAlumneScene = new Scene(llistaAlumnes, 1000, 700);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(afegirAlumneScene);
        window.show();

    }
}
