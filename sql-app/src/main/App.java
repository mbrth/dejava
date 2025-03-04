package com.monprojet;

import java.util.Scanner;

public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World !" );

        Connexion connexion = new Connexion();
        int choix = 0;
        Scanner sc = new Scanner(System.in);
        GestionUtilisateur gu = new GestionUtilisateur();    

        do {
            System.out.println("Que voulez-vous faire ?");
            System.out.println("1 - Ajouter un utilisateur");
            System.out.println("2 - Lister les utilisateurs");
            System.out.println("3 - Supprimer un utilisateur");
            System.out.println("4 - Modifier un utilisateur");
            System.out.println("0 - quitter");
            choix = sc.nextInt();

            switch (choix) {
                case 1:
                    gu.add(connexion, sc);
                    break;
                case 2:
                    gu.listUsers(connexion);
                    break;
                case 3:
                    gu.deleteUser(connexion, sc);
                    break;    
                case 4:
                    gu.editUser(connexion, sc);
                    break;
                default:
                    System.out.println("L'action demandé n'existe pas !");
                    break;
            }

            /* 
            Statement stmt = connexion.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id, nom, email FROM utilisateurs");

            System.out.println("Liste des utilisateurs :");

            // Parcours du ResultSet
            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                String email = rs.getString("email");
                System.out.println("ID : " + id + ", Nom : " + nom + ", Email : " + email);
            }
            */
        } while (choix != 0);
        connexion.close();
        sc.close();
        System.exit(0);
    }
}