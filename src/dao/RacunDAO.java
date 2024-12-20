package dao;


import dto.RacunStavka;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;

/**
 *
 * @author 
 */
public class RacunDAO {
 
    public static boolean prodaj(ObservableList<RacunStavka> lista){
        Connection con = null;
        CallableStatement cs = null;

        try {
            con = ConnectionPool.getInstance().checkOut();
            cs = con.prepareCall("{call_dodaj_racun(?)}");
            
            int idZaposlenika = lista.get(0).IdZaposlenika;
        
            cs.setInt(1, idZaposlenika);
            
            cs.executeUpdate(); 
                       
            cs = null;
            cs = con.prepareCall("{call dodaj_racun_stavka(?,?,?)}");
            CallableStatement cs1 = con.prepareCall("{call dodaj_racun_stavka_bez_recepta(?,?,?)}");
                        
            for (int i = 0; i < lista.size(); ++i) {
                
                RacunStavka stavka = lista.get(i);
                     
                int barkod = stavka.Barkod;
                int kolicina = stavka.Kolicina;
                double cijena = stavka.Cijena;
                int idTipArtikla = stavka.IdTipArtikla;

                if (idTipArtikla == 1) {
                    cs.setInt(1, barkod);
                    cs.setDouble(2, cijena);
                    cs.setInt(3, kolicina);
        
                    cs.executeUpdate();   
                }else{
                    cs1.setInt(1, barkod);
                    cs1.setDouble(2, cijena);
                    cs1.setInt(3, kolicina);
        
                    cs1.executeUpdate();                     
                }   
            }            
        } catch (SQLException ex) {
            Logger.getLogger(RacunDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(RacunDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (cs != null) {
                try {
                    cs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(RacunDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return true;
    }
}