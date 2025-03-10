package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class App extends Application {

    private UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
    private Scene scene; // Déclarez la scène ici

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/interface.fxml"));
        scene = new Scene(loader.load(), 400, 400); // Assignez la scène
        primaryStage.setTitle("Gestion des Utilisateurs");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void createUser () {
        try {
            // Ajoute un utilisateur à la base
            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setName("Jean Dupont");
            utilisateur.setEmail("jean.dupont@example.com");
            utilisateurDAO.create(utilisateur);
        } catch (Exception e) {
            e.printStackTrace(); // Gestion des exceptions
        }
    }

    public void listUsers() {
        try {
            // Liste les utilisateurs
            ListView<Utilisateur> listView = (ListView<Utilisateur>) scene.lookup("#userList");
            listView.getItems().clear();
            listView.getItems().addAll(utilisateurDAO.list());
        } catch (Exception e) {
            e.printStackTrace(); // Gestion des exceptions
        }
    }

    public void searchUser () {
        try {
            // Recherche un utilisateur
            Utilisateur utilisateur = utilisateurDAO.search("jean");
            System.out.println(utilisateur);
        } catch (Exception e) {
            e.printStackTrace(); // Gestion des exceptions
        }
    }
}