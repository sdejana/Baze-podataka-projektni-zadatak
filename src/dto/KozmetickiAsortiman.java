package dto;

/**
 *
 * @author
 */
public class KozmetickiAsortiman {
    
    private int Barkod;
    private String NazivArtikla;    
    private double NabavnaCijena;
    private double ProdajnaCijena;
    private String Proizvodjac;
    private String JedinicaMjere;
    private String NazivTipa;
    
    public KozmetickiAsortiman(){
        
    }
    
    public KozmetickiAsortiman(int Barkod, String NazivArtikla, double NabavnaCijena, double ProdajnaCijena, String Proizvodjac, String JedinicaMjere, String NazivTipa){
        this.Barkod=Barkod;
        this.NazivArtikla=NazivArtikla;
        this.NabavnaCijena=NabavnaCijena;
        this.ProdajnaCijena=ProdajnaCijena;
        this.Proizvodjac=Proizvodjac;
        this.JedinicaMjere=JedinicaMjere;
        this.NazivTipa=NazivTipa;
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
   
    public String getNazivTipa(){
        return NazivTipa;
    }
    
    public void setNazivTipa(String NazivTipa){
        this.NazivTipa=NazivTipa;
    } 
    
    public String getJedinicaMjere(){
        return JedinicaMjere;
    }
    
    public void setJedinicaMjere(String JedinicaMjere){
        this.JedinicaMjere=JedinicaMjere;
    }
    
    public String getProizvodjac(){
        return Proizvodjac;
    }
    
    public void setProizvodjac(String Proizvodjac){
        this.Proizvodjac=Proizvodjac;
    }
    
    public double getNabavnaCijena(){
        return NabavnaCijena;
    }
    
    public void setNabavnaCijena(double NabavnaCijena){
        this.NabavnaCijena=NabavnaCijena;
    } 
    
    public double getProdajnaCijena(){
        return ProdajnaCijena;
    }
    
    public void setProdajnaCijena(double ProdajnaCijena){
        this.ProdajnaCijena=ProdajnaCijena;
    } 
}