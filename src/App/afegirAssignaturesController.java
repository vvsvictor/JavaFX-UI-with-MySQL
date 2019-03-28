/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

import static Database.basedadesSqlite.afegirAssignatura;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.NumberValidator;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Víctor
 */
public class afegirAssignaturesController implements Initializable {

    @FXML
    private JFXTextField nom;

    @FXML
    private JFXTextField credits;

    @FXML
    private JFXTextField descripcio;

    @FXML
    private JFXButton afegirAssignaturaBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        afegirAssignaturaBtn.setDisable(true);
        NumberValidator numberValid = new NumberValidator();
        numberValid.setMessage("El valor introduït no és correcte");

        RequiredFieldValidator validator = new RequiredFieldValidator();
        validator.setMessage("Falten valors d'entrada");
        nom.getValidators().add(validator);
        descripcio.getValidators().add(validator);
        credits.getValidators().add(numberValid);
        credits.getValidators().add(validator);

        nom.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldVAlue, Boolean newValue) {
                if (!newValue) {
                    nom.validate();
                }
            }
        });
//
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

    public void validateInput() {
        String sNom = nom.getText();
        String sDescripcio = descripcio.getText();
        String sCredits = credits.getText();
        if ((!isInt(credits)) || sCredits.isEmpty() || sDescripcio.isEmpty() || sNom.isEmpty()) {
            afegirAssignaturaBtn.setDisable(true);
        } else {
            afegirAssignaturaBtn.setDisable(false);
        }
    }

    private boolean isInt(TextField input) {
        try {
            int credits = Integer.parseInt(input.getText());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void changeToListAlumnes(ActionEvent event) throws IOException {
        Parent llistaAlumnes = FXMLLoader.load(getClass().getResource("sb.fxml"));
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
}
