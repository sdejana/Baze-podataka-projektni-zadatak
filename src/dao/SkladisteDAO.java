package dao;


import dto.Skladiste;
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
public class SkladisteDAO {
  
    public static ObservableList<Skladiste> getSkladiste() {
        ArrayList<Skladiste> rezultati = new ArrayList<>();
        Connection con = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        
        String query = "select * from artikal_pogled";
        try {
            con = ConnectionPool.getInstance().checkOut();
            cs = con.prepareCall(query);
	    rs = cs.executeQuery();

            while (rs.next()) {
                int Barkod = rs.getInt(1);
                String NazivArtikla = rs.getString(2);
                int Zaliha = rs.getInt(3);
                String Proizvodjac = rs.getString(4);
                rezultati.add(new Skladiste(Barkod, NazivArtikla, Zaliha, Proizvodjac));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SkladisteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                ConnectionPool.getInstance().checkIn(con);
            }
            if (cs != null) {
                try {
                    cs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SkladisteDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SkladisteDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return FXCollections.observableArrayList(rezultati);
    }
}