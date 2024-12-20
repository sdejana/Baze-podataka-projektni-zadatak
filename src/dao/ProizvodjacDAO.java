package dao;

import dto.Proizvodjac;
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
public class ProizvodjacDAO {
    
    private static final String SQL_GET_PROIZVODJAC = "select p.Naziv, p.Adresa, p.BrojPoste, m.Naziv, p.Telefon\n" +
                                                      "from proizvodjac p\n" +
                                                      "inner join sjediste m on m.BrojPoste = p.BrojPoste order by p.Naziv;";
    
    public static ObservableList<Proizvodjac> getProizvodjac() {
  
        Connection con = null;
        Statement s = null;
        ResultSet rs = null;
        ArrayList<Proizvodjac> rezultati = new ArrayList<>();

        try {
            con = ConnectionPool.getInstance().checkOut();
            s = con.createStatement();
            rs = s.executeQuery(SQL_GET_PROIZVODJAC);

            while (rs.next()){
                String Naziv = rs.getString(1);
                String Adresa = rs.getString(2);
                int BrojPoste = rs.getInt(3);
                String Mjesto = rs.getString(4);
                String Telefon = rs.getString(5);
                               
            rezultati.add(new Proizvodjac(Naziv, Adresa, BrojPoste, Mjesto, Telefon));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProizvodjacDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                ConnectionPool.getInstance().checkIn(con);
            }
            if (s != null) {
                try {
                    s.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProizvodjacDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProizvodjacDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return FXCollections.observableArrayList(rezultati);
    }
    
    public static boolean upisiProizvodjaca(String Naziv, String Adresa, int Mjesto, String Telefon){
        boolean retValue = false;
        Connection con = null;
        CallableStatement cs = null;
        
        String query = "{call dodaj_proizvodjaca(?,?,?,?)}";
        
        try{
            con = ConnectionPool.getInstance().checkOut();
            cs = con.prepareCall(query);
            
            cs.setString(1, Naziv);
            cs.setString(2, Adresa);
            cs.setInt(3, Mjesto);    
            cs.setString(4, Telefon);            
            
            retValue = cs.executeUpdate() == 1;
                       
        }catch (SQLException ex) {
            Logger.getLogger(ProizvodjacDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                ConnectionPool.getInstance().checkIn(con);
            }
            if (cs != null) {
                try {
                    cs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProizvodjacDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return retValue; 
    }
    
    public static boolean izmijeniProizvodjaca(String Naziv, String Adresa, int Mjesto, String Telefon) {
        boolean retValue = false;
        Connection con = null;
        CallableStatement cs = null;

        String query = "{call azuriraj_proizvodjaca(?,?,?,?)}";
        try {
            con = ConnectionPool.getInstance().checkOut();
            cs = con.prepareCall(query);
            
            cs.setString(1, Naziv);
            cs.setString(2, Adresa);
            cs.setInt(3, Mjesto);   
            cs.setString(4, Telefon);
            
            retValue = cs.executeUpdate() == 1;

        }catch (SQLException ex) {
            Logger.getLogger(ProizvodjacDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                ConnectionPool.getInstance().checkIn(con);
            }
            if (cs != null) {
                try {
                    cs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProizvodjacDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return retValue;
    }
      
    public static boolean obrisiProizvodjaca(Proizvodjac proizvodjac) {
		boolean retVal = false;
		Connection con = null;
		CallableStatement cs = null;

		String query = "{call obrisi_proizvodjaca(?)}";
		try {
			con = ConnectionPool.getInstance().checkOut();
			cs = con.prepareCall(query);
			cs.setString(1, proizvodjac.Naziv);
			retVal = cs.executeUpdate() == 1;
		} catch (SQLException ex) {
                        retVal = false;
                } finally {
                    if (con != null) {
                        ConnectionPool.getInstance().checkIn(con);
                    }
                    if (cs != null) {
                        try {
                            cs.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(ProizvodjacDAO.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
	return retVal;
    }
}       