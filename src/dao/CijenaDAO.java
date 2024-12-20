package dao;

import dto.Cijena;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author 
 */
public class CijenaDAO {
     private static final String SQL_GET = "select IdCijena, NabavnaCijena, ProdajnaCijena, Barkod, IdTipArtikla from cijena;";
    
    public static ObservableList<Cijena> getCijena() {
       
        Connection con = null;
        Statement s = null;
        ResultSet rs = null;
        ArrayList<Cijena> rezultati = new ArrayList<>();

        try {
            con = ConnectionPool.getInstance().checkOut();
            s = con.createStatement();
            rs = s.executeQuery(SQL_GET);

            while (rs.next()){
                int IdCijena = rs.getInt(1);
                double NabavnaCijena = rs.getDouble(2);
                double ProdajnaCijena = rs.getDouble(3);
                int Barkod = rs.getInt(4);
                int IdTipArtikla = rs.getInt(5);
                
                               
            rezultati.add(new Cijena(IdCijena, NabavnaCijena, ProdajnaCijena, Barkod, IdTipArtikla));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CijenaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                ConnectionPool.getInstance().checkIn(con);
            }
            if (s != null) {
                try {
                    s.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CijenaDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CijenaDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return FXCollections.observableArrayList(rezultati);
    }
}