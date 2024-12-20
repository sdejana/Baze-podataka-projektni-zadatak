package dto;

/**
 *
 * @author 
 */
public class Zaposlenik {
    public int IdZaposlenika;
    public String KorisnickoIme;
    
    
    public Zaposlenik(int IdZaposlenika, String KorisnickoIme){
        this.IdZaposlenika=IdZaposlenika;
        this.KorisnickoIme=KorisnickoIme;
    }
    
    public int getIdZaposlenika(){
        return IdZaposlenika;
    }
    
    public void setIdZaposlenika(int IdZaposlenika){
        this.IdZaposlenika=IdZaposlenika;
    }
    
    public String getKorisnickoIme(){
        return KorisnickoIme;
    }
    
    public void setKorisnickoIme(String KorisnickoIme){
        this.KorisnickoIme=KorisnickoIme;
    }
    
    @Override
    public String toString(){
        return this.KorisnickoIme;
    }
}