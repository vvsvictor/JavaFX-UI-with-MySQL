package App;

import Classes.Assignatura;
import Database.basedadesSqlite;
import static Database.basedadesSqlite.obtenirEstudiants;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.List;
import javafx.scene.control.TextField;

public class afegirAvaluacionsController implements Initializable {

    @FXML
    private JFXComboBox<String> estudiantscb;

    @FXML
    private JFXComboBox<String> curscb;

    @FXML
    private JFXTextField nota;

    @FXML
    private JFXComboBox<String> assignaturacb;

    @FXML
    private JFXButton afegirAvaluacionsBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        afegirAvaluacionsBtn.setDisable(true);
        List estudiants = obtenirEstudiants();
        NumberValidator numberValid = new NumberValidator();
        numberValid.setMessage("El valor introduït no és correcte");
        RequiredFieldValidator validator = new RequiredFieldValidator();
        validator.setMessage("Falten valors d'entrada");
        nota.getValidators().add(validator);
        nota.getValidators().add(numberValid);

        for (int i = 0; i < estudiants.size(); i++) {
            String[] estudiant = (String[]) estudiants.get(i);
            String nom = estudiant[1];
            nom += " - " + estudiant[2];
            estudiantscb.getItems().add(nom);
        }

        List assignatures = basedadesSqlite.obtenirAssignatures();
        for (int i = 0; i < assignatures.size(); i++) {
            Assignatura assignatura = (Assignatura) assignatures.get(i);
            String nom = assignatura.getId();
            nom += " - " + assignatura.getNom();
            assignaturacb.getItems().add(nom);
        }

        for (int i = 2014; i < 2019; i++) {
            curscb.getItems().add(i + "");
        }

    }

    public void validateInput() {
        try {
            String estudiant = estudiantscb.getValue();
            String curs = curscb.getValue();
            String assignatura = assignaturacb.getValue();

            if ((!isDouble(nota)) || estudiant.isEmpty() || curs.isEmpty() || assignatura.isEmpty()) {
                afegirAvaluacionsBtn.setDisable(true);
            } else {
                double dNota = Double.parseDouble(nota.getText());
                if (dNota < 0 || dNota > 10) {
                    afegirAvaluacionsBtn.setDisable(true);
                } else {
                    afegirAvaluacionsBtn.setDisable(false);
                }

            }
        } catch (Exception e) {
        }

    }

    public void addAvaluacio(ActionEvent event) throws IOException {
        String estudiant = estudiantscb.getValue();
        String[] arrEstudiant = estudiant.split(" - ");
        estudiant = arrEstudiant[1];
        String sCurs = curscb.getValue();
        int iCurs = Integer.parseInt(sCurs);
        double dNota = Double.parseDouble(nota.getText());
        String assignatura = assignaturacb.getValue();
        String[] arrAssignatura = assignatura.split(" - ");
        assignatura = arrAssignatura[0];
        int iAssignatura = Integer.parseInt(assignatura);
        basedadesSqlite.afegirAvaluacio(estudiant, iAssignatura, dNota, iCurs);

        changeToAvaluacionsScene(event);
    }

    private boolean isDouble(TextField input) {
        try {
            double dInput = Double.parseDouble(input.getText());
            return true;
        } catch (Exception e) {
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
