package depense;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connexion.MyConnection;
import credit.*;

public class Depense {
    int idDepense;
    int idCredit; 
    float montant;
    Date date;

    public Depense() {
    }

    public Depense(int idDepense, int idCredit, float montant, Date date) {
        this.idDepense = idDepense;
        this.idCredit = idCredit;
        this.montant = montant;
        this.date = date;
    }

    public int getIdDepense() {
        return this.idDepense;
    }

    public void setIdDepense(int idDepense) {
        this.idDepense = idDepense;
    }

    public int getIdCredit() {
        return this.idCredit;
    }

    public void setIdCredit(int idCredit) {
        this.idCredit = idCredit;
    }

    public float getMontant() {
        return this.montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
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
        connection.setAutoCommit(false);
        String query = "INSERT INTO WebDyn_depense(idCredit, montant, dateDepense) VALUES (?,?,?)";
        Credit credit = Credit.findById(this.getIdCredit());

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            
            ps.setInt(1, this.getIdCredit());
            ps.setFloat(2, this.getMontant());
            ps.setDate(3, this.getDate());
            ps.executeUpdate();

            if(credit.getReste() < this.getMontant()){
                connection.rollback();
            }
            else{
                connection.commit();
                System.out.println("Donnees inserees avec succes");
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw e; 
        }
    }

    public static List<Depense> findAll() throws Exception{
        try {
            MyConnection mycon = new MyConnection();
            Connection connection = mycon.getConnection();
            return Depense.findAll(connection);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static List<Depense> findAll(Connection connection) throws Exception {
        List<Depense> list = new ArrayList<>();
        String query = "SELECT * FROM WebDyn_depense";

        try (PreparedStatement ps = connection.prepareStatement(query);
                ResultSet result = ps.executeQuery()) {

                while(result.next()) {
                    int idDepense = result.getInt("idDepense");
                    int idCredit = result.getInt("idCredit");
                    float montant = result.getFloat("montant");
                    Date date = result.getDate("dateDepense");

                    Depense temp = new Depense();
                    temp.setIdDepense(idDepense);
                    temp.setIdCredit(idCredit);
                    temp.setMontant(montant);
                    temp.setDate(date);

                    list.add(temp);
                }
        } catch(SQLException e) {
            e.printStackTrace();
            throw e;
        } 

        return list;
    }

    public static List<Depense> findAllOrderByDate() throws Exception{
        try {
            MyConnection mycon = new MyConnection();
            Connection connection = mycon.getConnection();
            return Depense.findAllOrderByDate(connection);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static List<Depense> findAllOrderByDate(Connection connection) throws Exception {
        List<Depense> list = new ArrayList<>();
        String query = "SELECT * FROM WebDyn_depense ORDER BY dateDepense";

        try (PreparedStatement ps = connection.prepareStatement(query);
                ResultSet result = ps.executeQuery()) {

                while(result.next()) {
                    int idDepense = result.getInt("idDepense");
                    int idCredit = result.getInt("idCredit");
                    float montant = result.getFloat("montant");
                    Date date = result.getDate("dateDepense");

                    Depense temp = new Depense();
                    temp.setIdDepense(idDepense);
                    temp.setIdCredit(idCredit);
                    temp.setMontant(montant);
                    temp.setDate(date);

                    list.add(temp);
                }
        } catch(SQLException e) {
            e.printStackTrace();
            throw e;
        } 

        return list;
    }

    public static Depense findById(int idCredit) throws Exception{
        try {
            MyConnection mycon = new MyConnection();
            Connection connection = mycon.getConnection();
            return findById(connection, idCredit);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static Depense findById(Connection connection, int idDepense) throws Exception{
        Depense d = null;
        String query = "SELECT * FROM WebDyn_depense WHERE idDepense=?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {

                ps.setInt(1, idDepense);
                ResultSet result = ps.executeQuery();

                if(result.next()) {
                    int idDep = result.getInt("idDepense");
                    int idCredit = result.getInt("idCredit");
                    float montant = result.getFloat("montant");
                    Date date = result.getDate("dateDepense");

                    Depense temp = new Depense();
                    temp.setIdDepense(idDep);
                    temp.setIdCredit(idCredit);
                    temp.setMontant(montant);
                    temp.setDate(date);

                    return temp;
                }
        } catch(SQLException e) {
            e.printStackTrace();
            throw e;
        } 
        return d;
    }
}

