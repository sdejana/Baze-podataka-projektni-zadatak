package dao;

import dto.TipArtikla;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author 
 */
public class TipArtiklaDAO {
    private static final String SQL_GET = "select * from tip_artikla";
    
    public static ObservableList<TipArtikla> getTipArtikla() {
        
        TipArtikla text = new TipArtikla(1, "Izaberi"); 
        ObservableList<TipArtikla> rezultati //
                = FXCollections.observableArrayList(text);
        
        Connection con = null;
        Statement s = null;
        ResultSet rs = null;

        try {
            con = ConnectionPool.getInstance().checkOut();
            s = con.createStatement();
            rs = s.executeQuery(SQL_GET);

            while (rs.next()){
                int IdTipArtikla = rs.getInt(1);
                String NazivTipa = rs.getString(2);
                               
            rezultati.add(new TipArtikla(IdTipArtikla, NazivTipa));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TipArtiklaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                ConnectionPool.getInstance().checkIn(con);
            }
            if (s != null) {
                try {
                    s.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TipArtiklaDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TipArtiklaDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return FXCollections.observableArrayList(rezultati);
    }

    public static TipArtikla getTipArtiklaById(int idTipArtikla) {
        TipArtikla tipArtikla = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = ConnectionPool.getInstance().checkOut();
            ps = con.prepareStatement("SELECT * FROM TIP_ARTIKLA WHERE id = ?");
            ps.setInt(1, idTipArtikla);
            rs = ps.executeQuery();

            if (rs.next()) {
                String nazivTipa = rs.getString("naziv_tipa");
                tipArtikla = new TipArtikla(idTipArtikla, nazivTipa);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            // Zatvaranje resursa
        }

        return tipArtikla;
    }

    public static TipArtikla getTipArtiklaByBarcode(int barcode) {
        TipArtikla tipArtikla = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // Otvaranje konekcije
            con = ConnectionPool.getInstance().checkOut();

            // SQL upit koji koristi relacionu tabelu ARTIKAL_TIP_ARTIKLA
            String sql = "SELECT ta.IdTipArtikla, ta.NazivTipa " +
                    "FROM TIP_ARTIKLA ta " +
                    "JOIN ARTIKAL_TIP_ARTIKLA ata ON ta.IdTipArtikla = ata.IdTipArtikla " +
                    "JOIN ARTIKAL a ON a.Barkod = ata.Barkod " +
                    "WHERE a.Barkod = ?";

            ps = con.prepareStatement(sql);
            ps.setInt(1, barcode); // Postavljanje barkoda u upit

            rs = ps.executeQuery();

            // Ako pronađemo rezultat, instanciramo novi TipArtikla objekat
            if (rs.next()) {
                int idTipa = rs.getInt("IdTipArtikla");
                String nazivTipa = rs.getString("NazivTipa");
                tipArtikla = new TipArtikla(idTipa, nazivTipa);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TipArtiklaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Zatvaranje resursa
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TipArtiklaDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TipArtiklaDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (con != null) {
                ConnectionPool.getInstance().checkIn(con);
            }
        }

        return tipArtikla; // Vraćamo pronađeni TipArtikla ili null ako ne postoji
    }

    public static String getKategorijaByBarcode(int barcode) {
        String kategorija = "Nepoznata kategorija"; // Podrazumevana vrednost
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // Otvaranje konekcije
            con = ConnectionPool.getInstance().checkOut();

            // SQL upit koji koristi JOIN-ove da pronađe kategoriju artikla
            String sql = "SELECT \n" +
                    "    CASE \n" +
                    "        WHEN l.Barkod IS NOT NULL THEN 'Lijek'\n" +
                    "        WHEN mp.Barkod IS NOT NULL THEN 'Medicinsko pomagalo'\n" +
                    "        WHEN k.Barkod IS NOT NULL THEN 'Kozmetički asortiman'\n" +
                    "        ELSE 'Nepoznata kategorija'\n" +
                    "    END AS Kategorija\n" +
                    "FROM \n" +
                    "    apoteka.ARTIKAL a\n" +
                    "LEFT JOIN \n" +
                    "    apoteka.LIJEK l ON a.Barkod = l.Barkod\n" +
                    "LEFT JOIN \n" +
                    "    apoteka.MEDICINSKO_POMAGALO mp ON a.Barkod = mp.Barkod\n" +
                    "LEFT JOIN \n" +
                    "    apoteka.KOZMETICKI_ASORTIMAN k ON a.Barkod = k.Barkod\n" +
                    "WHERE \n" +
                    "    a.Barkod = ?;";

            ps = con.prepareStatement(sql);
            ps.setInt(1, barcode); // Postavljanje barkoda u upit

            rs = ps.executeQuery();

            // Ako pronađemo rezultat, postavljamo odgovarajuću kategoriju
            if (rs.next()) {
                kategorija = rs.getString("Kategorija");
            }
        } catch (SQLException ex) {
            Logger.getLogger(TipArtiklaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Zatvaranje resursa
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TipArtiklaDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TipArtiklaDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (con != null) {
                ConnectionPool.getInstance().checkIn(con);
            }
        }

        return kategorija; // Vraćamo kategoriju
    }

}