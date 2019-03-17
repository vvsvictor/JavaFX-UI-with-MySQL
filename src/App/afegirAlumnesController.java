/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

import static Database.basedades.*;
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
public class afegirAlumnesController implements Initializable {

    @FXML
    private JFXTextField nomicognoms;

    @FXML
    private JFXTextField dni;

    @FXML
    private JFXTextField adreca;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        RequiredFieldValidator validator = new RequiredFieldValidator();
        
        nomicognoms.getValidators().add(validator);
        dni.getValidators().add(validator);
        adreca.getValidators().add(validator);
        validator.setMessage("Falten valors d'entrada");

        nomicognoms.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldVAlue, Boolean newValue) {
                if (!newValue) {
                    nomicognoms.validate();
                }
            }
        });
        
        dni.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldVAlue, Boolean newValue) {
                if (!newValue) {
                    dni.validate();
                }
            }
        });
        
        adreca.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldVAlue, Boolean newValue) {
                if (!newValue) {
                    adreca.validate();
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
    
    public void afegirAlumne(ActionEvent event) throws IOException{
        String sNomicognoms = nomicognoms.getText();
        String sDNI = dni.getText();
        String sAdreca = adreca.getText();
        afegirEstudiant(sNomicognoms, sDNI, sAdreca);
        Parent llistaAlumnes = FXMLLoader.load(getClass().getResource("sb.fxml"));
        Scene afegirAlumneScene = new Scene(llistaAlumnes, 1000, 700);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
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
