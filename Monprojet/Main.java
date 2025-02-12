package Monprojet;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GestionEtudiants gestionEtudiants = new GestionEtudiants();
        Scanner scanner = new Scanner(System.in);
        String choix;

        do {
            System.out.println("Que voulez-vous faire ? (ajouter/supprimer/afficher/quitter)");
            choix = scanner.nextLine();

            switch (choix.toLowerCase()) {
                case "ajouter":
                    System.out.println("Entrez le nom de l'étudiant :");
                    String nom = scanner.nextLine();
                    System.out.println("Entrez le prénom de l'étudiant :");
                    String prenom = scanner.nextLine();
                    System.out.println("Entrez la classe de l'étudiant :");
                    String classe = scanner.nextLine();
                    gestionEtudiants.ajouterEtudiant(new Etudiant(nom, prenom, classe));
                    break;

                case "supprimer":
                    System.out.println("Entrez le nom de l'étudiant à supprimer :");
                    String nomASupprimer = scanner.nextLine();
                    gestionEtudiants.supprimerEtudiantParNom(nomASupprimer);
                    break;

                case "afficher":
                    System.out.println("Liste des étudiants :");
                    gestionEtudiants.afficherEtudiants();
                    break;

                case "quitter":
                    System.out.println("Au revoir !");
                    break;

                default:
                    System.out.println("Choix non reconnu. Veuillez réessayer.");
                    break;
            }
        } while (!choix.equalsIgnoreCase("quitter"));

        scanner.close();
    }
}