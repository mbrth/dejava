import java.util.Scanner;

public class ask {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Entrez votre nom : ");
        String nom = scanner.nextLine();
        
        System.out.print("Entrez votre âge : ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Correction : consommer la ligne restante après nextInt()

        if (age >= 18) {
            System.out.println("Vous êtes majeur.");
        } else {
            System.out.println("Vous êtes mineur.");
        }

        System.out.print("Entrez votre sexe (Homme/Femme) : ");
        String sexe = scanner.nextLine();

        if (sexe.equalsIgnoreCase("Homme")) {
            System.out.println("Vous êtes un homme.");
        } else if (sexe.equalsIgnoreCase("Femme")) {
            System.out.println("Vous êtes une femme.");
        } else {
            System.out.println("con");
        }

        System.out.println("Bonjour, " + nom + ", vous avez " + age + " ans et vous êtes " + sexe + ".");

        scanner.close();
    }
}
