package dto;


/**
 *
 * @author 
 */
public class Lijek {
    private int Barkod;
    private String NazivArtikla;
    private String NazivTipa;
    private double NabavnaCijena;
    private double ProdajnaCijena;
    private String Proizvodjac;
    private String GenerickiNaziv;
    private String Doza;
    private String Oblik;
    private String NazivListe;
    
    public Lijek(){      
    }
 
    public Lijek(int Barkod, String NazivArtikla, String NazivTipa, double NabavnaCijena, double ProdajnaCijena, 
                  String Proizvodjac, String GenerickiNaziv, String Doza, String Oblik, String NazivListe){
        this.Barkod=Barkod;
        this.NazivArtikla=NazivArtikla;
        this.NazivTipa=NazivTipa;
        this.NabavnaCijena=NabavnaCijena;
        this.ProdajnaCijena=ProdajnaCijena;
        this.Proizvodjac=Proizvodjac;
        this.NazivListe=NazivListe;
        this.GenerickiNaziv=GenerickiNaziv;
        this.Doza=Doza;
        this.Oblik=Oblik;
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
    
    public String getGenerickiNaziv(){
        return GenerickiNaziv;
    }
    
    public void setGenerickiNaziv(String GenerickiNaziv){
        this.GenerickiNaziv=GenerickiNaziv;
    }
    
    public String getDoza(){
        return Doza;
    }
    
    public void setDoza(String Doza){
        this.Doza=Doza;
    }
    
    public String getOblik(){
        return Oblik;
    }
    
    public void setOblik(String Oblik){
        this.Oblik=Oblik;
    }
      
    public String getNazivListe(){
        return NazivListe;
    }
    
    public void setNazivListe(String NazivListe){
        this.NazivListe=NazivListe;
    }
    
    public String getNazivTipa(){
        return NazivTipa;
    }
    
    public void setNazivTipa(String NazivTipa){
        this.NazivTipa=NazivTipa;
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