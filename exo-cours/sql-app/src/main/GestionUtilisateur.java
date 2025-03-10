package com.monprojet;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.PreparedStatement;

public class GestionUtilisateur
{
    ArrayList listUser = new ArrayList<>();

    public void add (Connexion connect, Scanner sc)
    {
        sc.nextLine();
        System.out.println("Nom de l'utilisateur");
        String lastName = sc.nextLine();

        System.out.println("Prénom de l'utilisateur");
        String fistName = sc.nextLine();
        
        System.out.println("Email de l'utilisateur");
        String email = sc.nextLine();
        
        try {
            String sqlInsert = "INSERT INTO utilisateurs (prenom, nom, email) VALUES (?, ?, ?)";
            PreparedStatement pstmtInsert = connect.connexion.prepareStatement(sqlInsert);
            pstmtInsert.setString(1, fistName);
            pstmtInsert.setString(2, lastName);
            pstmtInsert.setString(3, email);

            int rowsAffected = pstmtInsert.executeUpdate();
            System.out.println("Insertion réussie, lignes affectées : " + rowsAffected);
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getMessage());
        }
    }

        public void listUsers(Connexion connect) {
        try {
            String sql = "SELECT id, nom, prenom, email FROM utilisateurs";
            PreparedStatement pstmt = connect.connexion.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            System.out.println("\nListe des utilisateurs :");
            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String email = rs.getString("email");

                System.out.println("ID: " + id + " | Nom: " + nom + " | Prénom: " + prenom + " | Email: " + email);
            }

        } catch (SQLException e) {
            System.err.println("Erreur en récupérant les utilisateurs : " + e.getMessage());
        }
    }

    public void deleteUser(Connexion connect, Scanner sc) {
        System.out.println("Entrez l'ID de l'utilisateur à supprimer :");
        int id = sc.nextInt();

        try {
            String sql = "DELETE FROM utilisateurs WHERE id = ?";
            PreparedStatement pstmt = connect.connexion.prepareStatement(sql);
            pstmt.setInt(1, id);

            int rowsAffected = pstmt.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("Utilisateur supprimé avec succès !");
            } else {
                System.out.println("Aucun utilisateur trouvé avec cet ID.");
            }

        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression : " + e.getMessage());
        }
    }

    public void editUser(Connexion connect, Scanner sc) {
        System.out.println("Entrez l'ID de l'utilisateur à modifier :");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.println("Nouveau nom :");
        String newNom = sc.nextLine();

        System.out.println("Nouveau prénom :");
        String newPrenom = sc.nextLine();

        System.out.println("Nouvel email :");
        String newEmail = sc.nextLine();

        try {
            String sql = "UPDATE utilisateurs SET nom = ?, prenom = ?, email = ? WHERE id = ?";
            PreparedStatement pstmt = connect.connexion.prepareStatement(sql);
            pstmt.setString(1, newNom);
            pstmt.setString(2, newPrenom);
            pstmt.setString(3, newEmail);
            pstmt.setInt(4, id);

            int rowsAffected = pstmt.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("Utilisateur modifié avec succès !");
            } else {
                System.out.println("Aucun utilisateur trouvé avec cet ID.");
            }

        } catch (SQLException e) {
            System.err.println("Erreur lors de la modification : " + e.getMessage());
        }
    }


}