import java.util.Scanner;

public class CompteurMots {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Demander à l'utilisateur de saisir un texte
        System.out.print("Veuillez entrer un texte : ");
        String texte = scanner.nextLine();
        
        // Vérifier si le texte n'est pas vide après suppression des espaces inutiles
        if (texte.trim().isEmpty()) {
            System.out.println("Vous n'avez entré aucun mot.");
        } else {
            // Séparer les mots en utilisant les espaces comme délimiteur
            String[] mots = texte.trim().split("\\s+");
            
            // Afficher le nombre de mots
            System.out.println("Nombre de mots : " + mots.length);
        }
        
        scanner.close();
    }
}