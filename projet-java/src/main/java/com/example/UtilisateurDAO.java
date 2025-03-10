package com.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtilisateurDAO {
    private Connection conn;

    public UtilisateurDAO() {
        try {
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/gestion_utilisateurs", "root", "");
        } catch (SQLException e) {
            System.err.println("Erreur de connexion à la base de données : " + e.getMessage());
        }
    }

    // Créer un utilisateur
    public void create(Utilisateur utilisateur) {
        String query = "INSERT INTO utilisateurs (name, email) VALUES (?, ?)";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, utilisateur.getName());
            statement.setString(2, utilisateur.getEmail());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur lors de la création de l'utilisateur : " + e.getMessage());
        }
    }

    // Lister tous les utilisateurs
    public List<Utilisateur> list() {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        String query = "SELECT * FROM utilisateurs";
        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Utilisateur utilisateur = new Utilisateur();
                utilisateur.setId(resultSet.getInt("id"));
                utilisateur.setName(resultSet.getString("name"));
                utilisateur.setEmail(resultSet.getString("email"));
                utilisateur.setCreatedAt(resultSet.getTimestamp("createdAt"));
                utilisateur.setUpdatedAt(resultSet.getTimestamp("updatedAt"));
                utilisateurs.add(utilisateur);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la liste des utilisateurs : " + e.getMessage());
        }
        return utilisateurs;
    }

    // Rechercher un utilisateur par email ou nom
    public Utilisateur search(String query) {
        Utilisateur utilisateur = null;
        String sql = "SELECT * FROM utilisateurs WHERE email LIKE ? OR name LIKE ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, "%" + query + "%");
            statement.setString(2, "%" + query + "%");
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                utilisateur = new Utilisateur();
                utilisateur.setId(resultSet.getInt("id"));
                utilisateur.setName(resultSet.getString("name"));
                utilisateur.setEmail(resultSet.getString("email"));
                utilisateur.setCreatedAt(resultSet.getTimestamp("createdAt"));
                utilisateur.setUpdatedAt(resultSet.getTimestamp("updatedAt"));
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la recherche de l'utilisateur : " + e.getMessage());
        }
        return utilisateur;
    }

    // Modifier un utilisateur
    public void update(Utilisateur utilisateur) {
        String query = "UPDATE utilisateurs SET name = ?, email = ? WHERE id = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, utilisateur.getName());
            statement.setString(2, utilisateur.getEmail());
            statement.setInt(3, utilisateur.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur lors de la mise à jour de l'utilisateur : " + e.getMessage());
        }
    }

    // Supprimer un utilisateur
    public void delete(int id) {
        String query = "DELETE FROM utilisateurs WHERE id = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression de l'utilisateur : " + e.getMessage());
        }
    }
}