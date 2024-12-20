package dao;

import dto.ProizvodjacCombo;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author 
 */
public class ProizvodjacComboDAO {
    private static final String SQL_GET = "select * from proizvodjac";
    
    public static ObservableList<ProizvodjacCombo> getProizvodjacCombo() {
        
        ProizvodjacCombo text = new ProizvodjacCombo(1, "Izaberi"); 
        ObservableList<ProizvodjacCombo> rezultati //
                = FXCollections.observableArrayList(text);
        
        Connection con = null;
        Statement s = null;
        ResultSet rs = null;

        try {
            con = ConnectionPool.getInstance().checkOut();
            s = con.createStatement();
            rs = s.executeQuery(SQL_GET);

            while (rs.next()){
                int IdProizvodjac = rs.getInt(1);
                String Naziv = rs.getString(2);
                               
            rezultati.add(new ProizvodjacCombo(IdProizvodjac, Naziv));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProizvodjacComboDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                ConnectionPool.getInstance().checkIn(con);
            }
            if (s != null) {
                try {
                    s.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProizvodjacComboDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProizvodjacComboDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return FXCollections.observableArrayList(rezultati);
    }   
}