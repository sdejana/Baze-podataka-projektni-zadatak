package dto;

/**
 *
 * @author 
 */
public class RacunStavka {
    public int Barkod;
    public String NazivArtikla;
    public int Kolicina;
    public double Cijena;
    public int IdZaposlenika;
    public int IdTipArtikla;

    
    public RacunStavka(int Barkod, String NazivArtikla, int Kolicina, double Cijena, int IdZaposlenika, int IdTipArtikla){
        this.Barkod=Barkod;
        this.NazivArtikla=NazivArtikla;
        this.Kolicina=Kolicina;
        this.Cijena=Cijena;       
        this.IdZaposlenika=IdZaposlenika;
        this.IdTipArtikla=IdTipArtikla;
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
   
    public double getCijena(){
        return Cijena;
    }
        
    public void setCijena(double Cijena){
        this.Cijena=Cijena;
    }
    
    public int getKolicina(){
        return Kolicina;
    }
        
    public void setKolicina(int Kolicina){
        this.Kolicina=Kolicina;
    }
      
    public int getIdZaposlenika(){
        return IdZaposlenika;
    }
        
    public void setIdZaposlenika(int IdZaposlenika){
        this.IdZaposlenika=IdZaposlenika;
    }
  
    public int getIdTipArtikla() {
        return IdTipArtikla;
    }

    public void setIdTipArtikla(int IdTipArtikla) {
        this.IdTipArtikla = IdTipArtikla;
    } 
}