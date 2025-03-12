package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {
    private static final String URL = "jdbc:mysql://localhost:3306/gestion_utilisateurs";
    private static final String UTILISATEUR = "root";
    private static final String MOT_DE_PASSE = "";
    private Connection connexion;

    public Connexion() {
        try {
            this.connexion = DriverManager.getConnection(URL, UTILISATEUR, MOT_DE_PASSE);
            System.out.println("Connexion réussie !");
        } catch (SQLException e) {
            System.err.println("Erreur de connexion : " + e.getMessage());
        }
    }

    public Connection getConnexion() {
        return connexion;
    }

    public void close() {
        try {
            if (this.connexion != null) {
                this.connexion.close();
                System.out.println("Connexion fermée avec succès.");
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la fermeture : " + e.getMessage());
        }
    }
}