package dao;

import dto.KozmetickiAsortiman;
import java.sql.CallableStatement;
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
public class KozmetickiAsortimanDAO {
    private static final String SQL_SELECT = "select a.Barkod, a.NazivArtikla, c.NabavnaCijena, c.ProdajnaCijena, p.Naziv, JedinicaMjere, ta.NazivTipa\n" +
"                                             from kozmetika k\n" +
"                                             inner join artikal a on k.Barkod = a.Barkod\n" +
"                                             inner join cijena c on c.Barkod = a.Barkod\n" +
"                                             inner join tip_artikla ta on ta.IdTipArtikla= c.IdTipArtikla\n" +
"                                             inner join proizvodjac p on p.IdProizvodjac = a.IdProizvodjac order by a.Barkod;";
      
    public static ObservableList<KozmetickiAsortiman> getKozmetickiAsortiman() {
        
        Connection con = null;
        Statement s = null;
        ResultSet rs = null;
        ArrayList<KozmetickiAsortiman> rezultati = new ArrayList<>();

        try {
            con = ConnectionPool.getInstance().checkOut();
            s = con.createStatement();
            rs = s.executeQuery(SQL_SELECT);

            while (rs.next()){
                int Barkod = rs.getInt(1);
                String NazivArtikla = rs.getString(2);            
                double NabavnaCijena = rs.getDouble(3);
                double ProdajnaCijena = rs.getDouble(4);
                String Proizvodjac = rs.getString(5);
                String JedinicaMjere = rs.getString(6);
                String NazivTipa = rs.getString(7);
                               
            rezultati.add(new KozmetickiAsortiman(Barkod, NazivArtikla, NabavnaCijena, ProdajnaCijena, Proizvodjac, JedinicaMjere, NazivTipa));
            }
        } catch (SQLException ex) {
            Logger.getLogger(KozmetickiAsortimanDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                ConnectionPool.getInstance().checkIn(con);
            }
            if (s != null) {
                try {
                    s.close();
                } catch (SQLException ex) {
                    Logger.getLogger(KozmetickiAsortimanDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(KozmetickiAsortimanDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return FXCollections.observableArrayList(rezultati);
    }
    
    public static boolean upisiKozmetickiAsortiman(int Barkod, String NazivArtikla, double NabavnaCijena, double ProdajnaCijena, int Proizvodjac,  String JedinicaMjere, int NazivTipa){
        boolean retValue = false;
        Connection con = null;
        CallableStatement cs = null;
        
        String query = "{call dodaj_kozmeticki_asortiman(?,?,?,?,?,?,?)}";
        
        try{
            con = ConnectionPool.getInstance().checkOut();
            cs = con.prepareCall(query);
            
            cs.setInt(1, Barkod);
            cs.setString(2, NazivArtikla);
            cs.setDouble(3, NabavnaCijena);  
            cs.setDouble(4, ProdajnaCijena);
            cs.setInt(5, Proizvodjac);
            cs.setString(6, JedinicaMjere);
            cs.setInt(7, NazivTipa);
            
            retValue = cs.executeUpdate() == 1;
                       
        }catch (SQLException ex) {
            Logger.getLogger(KozmetickiAsortimanDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                ConnectionPool.getInstance().checkIn(con);
            }
            if (cs != null) {
                try {
                    cs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(KozmetickiAsortimanDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return retValue; 
    }
    
    public static boolean izmijeniKozmetickiAsortiman(int Barkod, String NazivArtikla, int Proizvodjac, String JedinicaMjere) {
        boolean retValue = false;
        Connection con = null;
        CallableStatement cs = null;

        String query = "{call azuriraj_kozmeticki_asortiman(?,?,?,?)}";
        try {
            con = ConnectionPool.getInstance().checkOut();
            cs = con.prepareCall(query);
            
            cs.setInt(1, Barkod);
            cs.setString(2, NazivArtikla);
            cs.setInt(3, Proizvodjac);
            cs.setString(4, JedinicaMjere);
            
            retValue = cs.executeUpdate() == 1;

        }catch (SQLException ex) {
            Logger.getLogger(KozmetickiAsortimanDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                ConnectionPool.getInstance().checkIn(con);
            }
            if (cs != null) {
                try {
                    cs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(KozmetickiAsortimanDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return retValue;
    }   
}