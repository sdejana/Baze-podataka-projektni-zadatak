package dao;

/**
 *
 * @author 
 */
import dto.Artikal;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ArtikalDAO {

    private static final String SQL_GET = "select * from artikal order by Barkod";
    private static final String SQL_GET_ARTIKAL_KATEGORIJA =
            "SELECT a.NazivArtikla, 'Kozmetika' AS Kategorija " +
                    "FROM apoteka.ARTIKAL a " +
                    "JOIN apoteka.KOZMETICKI_ASORTIMAN k ON a.Barkod = k.Barkod " +
                    "WHERE a.Barkod = ? " +
                    "UNION " +
                    "SELECT a.NazivArtikla, 'Lijek' AS Kategorija " +
                    "FROM apoteka.ARTIKAL a " +
                    "JOIN apoteka.LIJEK l ON a.Barkod = l.Barkod " +
                    "WHERE a.Barkod = ? " +
                    "UNION " +
                    "SELECT a.NazivArtikla, 'Medicinsko pomagalo' AS Kategorija " +
                    "FROM apoteka.ARTIKAL a " +
                    "JOIN apoteka.MEDICINSKO_POMAGALO m ON a.Barkod = m.Barkod " +
                    "WHERE a.Barkod = ?";


    public static ObservableList<Artikal> getArtikal() {
        Connection con = null;
        Statement s = null;
        ResultSet rs = null;
        ArrayList<Artikal> rezultati = new ArrayList<>();

        try {
            con = ConnectionPool.getInstance().checkOut();
            s = con.createStatement();
            rs = s.executeQuery(SQL_GET);

            while (rs.next()) {
                int Barkod = rs.getInt(1);
                String NazivArtikla = rs.getString(2);
                int Zaliha = rs.getInt(3);
                int IdProizvodjac = rs.getInt(4);

                rezultati.add(new Artikal(Barkod, NazivArtikla, Zaliha, IdProizvodjac));

            }
        } catch (SQLException ex) {
            Logger.getLogger(Artikal.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                ConnectionPool.getInstance().checkIn(con);
            }
            if (s != null) {
                try {
                    s.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Artikal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Artikal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return FXCollections.observableArrayList(rezultati);
    }

    public static String getArtikalKategorijaByBarkod(int barkod) {
        Connection con = null;
        java.sql.PreparedStatement ps = null;
        ResultSet rs = null;
        String rezultat = null;

        try {
            con = ConnectionPool.getInstance().checkOut();
            ps = con.prepareStatement(SQL_GET_ARTIKAL_KATEGORIJA);
            ps.setInt(1, barkod);  // Postavljamo barkod za prvi upit
            ps.setInt(2, barkod);  // Postavljamo barkod za drugi upit
            ps.setInt(3, barkod);  // Postavljamo barkod za treći upit

            rs = ps.executeQuery();

            if (rs.next()) {
                String nazivArtikla = rs.getString("NazivArtikla");
                String kategorija = rs.getString("Kategorija");
                rezultat = kategorija;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ArtikalDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                ConnectionPool.getInstance().checkIn(con);
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ArtikalDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ArtikalDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return rezultat;
    }

    public static boolean dodajArtikal(int barkod, String naziv, int zaliha, int idProizvodjac, String kategorija) {
        Connection con = null;
        CallableStatement cs = null;
        boolean uspjeh = false;

        try {
            con = ConnectionPool.getInstance().checkOut();
            con.setAutoCommit(false);

            // Dodavanje artikla
            cs = con.prepareCall("{call dodaj_artikal(?, ?, ?, ?)}");
            cs.setInt(1, barkod);
            cs.setString(2, naziv);
            cs.setInt(3, zaliha);
            cs.setInt(4, idProizvodjac);
            cs.execute();

            // Pozivanje odgovarajuće procedure na osnovu kategorije
            switch (kategorija.toLowerCase()) {
                case "lijek":
                    cs = con.prepareCall("{call dodaj_lijek(?, ?, ?, ?, ?, ?, ?)}");
                    cs.setInt(1, barkod);
                    cs.setString(2, naziv);
                    cs.setInt(3, 1); // Postavi odgovarajući IdTipArtikla
                    cs.setBigDecimal(4, new BigDecimal("0.00")); // Nabavna cijena
                    cs.setBigDecimal(5, new BigDecimal("0.00")); // Prodajna cijena
                    cs.setInt(6, idProizvodjac);
                    cs.setString(7, "nn"); // Genericki naziv
                    cs.execute();
                    break;
                case "kozmetika":
                    cs = con.prepareCall("{call dodaj_kozmetika(?, ?, ?, ?, ?, ?)}");
                    cs.setInt(1, barkod);
                    cs.setString(2, naziv);
                    cs.setBigDecimal(3, new BigDecimal("0.00")); // Nabavna cijena
                    cs.setBigDecimal(4, new BigDecimal("0.00")); // Prodajna cijena
                    cs.setInt(5, idProizvodjac);
                    cs.setString(6, "ml"); // Jedinica mjere
                    cs.execute();
                    break;
                case "medicinsko pomagalo":
                    cs = con.prepareCall("{call dodaj_medicinska_pomagala(?, ?, ?, ?, ?)}");
                    cs.setInt(1, barkod);
                    cs.setString(2, naziv);
                    cs.setBigDecimal(3, new BigDecimal("0.00")); // Nabavna cijena
                    cs.setBigDecimal(4, new BigDecimal("0.00")); // Prodajna cijena
                    cs.setInt(5, idProizvodjac);
                    cs.execute();
                    break;
                default:
                    throw new IllegalArgumentException("Nepoznata kategorija: " + kategorija);
            }

            con.commit();
            uspjeh = true;
        } catch (SQLException e) {
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            if (cs != null) {
                try {
                    cs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                ConnectionPool.getInstance().checkIn(con);
            }
        }

        return uspjeh;
    }

    public static Artikal getArtikalByBarkod(int barkod) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Artikal artikal = null;

        try {
            con = ConnectionPool.getInstance().checkOut();
            ps = con.prepareStatement("SELECT * FROM artikal WHERE Barkod = ?");
            ps.setInt(1, barkod);

            rs = ps.executeQuery();

            if (rs.next()) {
                int idBarkod = rs.getInt("Barkod");
                String nazivArtikla = rs.getString("NazivArtikla");
                int zaliha = rs.getInt("Zaliha");
                int idProizvodjac = rs.getInt("IdProizvodjac");

                artikal = new Artikal(idBarkod, nazivArtikla, zaliha, idProizvodjac);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ArtikalDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                ConnectionPool.getInstance().checkIn(con);
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ArtikalDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ArtikalDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return artikal;
    }

}