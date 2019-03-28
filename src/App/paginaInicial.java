package App;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class paginaInicial implements Initializable {

    //String amb totes les bases de dades
    private final String[] DATABASE = {"MySQl", "SqLite", "H2", "PostgreSQL"};
    private static String database;

    @FXML
    private JFXComboBox<String> basesdedades;
    @FXML
    private JFXButton entrabtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        entrabtn.setDisable(true);
        //Obtenció de les bases de dades a ComboBox
        basesdedades.getItems().addAll(Arrays.asList(DATABASE));

    }

    public static String getDatabase() {
        return database;
    }

    /**
     * Mètode validador
     */
    public void validator() {
        String databaseSelected = basesdedades.getValue();
        if (!"".equals(databaseSelected)) {
            entrabtn.setDisable(false);
            database = databaseSelected;
        } else {
            entrabtn.setDisable(true);
        }

    }

    public void enterBtn(ActionEvent event) throws IOException {
        String databaseSelected = basesdedades.getValue();
        if (!"".equals(databaseSelected)) {
            entrabtn.setDisable(false);
            database = databaseSelected;
            changeToListAlumnes(event);
        } else {
            entrabtn.setDisable(true);
        }

    }

    private void changeToListAlumnes(ActionEvent event) throws IOException {
        Parent llistaAlumnes = FXMLLoader.load(getClass().getResource("sb.fxml"));
        Scene afegirAlumneScene = new Scene(llistaAlumnes, 1000, 700);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(afegirAlumneScene);
        window.show();
    }

}
