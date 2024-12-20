package dao;


import dto.Recept;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author
 */
public class ReceptDAO {
          
    public static ObservableList<Recept> getRecept() {       
        ArrayList<Recept> rezultati = new ArrayList<>();
        Connection con = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        String query = "select * from recept_pogled";
        try {
            con = ConnectionPool.getInstance().checkOut();
            cs = con.prepareCall(query);
	    rs = cs.executeQuery();
            
            while (rs.next()){
                java.sql.Date DatumIzdavanja = rs.getDate(1);
                String BrojKnjizice=rs.getString(2);
                String JMBG = rs.getString(3);
                int SifraDoktora = rs.getInt(4);
                int Barkod = rs.getInt(5);
                String NazivArtikla = rs.getString(6);
                int Kolicina = rs.getInt(7);
                double ProdajnaCijena = rs.getDouble(8);
                                
            rezultati.add(new Recept(DatumIzdavanja, BrojKnjizice, JMBG, SifraDoktora, Barkod, NazivArtikla, Kolicina, ProdajnaCijena));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReceptDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                ConnectionPool.getInstance().checkIn(con);
            }
            if (cs != null) {
                try {
                    cs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ReceptDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ReceptDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return FXCollections.observableArrayList(rezultati);
    }
    
    public static boolean dodajRecept(java.sql.Date datum, String brojKnjizica, String jmbg, int sifraDoktora){
        boolean retVal = false;
        Connection con = null;
        CallableStatement cs = null;
        
        String query = "{call dodaj_recept(?,?,?,?)}";
        try{
            con = ConnectionPool.getInstance().checkOut();
            cs = con.prepareCall(query);
            
            cs.setDate(1, datum);
            cs.setString(2,brojKnjizica);
            cs.setString(3, jmbg);
            cs.setInt(4, sifraDoktora);
            
            retVal = cs.executeUpdate() == 1;
            
        }catch (SQLException ex) {
            Logger.getLogger(ReceptDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                ConnectionPool.getInstance().checkIn(con);
            }
            if (cs != null) {
                try {
                    cs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ReceptDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }          
        }
        return retVal;
    }   
}