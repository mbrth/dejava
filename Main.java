import java.util.Scanner;

class Voiture {
    String marque;
    String modele;
    String couleur;
    final String[] couleursAutorisees = {"Rouge", "Bleu", "Noir", "Blanc", "Gris"};

    void demarrer() {
        System.out.println("La voiture " + marque + " " + modele + " de couleur " + couleur + " démarre !");
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Voiture v = new Voiture();

        System.out.println("Entrez une marque de voiture :");
        v.marque = scanner.nextLine();

        System.out.println("Entrez un modèle de voiture :");
        v.modele = scanner.nextLine();

        System.out.println("Les couleurs autorisées sont :");
        System.out.println(String.join(", ", v.couleursAutorisees));

        System.out.println("Entrez une couleur de voiture :");
        String couleur = scanner.nextLine();
        boolean found = false;

        // Vérification si la couleur est autorisée
        for (String color : v.couleursAutorisees) {
            if (color.equalsIgnoreCase(couleur)) { // ignore la casse (Rouge = rouge)
                v.couleur = color; // Utilisation de la couleur normalisée
                found = true;
                break;
            }
        }

        if (!found) { 
            System.out.println("La couleur n'est pas autorisée !");
            scanner.close();
            System.exit(1);
        }

        v.demarrer(); 
        scanner.close();
    }
}
