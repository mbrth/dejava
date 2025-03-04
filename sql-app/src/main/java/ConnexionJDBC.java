import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConnexionJDBC {
    private Connection connexion;

    public ConnexionJDBC() {
        try {
            String url = "jdbc:mysql://localhost:3306/java";
            String utilisateur = "root";
            String motDePasse = "password";
            connexion = DriverManager.getConnection(url, utilisateur, motDePasse);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ajouterUtilisateur(String nom, String email) {
        try {
            String sql = "INSERT INTO utilisateurs (nom, email) VALUES (?, ?)";
            PreparedStatement stmt = connexion.prepareStatement(sql);
            stmt.setString(1, nom);
            stmt.setString(2, email);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> listerUtilisateurs() {
        List<String> utilisateurs = new ArrayList<>();
        try {
            String sql = "SELECT * FROM utilisateurs";
            Statement stmt = connexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                utilisateurs.add(rs.getInt("id") + " - " + rs.getString("nom") + " - " + rs.getString("email"));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utilisateurs;
    }

    public void supprimerUtilisateur(int id) {
        try {
            String sql = "DELETE FROM utilisateurs WHERE id = ?";
            PreparedStatement stmt = connexion.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modifierUtilisateur(int id, String nom, String email) {
        try {
            String sql = "UPDATE utilisateurs SET nom = ?, email = ? WHERE id = ?";
            PreparedStatement stmt = connexion.prepareStatement(sql);
            stmt.setString(1, nom);
            stmt.setString(2, email);
            stmt.setInt(3, id);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String chercherUtilisateur(String email, String nom) {
        try {
            String sql = "SELECT * FROM utilisateurs WHERE email = ? OR nom = ?";
            PreparedStatement stmt = connexion.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, nom);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String result = rs.getInt("id") + " - " + rs.getString("nom") + " - " + rs.getString("email");
                rs.close();
                stmt.close();
                return result;
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Utilisateur non trouvé";
    }
}
