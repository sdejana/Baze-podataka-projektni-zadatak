package dao;

import dto.Zaposlenik;
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
public class ZaposlenikDAO {
    private static final String SQL_GET = "select * from zaposlenik";
    
    public static ObservableList<Zaposlenik> getZaposlenik() {
        
        Zaposlenik text = new Zaposlenik(1, "Izaberi"); 
        ObservableList<Zaposlenik> rezultati //
                = FXCollections.observableArrayList(text);
        
        Connection con = null;
        Statement s = null;
        ResultSet rs = null;

        try {
            con = ConnectionPool.getInstance().checkOut();
            s = con.createStatement();
            rs = s.executeQuery(SQL_GET);

            while (rs.next()){
                int IdZaposlenika = rs.getInt(1);
                String KorisnickoIme = rs.getString(2);
                               
            rezultati.add(new Zaposlenik(IdZaposlenika, KorisnickoIme));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ZaposlenikDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                ConnectionPool.getInstance().checkIn(con);
            }
            if (s != null) {
                try {
                    s.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ZaposlenikDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ZaposlenikDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return FXCollections.observableArrayList(rezultati);
    }
}