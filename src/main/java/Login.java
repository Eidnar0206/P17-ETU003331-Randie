package login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connexion.MyConnection;

public class Login {
    String nom;
    String motdepasse;

    public Login(String nom, String motdepasse) {
        this.nom = nom;
        this.motdepasse = motdepasse;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMotdepasse() {
        return this.motdepasse;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }

    public static Login getLogin() throws Exception{
        Login resultat = null;
        String query = "SELECT * FROM WebDyn_login";
        MyConnection mycon = new MyConnection();
            try (Connection connection = mycon.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {
                    ResultSet result = ps.executeQuery();
                    if(result.next()) {
                        String nom = result.getString("nom");
                        String motdepasse = result.getString("motdepasse");
                        Login l = new Login(nom, motdepasse);
                        return l;
                    }

            } catch(SQLException e) {
                e.printStackTrace();
                throw e;
            } 
            return resultat;
    }

    public static boolean checkLogin(String nom, String mdp) throws Exception {
        try {
            Login log = Login.getLogin();
            if(log.getNom().equals(nom) && log.getMotdepasse().equals(mdp)){
                return true;
            }
            return false;

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
