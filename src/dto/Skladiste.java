package dto;

/**
 *
 * @author 
 */
public class Skladiste {
    private int Barkod;
    private String NazivArtikla;
    private int Zaliha;
    private String Proizvodjac;
    
    public Skladiste(){
        
    }
 
    public Skladiste(int Barkod, String NazivArtikla, int Zaliha, String Proizvodjac){
        this.Barkod=Barkod;
        this.NazivArtikla=NazivArtikla;
        this.Zaliha=Zaliha;
        this.Proizvodjac=Proizvodjac;
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
  
    public int getZaliha(){
        return Zaliha;
    }
    
    public void setZaliha(int Zaliha){
        this.Zaliha=Zaliha;
    }
    
    public String getProizvodjac(){
        return Proizvodjac;
    }
    
    public void setProizvodjac(String Proizvodjac){
        this.Proizvodjac=Proizvodjac;
    }
}
