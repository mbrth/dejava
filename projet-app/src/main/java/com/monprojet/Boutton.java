package com.monprojet;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;

public class Boutton {

    @FXML
    private TextField nomField, prenomField, emailField;
    @FXML
    private TableView<User> tableUtilisateurs;
    @FXML
    private TableColumn<User, Integer> colId;
    @FXML
    private TableColumn<User, String> colNom;
    @FXML
    private TableColumn<User, String> colPrenom;
    @FXML
    private TableColumn<User, String> colEmail;

    private Connection connexion = Connexion.getConnexion(); // Utilisation de la connexion centralisée
    private ObservableList<User> usersList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colId.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        colNom.setCellValueFactory(cellData -> cellData.getValue().nomProperty());
        colPrenom.setCellValueFactory(cellData -> cellData.getValue().prenomProperty());
        colEmail.setCellValueFactory(cellData -> cellData.getValue().emailProperty());

        chargerUtilisateurs(); // Charger les utilisateurs au démarrage
    }

    @FXML
    public void ajouterUtilisateur() {
        String nom = nomField.getText();
        String prenom = prenomField.getText();
        String email = emailField.getText();

        if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty()) {
            showAlert("Erreur", "Veuillez remplir tous les champs !");
            return;
        }

        try {
            String sql = "INSERT INTO utilisateurs (nom, prenom, email) VALUES (?, ?, ?)";
            PreparedStatement pstmt = connexion.prepareStatement(sql);
            pstmt.setString(1, nom);
            pstmt.setString(2, prenom);
            pstmt.setString(3, email);
            pstmt.executeUpdate();
            showAlert("Succès", "Utilisateur ajouté !");
            chargerUtilisateurs();
        } catch (SQLException e) {
            showAlert("Erreur SQL", e.getMessage());
        }
    }

    @FXML
    public void supprimerUtilisateur() {
        User selectedUser = tableUtilisateurs.getSelectionModel().getSelectedItem();
        if (selectedUser == null) {
            showAlert("Erreur", "Veuillez sélectionner un utilisateur !");
            return;
        }

        try {
            String sql = "DELETE FROM utilisateurs WHERE id = ?";
            PreparedStatement pstmt = connexion.prepareStatement(sql);
            pstmt.setInt(1, selectedUser.getId());
            pstmt.executeUpdate();
            showAlert("Succès", "Utilisateur supprimé !");
            chargerUtilisateurs();
        } catch (SQLException e) {
            showAlert("Erreur SQL", e.getMessage());
        }
    }

    @FXML
    public void modifierUtilisateur() {
        User selectedUser = tableUtilisateurs.getSelectionModel().getSelectedItem();
        if (selectedUser == null) {
            showAlert("Erreur", "Veuillez sélectionner un utilisateur !");
            return;
        }

        try {
            String sql = "UPDATE utilisateurs SET nom = ?, prenom = ?, email = ? WHERE id = ?";
            PreparedStatement pstmt = connexion.prepareStatement(sql);
            pstmt.setString(1, nomField.getText());
            pstmt.setString(2, prenomField.getText());
            pstmt.setString(3, emailField.getText());
            pstmt.setInt(4, selectedUser.getId());
            pstmt.executeUpdate();
            showAlert("Succès", "Utilisateur modifié !");
            chargerUtilisateurs();
        } catch (SQLException e) {
            showAlert("Erreur SQL", e.getMessage());
        }
    }

    @FXML
    public void rechercherUtilisateur() {
        String keyword = nomField.getText();
        if (keyword.isEmpty()) {
            showAlert("Erreur", "Entrez un nom ou un email !");
            return;
        }

        try {
            usersList.clear();
            String sql = "SELECT * FROM utilisateurs WHERE nom LIKE ? OR email LIKE ?";
            PreparedStatement pstmt = connexion.prepareStatement(sql);
            pstmt.setString(1, "%" + keyword + "%");
            pstmt.setString(2, "%" + keyword + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                usersList.add(new User(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email")));
            }
            tableUtilisateurs.setItems(usersList);
        } catch (SQLException e) {
            showAlert("Erreur SQL", e.getMessage());
        }
    }

    @FXML
    public void chargerUtilisateurs() {
        usersList.clear();
        try {
            String sql = "SELECT * FROM utilisateurs";
            PreparedStatement pstmt = connexion.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                usersList.add(new User(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email")));
            }
            tableUtilisateurs.setItems(usersList);
        } catch (SQLException e) {
            showAlert("Erreur SQL", e.getMessage());
        }
    }

    private void showAlert(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
