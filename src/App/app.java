/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Víctor
 */
public class app extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException  {
        
        Parent root = FXMLLoader.load(getClass().getResource("sb.fxml"));
//        Button btn = new Button();
//        btn.setText("Say 'Hello World'");
//        btn.setOnAction(new EventHandler<ActionEvent>() {
//            
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println("Hello World!");
//            }
//        });
        
//        StackPane root = new StackPane();
//        root.getChildren().add(btn);
//        AnchorPane leftPane = new AnchorPane(list, 10);
        
        
        Scene alumnesListScene = new Scene(root, 1000, 700);
        //alumnesListScene.getStylesheets().add("/App/myCss.css");
        primaryStage.setTitle("Classe App");
        primaryStage.setScene(alumnesListScene);
        
        
       
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
