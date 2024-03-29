package App;

import Classes.Professor;
import Database.basedadesH2;
import Database.basedadesMysql;
import Database.basedadesPostgreSQL;
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

public class llistaProfessorsController implements Initializable {

    @FXML
    private TableView<Professor> tableView;
    @FXML
    private TableColumn<Professor, String> nomProfessor;
    @FXML
    private TableColumn<Professor, String> departamentProfessor;
    @FXML
    private TableColumn<Professor, String> idProfessor;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Set uop the columns in the table
        idProfessor.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomProfessor.setCellValueFactory(new PropertyValueFactory<>("nom"));
        departamentProfessor.setCellValueFactory(new PropertyValueFactory<>("departament"));

        //Load data
        tableView.setItems(getProfessors());
    }

    public ObservableList getProfessors() {
        ObservableList<Professor> professors = FXCollections.observableArrayList();
        List llistaProfessors;

        String database = paginaInicial.getDatabase();
        //Multiple databases
        switch (database) {
            case "H2":
                llistaProfessors = basedadesH2.obtenirProfessors();
                break;
            case "MySQl":
                llistaProfessors = basedadesMysql.obtenirProfessors();
                break;
            case "SqLite":
                llistaProfessors = basedadesSqlite.obtenirProfessors();
                break;
            case "PostgreSQL":
                llistaProfessors = basedadesPostgreSQL.obtenirProfessors();
                break;
            default:
                throw new AssertionError();
        }
        for (int i = 0; i < llistaProfessors.size(); i++) {
            //Array amb les dades del professor
            Professor professor = (Professor) llistaProfessors.get(i);
            professors.add(professor);
        }

        return professors;
    }

    public void changeToAlumnesScene(ActionEvent event) throws IOException {
        Parent alumnes = FXMLLoader.load(getClass().getResource("sb.fxml"));
        Scene alumnesScene = new Scene(alumnes, 1000, 700);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(alumnesScene);
        window.show();
    }

    public void changeToafegirProfessors(ActionEvent event) throws IOException {
        Parent afegirAlumne = FXMLLoader.load(getClass().getResource("afegirProfessors.fxml"));
        Scene afegirAlumneScene = new Scene(afegirAlumne, 1000, 700);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(afegirAlumneScene);
        window.show();
    }

    public void changeToAssignaturesScene(ActionEvent event) throws IOException {
        Parent alumnes = FXMLLoader.load(getClass().getResource("llistaAssignatures.fxml"));
        Scene alumnesScene = new Scene(alumnes, 1000, 700);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(alumnesScene);
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
