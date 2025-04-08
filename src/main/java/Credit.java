package credit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connexion.MyConnection;

public class Credit {
    int idCredit;
    String libelle;
    float montant;
    
    public Credit(int idCredit, String libelle, float montant) {
        this.idCredit = idCredit;
        this.libelle = libelle;
        this.montant = montant;
    }

    public Credit(String libelle, float montant) {
        this.libelle = libelle;
        this.montant = montant;
    }

    public Credit() {
    }

    public int getIdCredit() {
        return this.idCredit;
    }

    public void setIdCredit(int idCredit) {
        this.idCredit = idCredit;
    }

    public String getLibelle() {
        return this.libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public float getMontant() {
        return this.montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }
    

    public void save() throws Exception {
        try {
            MyConnection mycon = new MyConnection();
            Connection connection = mycon.getConnection();
            this.save(connection);
        } catch (Exception e) {
            e.printStackTrace();
            throw e; 
        }
    }

    public void save(Connection connection) throws Exception {
        String query = "INSERT INTO WebDyn_credit(libelle, montant) VALUES (?,?)";
        
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            
            ps.setString(1, this.getLibelle());
            ps.setFloat(2, this.getMontant());
            ps.executeUpdate();
            System.out.println("Donnees inserees avec succes");

        } catch (Exception e) {
            e.printStackTrace();
            throw e; 
        }
    }

    public static List<Credit> findAll() throws Exception{
        try {
            MyConnection mycon = new MyConnection();
            Connection connection = mycon.getConnection();
            return Credit.findAll(connection);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static List<Credit> findAll(Connection connection) throws Exception {
        List<Credit> list = new ArrayList<>();
        String query = "SELECT * FROM WebDyn_credit";

        try (PreparedStatement ps = connection.prepareStatement(query);
                ResultSet result = ps.executeQuery()) {

                while(result.next()) {
                    int idCredit = result.getInt("idCredit");
                    String libelle = result.getString("libelle");
                    float montant = result.getFloat("montant");

                    Credit temp = new Credit();
                    temp.setIdCredit(idCredit);
                    temp.setLibelle(libelle);
                    temp.setMontant(montant);

                    list.add(temp);
                }
        } catch(SQLException e) {
            e.printStackTrace();
            throw e;
        } 

        return list;
    }

    public static Credit findById(int idCredit) throws Exception{
        try {
            MyConnection mycon = new MyConnection();
            Connection connection = mycon.getConnection();
            return findById(connection, idCredit);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static Credit findById(Connection connection, int idCredit) throws Exception{
        Credit c = null;
        String query = "SELECT * FROM WebDyn_credit WHERE idCredit=?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {

                ps.setInt(1, idCredit);
                ResultSet result = ps.executeQuery();

                if(result.next()) {
                    int idCr = result.getInt("idCredit");
                    String libelle = result.getString("libelle");
                    float montant = result.getFloat("montant");

                    Credit temp = new Credit();
                    temp.setIdCredit(idCr);
                    temp.setLibelle(libelle);
                    temp.setMontant(montant);

                    return temp;
                }
        } catch(SQLException e) {
            e.printStackTrace();
            throw e;
        } 
        return c;
    }


    public void update() throws Exception{
        try {
            MyConnection mycon = new MyConnection();
            Connection connection = mycon.getConnection();
            this.update(connection);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void update(Connection connection) throws Exception{
        String query = "UPDATE WebDyn_credit SET libelle=?, montant=? WHERE idCredit=?";
        
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            
            ps.setString(1, this.getLibelle());
            ps.setFloat(2, this.getMontant());
            ps.setInt(3, this.getIdCredit());
            ps.executeUpdate();
            System.out.println("Donnees modifiees avec succes");
            
        } catch (Exception e) {
            e.printStackTrace();
            throw e; 
        }
    }    

    public void remove() throws Exception{
        try {
            MyConnection mycon = new MyConnection();
            Connection connection = mycon.getConnection();
            this.remove(connection);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void remove(Connection connection) throws Exception{
        String query = "DELETE FROM WebDyn_credit WHERE idCredit=?";
        
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            
            ps.setInt(1, this.getIdCredit());
            ps.executeUpdate();
            System.out.println("Donnees modifiees avec succes");
            
        } catch (Exception e) {
            e.printStackTrace();
            throw e; 
        }
    }    

    public float getTotalDepense() throws Exception{
        try {
            MyConnection mycon = new MyConnection();
            Connection connection = mycon.getConnection();
            return this.getTotalDepense(connection);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public float getTotalDepense(Connection connection) throws Exception{
        float resultat = 0f;
        String query = "SELECT SUM(montant) as total FROM WebDyn_depense WHERE idCredit=?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {

                ps.setInt(1, this.getIdCredit());
                ResultSet result = ps.executeQuery();

                if(result.next()) {
                    float montant = result.getFloat("total");
                    return montant;
                }

        } catch(SQLException e) {
            e.printStackTrace();
            throw e;
        } 
        return resultat;
    }

    public float getReste(Connection connection) throws Exception {
        try {
            float total = this.getMontant();
            float depense = this.getTotalDepense(connection);
            float result = total - depense;
            return result;
    
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public float getReste() throws Exception {
        try {
            MyConnection mycon = new MyConnection();
            Connection connection = mycon.getConnection();
            return this.getReste(connection);
    
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
