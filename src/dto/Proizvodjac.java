package dto;

/**
 *
 * @author 
 */
public class Proizvodjac {
    public String Naziv;
    public String Adresa;
    public int BrojPoste;
    public String Sjediste;
    public String Telefon;
    
    public Proizvodjac(String Naziv, String Adresa, int BrojPoste, String Sjediste, String Telefon){
  
        this.Naziv=Naziv;
        this.Adresa=Adresa;       
        this.BrojPoste=BrojPoste;
        this.Sjediste=Sjediste;
        this.Telefon=Telefon;
    }

    public Proizvodjac(String naziv, String adresa, int sjediste, String telefon) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 
    public String getNaziv(){
        return Naziv;
    }
    
    public void setNaziv(String Naziv){
        this.Naziv=Naziv;
    }
    
    public String getAdresa(){
        return Adresa;
    }
    
    public void setAdresa(String Adresa){
        this.Adresa=Adresa;
    }
    
    public String getTelefon(){
        return Telefon;
    }
    
    public void setTelefon(String Telefon){
        this.Telefon=Telefon;
    }
    
    public int getBrojPoste(){
        return BrojPoste;
    }
    
    public void setBrojPoste(int BrojPoste){
        this.BrojPoste=BrojPoste;
    }
    
    public String getSjediste(){
        return Sjediste;
    }
    
    public void setSjediste(String Sjediste){
        this.Sjediste=Sjediste;
    }
    
    @Override
    public String toString()  {
        return this.Naziv;
    }   
}