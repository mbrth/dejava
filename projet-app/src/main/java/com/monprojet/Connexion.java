package com.monprojet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {
    private static final String URL = "jdbc:mysql://localhost:3306/gestion_utilisateurs";
    private static final String UTILISATEUR = "root";
    private static final String MOT_DE_PASSE = "";

    private static Connection connexion;

    public static Connection getConnexion() {
        if (connexion == null) {
            try {
                connexion = DriverManager.getConnection(URL, UTILISATEUR, MOT_DE_PASSE);
                System.out.println("Connexion a la base de donnees reussie !");
            } catch (SQLException e) {
                System.err.println("Erreur de connexion : " + e.getMessage());
            }
        }
        return connexion;
    }

    public static void fermerConnexion() {
        if (connexion != null) {
            try {
                connexion.close();
                System.out.println("Connexion fermee.");
            } catch (SQLException e) {
                System.err.println("Erreur lors de la fermeture : " + e.getMessage());
            }
        }
    }
}