package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    
    @Override
    public void start(Stage primaryStage) throws Exception{
        // Créer un label
        //Label label = new Label("Bonjour, JavaFX !");

        VBox root = FXMLLoader.load(getClass().getResource("/interface.fxml"));

        // Créer une scène avec le label
        Scene scene = new Scene(root, 300, 200);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        Label messageLabel = (Label) root.lookup("#messageLabel");
        Button clickButton = (Button) root.lookup("#clickButton");
        clickButton.setOnAction(event -> this.handleButtonClick(messageLabel));

   
        // Configurer la fenêtre principale
        primaryStage.setTitle("Ma Première Application javafx");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}