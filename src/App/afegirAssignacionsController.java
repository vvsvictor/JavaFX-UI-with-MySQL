package App;


import Classes.Assignatura;
import Classes.Professor;
import Database.basedadesSqlite;
import static Database.basedadesSqlite.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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

public class afegirAssignacionsController implements Initializable {
//    
//    ObservableList<String> estidiantsList;
//    

    @FXML
    private JFXComboBox<String> professorscb;

    @FXML
    private JFXComboBox<String> assignaturescb;

    @FXML
    private JFXComboBox<String> curscb;

    @FXML
    private JFXButton afegirAssignacionsBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        afegirAssignacionsBtn.setDisable(true);

        List professors = basedadesSqlite.obtenirProfessors();
        for (int i = 0; i < professors.size(); i++) {
            Professor professor = (Professor) professors.get(i);
            String nom = professor.getId() + " - ";
            nom += professor.getNom();
            professorscb.getItems().add(nom);
        }

        List assignatures = basedadesSqlite.obtenirAssignatures();
        for (int i = 0; i < assignatures.size(); i++) {
            Assignatura assignatura = (Assignatura) assignatures.get(i);
            String nom = assignatura.getId();
            nom += " - " + assignatura.getNom();
            assignaturescb.getItems().add(nom);
        }

        for (int i = 2014; i < 2019; i++) {
            curscb.getItems().add(i + "");
        }

    }

    public void addAssignacio(ActionEvent event) throws IOException {
        String professor = professorscb.getValue();
        String[] arrProfessors = professor.split(" - ");
        professor = arrProfessors[0];
        String sCurs = curscb.getValue();
        int iCurs = Integer.parseInt(sCurs);
        String assignatura = assignaturescb.getValue();
        String[] arrassignatura = assignatura.split(" - ");
        assignatura = arrassignatura[0];
        int idAssignatura = Integer.parseInt(assignatura);
        int idProfessor = Integer.parseInt(professor);
        afegirAssignacio(idProfessor, iCurs, idAssignatura);

        changeToAssignacionsScene(event);
    }

    private boolean isInt(TextField input) {
        try {
            int dInput = Integer.parseInt(input.getText());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void validateInput() {
        try {
            String estudiant = professorscb.getValue();
            String curs = curscb.getValue();
            String assignatura = assignaturescb.getValue();

            if (estudiant.isEmpty() || curs.isEmpty() || assignatura.isEmpty()) {
                afegirAssignacionsBtn.setDisable(true);
            } else {
                afegirAssignacionsBtn.setDisable(false);

            }
        } catch (Exception e) {
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
