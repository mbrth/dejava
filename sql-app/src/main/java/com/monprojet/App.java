package com.monprojet;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        ConnexionJDBC connexion = new ConnexionJDBC();
        Scanner sc = new Scanner(System.in);
        int choix;

        do {
            System.out.println("Que voulez-vous faire ?");
            System.out.println("1 - Ajouter un utilisateur");
            System.out.println("2 - Afficher les utilisateurs");
            System.out.println("3 - Modifier un utilisateur");
            System.out.println("4 - Supprimer un utilisateur");
            System.out.println("5 - Rechercher un utilisateur");
            System.out.println("10 - Quitter");

            System.out.print("Votre choix : ");
            choix = sc.nextInt();
            sc.nextLine(); // Pour éviter les erreurs de saut de ligne

            switch (choix) {
                case 1:
                    System.out.print("Nom : ");
                    String nom = sc.nextLine();
                    System.out.print("Email : ");
                    String email = sc.nextLine();
                    connexion.ajouterUtilisateur(nom, email);
                    System.out.println("Utilisateur ajouté !");
                    break;

                case 2:
                    System.out.println("Liste des utilisateurs :");
                    for (String utilisateur : connexion.listerUtilisateurs()) {
                        System.out.println(utilisateur);
                    }
                    break;

                case 3:
                    System.out.print("ID de l'utilisateur à modifier : ");
                    int idModif = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Nouveau nom : ");
                    String newNom = sc.nextLine();
                    System.out.print("Nouvel email : ");
                    String newEmail = sc.nextLine();
                    connexion.modifierUtilisateur(idModif, newNom, newEmail);
                    System.out.println("Utilisateur modifié !");
                    break;

                case 4:
                    System.out.print("ID de l'utilisateur à supprimer : ");
                    int idSup = sc.nextInt();
                    connexion.supprimerUtilisateur(idSup);
                    System.out.println("Utilisateur supprimé !");
                    break;

                case 5:
                    System.out.print("Nom ou Email de l'utilisateur à rechercher : ");
                    String search = sc.nextLine();
                    System.out.println("Résultat : " + connexion.chercherUtilisateur(search, search));
                    break;

                case 10:
                    System.out.println("Fermeture du programme...");
                    break;

                default:
                    System.out.println("Choix invalide !");
            }

        } while (choix != 10);

        sc.close();
        System.exit(0);
    }
}
