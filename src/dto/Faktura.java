package dto;

/**
 *
 * @author 
 */
public class Faktura {
    public int BrojFakture;
    public java.sql.Date DatumIzdavanja;
    public double IznosRacuna;   
    public String Proizvodjac;

    
    public Faktura(int BrojFakture, java.sql.Date DatumIzdavanja, double IznosRacuna, String Proizvodjac){
        
        this.BrojFakture=BrojFakture;
        this.DatumIzdavanja=DatumIzdavanja;
        this.IznosRacuna=IznosRacuna;
        this.Proizvodjac=Proizvodjac;
    }
    
    public int getBrojFakture(){
        return BrojFakture;
    }
        
    public void setBrojFakture(int BrojFakture){
        this.BrojFakture=BrojFakture;
    }
    
    public java.sql.Date getDatumIzdavanja() {
        return DatumIzdavanja;
    }
    
    public void setDatumIzdavanja(java.sql.Date DatumIzdavanja) {
        this.DatumIzdavanja = DatumIzdavanja;
    }
   
    public double getIznosRacuna(){
        return IznosRacuna;
    }
        
    public void setIznosRacuna(double IznosRacuna){
        this.IznosRacuna=IznosRacuna;
    }
    
    public String getProizvodjac(){
        return Proizvodjac;
    }
        
    public void setProizvodjac(String Proizvodjac){
        this.Proizvodjac=Proizvodjac;
    }   
}