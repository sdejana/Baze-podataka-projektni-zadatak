package dto;

/**
 *
 * @author 
 */
public class FakturaStavka { 
   
    public int BrojFakture;
    public int Barkod;
    public String NazivArtikla;
    public int Kolicina;
    public double CijenaStavke;   
    public int IdProizvodjaca;
    public int IdZaposlenog;

    public FakturaStavka(int BrojFakture, int Barkod, String NazivArtikla,  int Kolicina, double CijenaStavke, int IdProizvodjaca, int IdZaposlenog){       
        this.BrojFakture=BrojFakture;
        this.Barkod=Barkod;
        this.NazivArtikla=NazivArtikla;
        this.CijenaStavke=CijenaStavke;
        this.Kolicina=Kolicina;
        this.IdProizvodjaca=IdProizvodjaca;
        this.IdZaposlenog=IdZaposlenog;
    }
    
    public int getBrojFakture(){
        return BrojFakture;
    }
        
    public void setBrojFakture(int BrojFakture){
        this.BrojFakture=BrojFakture;
    }
    
    public int getBarkod(){
        return Barkod;
    }
        
    public void setBarkod(int Barkod){
        this.Barkod=Barkod;
    }
    
    public String getNazivArtikla(){
        return NazivArtikla;
    }
        
    public void setNazivArtikla(String NazivArtikla){
        this.NazivArtikla=NazivArtikla;
    }
   
    public double getCijenaStavke(){
        return CijenaStavke;
    }
        
    public void setCijenaStavke(double CijenaStavke){
        this.CijenaStavke=CijenaStavke;
    }
    
    public int getKolicina(){
        return Kolicina;
    }
        
    public void setKolicina(int Kolicina){
        this.Kolicina=Kolicina;
    }
    
    public int getIdZaposlenika(){
        return IdZaposlenog;
    }
        
    public void setIdZaposlenika(int IdZaposlenika){
        this.IdZaposlenog=IdZaposlenika;
    }
}