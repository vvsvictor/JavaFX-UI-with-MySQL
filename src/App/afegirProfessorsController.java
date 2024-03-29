package App;

import Database.basedadesH2;
import Database.basedadesMysql;
import Database.basedadesPostgreSQL;
import Database.basedadesSqlite;
import com.jfoenix.controls.JFXButton;
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

public class afegirProfessorsController implements Initializable {

    @FXML
    private JFXTextField nomicognoms;

    @FXML
    private JFXTextField departament;

    @FXML
    private JFXButton afegirProfessorsBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        afegirProfessorsBtn.setDisable(true);
        RequiredFieldValidator validator = new RequiredFieldValidator();

        nomicognoms.getValidators().add(validator);
        departament.getValidators().add(validator);
        validator.setMessage("Falten valors d'entrada");

        nomicognoms.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldVAlue, Boolean newValue) -> {
            if (!newValue) {
                nomicognoms.validate();
            }
        });

        departament.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldVAlue, Boolean newValue) -> {
            if (!newValue) {
                departament.validate();
            }
        });

    }

    public void inputValidator() {
        nomicognoms.validate();
        departament.validate();
        String sNomicognoms = nomicognoms.getText();
        String sDepartament = departament.getText();
        boolean correctInput;
        correctInput = !(sNomicognoms.isEmpty() || sDepartament.isEmpty());
        if (correctInput) {
            afegirProfessorsBtn.setDisable(false);
        } else {
            afegirProfessorsBtn.setDisable(true);
        }
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
        String sNomicognoms = nomicognoms.getText();
        String sDepartament = departament.getText();
        String database = paginaInicial.getDatabase();
        //Multiple databases
        switch (database) {
            case "H2":
                basedadesH2.afegirProfessor(sNomicognoms, sDepartament);
                break;
            case "MySQl":
                basedadesMysql.afegirProfessor(sNomicognoms, sDepartament);
                break;
            case "SqLite":
                basedadesSqlite.afegirProfessor(sNomicognoms, sDepartament);
                break;
            case "PostgreSQL":
                basedadesPostgreSQL.afegirProfessor(sNomicognoms, sDepartament);
                break;
            default:
                throw new AssertionError();
        }
        Parent llistaAlumnes = FXMLLoader.load(getClass().getResource("llistaProfessors.fxml"));
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
