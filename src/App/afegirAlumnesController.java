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

/**
 *
 * @author Víctor Vivancos Serrano
 */
public class afegirAlumnesController implements Initializable {

    //Boto javafx
    @FXML
    private JFXButton afegirAlumneBtn;
    //Input text
    @FXML
    private JFXTextField nomicognoms;

    @FXML
    private JFXTextField dni;

    @FXML
    private JFXTextField adreca;

    /**
     * Mètode executat al inicialitzar
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Botó deshabilitat al inici
        afegirAlumneBtn.setDisable(true);
        //Validador de text
        RequiredFieldValidator validator = new RequiredFieldValidator();
        //Afegeixo el validador de text
        nomicognoms.getValidators().add(validator);
        dni.getValidators().add(validator);
        adreca.getValidators().add(validator);
        //missatge del validador
        validator.setMessage("Falten valors d'entrada");
        /**
         * Comprovador de canvi
         */
        nomicognoms.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldVAlue, Boolean newValue) {
                if (correctInput()) {
                    afegirAlumneBtn.setDisable(false);
                }
                if (!newValue) {
                    nomicognoms.validate();
                }
            }
        });
        /**
         * Comprovador de canvi
         */
        dni.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldVAlue, Boolean newValue) {
                if (correctInput()) {
                    afegirAlumneBtn.setDisable(false);
                }
                if (!newValue) {
                    dni.validate();
                }
            }
        });
        /**
         * Comprovador de canvi
         */
        adreca.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldVAlue, Boolean newValue) {
                if (correctInput()) {
                    afegirAlumneBtn.setDisable(false);
                }
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

    public boolean correctInput() {
        String sNomicognoms = nomicognoms.getText();
        String sDNI = dni.getText();
        String sAdreca = adreca.getText();
        return !(sNomicognoms.isEmpty() || sDNI.isEmpty() || sAdreca.isEmpty());
    }

    public void correctInputValidator() {
        adreca.validate();
        nomicognoms.validate();
        dni.validate();
        if (correctInput()) {
            afegirAlumneBtn.setDisable(false);
        } else {
            afegirAlumneBtn.setDisable(true);
        }
    }

    public void afegirAlumne(ActionEvent event) throws IOException {
        String sNomicognoms = nomicognoms.getText();
        String sDNI = dni.getText();
        String sAdreca = adreca.getText();
        String database = paginaInicial.getDatabase();
        boolean correcte = false;
        switch (database) {
            case "H2":
                correcte = basedadesH2.afegirEstudiant(sNomicognoms, sDNI, sAdreca);
                break;
            case "MySQl":
                correcte = basedadesMysql.afegirEstudiant(sNomicognoms, sDNI, sAdreca);
                break;
            case "SqLite":
                correcte = basedadesSqlite.afegirEstudiant(sNomicognoms, sDNI, sAdreca);
                break;
            case "PostgreSQL":
                correcte = basedadesPostgreSQL.afegirEstudiant(sNomicognoms, sDNI, sAdreca);
                break;
            default:
                throw new AssertionError();
        }
        if (correcte) {
            Parent llistaAlumnes = FXMLLoader.load(getClass().getResource("sb.fxml"));
            Scene afegirAlumneScene = new Scene(llistaAlumnes, 1000, 700);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(afegirAlumneScene);
            window.show();
        } else {
          
            afegirAlumneBtn.setDisable(true);
           
        }

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

    public void changeToAssignaturesScene(ActionEvent event) throws IOException {
        Parent professors = FXMLLoader.load(getClass().getResource("llistaAssignatures.fxml"));
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
