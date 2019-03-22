package App;

import Classes.Alumne;
import Classes.Assignatura;
import Database.basedades;
import static Database.basedades.afegirProfessor;
import static Database.basedades.obtenirEstudiants;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.List;

public class afegirAvaluacionsController implements Initializable {
//    
//    ObservableList<String> estidiantsList;
//    
            
            
    @FXML
    private JFXComboBox <String> estudiantscb;

    @FXML
    private JFXComboBox  <String>curscb;
    
    @FXML
    private JFXTextField nota;

    @FXML
    private JFXComboBox <String> assignaturacb;


    @FXML
    private JFXButton afegirAvaluacionsBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List estudiants = obtenirEstudiants();
        for (int i = 0; i < estudiants.size(); i++) {
            String [] estudiant = (String[]) estudiants.get(i);
            String nom = estudiant[1];
            nom+=" - "+estudiant[2];
            estudiantscb.getItems().add(nom);
        }
        
        List assignatures = basedades.obtenirAssignatures();
        for (int i = 0; i < assignatures.size(); i++) {
            Assignatura assignatura = (Assignatura) assignatures.get(i);
            String nom = assignatura.getId();
            nom+=" - "+assignatura.getNom();
            assignaturacb.getItems().add(nom);
        }
        
        for (int i = 2014; i < 2019; i++) {
            curscb.getItems().add(i+"");
        }


        
//        afegirProfessorsBtn.setDisable(true);
//        RequiredFieldValidator validator = new RequiredFieldValidator();
//
//        nomicognoms.getValidators().add(validator);
//        departament.getValidators().add(validator);
//        validator.setMessage("Falten valors d'entrada");
//
//        nomicognoms.focusedProperty().addListener(new ChangeListener<Boolean>() {
//            @Override
//            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldVAlue, Boolean newValue) {
//                if (!newValue) {
//                    nomicognoms.validate();
//                }
//            }
//        });
//
//        departament.focusedProperty().addListener(new ChangeListener<Boolean>() {
//            @Override
//            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldVAlue, Boolean newValue) {
//                if (!newValue) {
//                    departament.validate();
//                }
//            }
//        });

    }

    public void inputValidator() {
//        nomicognoms.validate();
//        departament.validate();
//        String sNomicognoms = nomicognoms.getText();
//        String sDepartament = departament.getText();
//        boolean correctInput;
//        correctInput = !(sNomicognoms.isEmpty() || sDepartament.isEmpty());
//        if (correctInput) {
//            afegirProfessorsBtn.setDisable(false);
//        } else {
//            afegirProfessorsBtn.setDisable(true);
//        }
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

    public void afegirProfessors(ActionEvent event) throws IOException {
//        String sNomicognoms = nomicognoms.getText();
//        String sDepartament = departament.getText();
//        afegirProfessor(sNomicognoms, sDepartament);
//        Parent llistaAlumnes = FXMLLoader.load(getClass().getResource("llistaProfessors.fxml"));
//        Scene afegirAlumneScene = new Scene(llistaAlumnes, 1000, 700);
//        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        window.setScene(afegirAlumneScene);
//        window.show();

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
