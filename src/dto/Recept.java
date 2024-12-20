package dto;

/**
 *
 * @author 
 */
public class Recept {
    
    public java.sql.Date DatumIzdavanja;
    private String BrojKnjizice;
    private String JMBG;
    private int SifraDoktora;
    private int Barkod;
    private String NazivArtikla;
    private int Kolicina;
    private double ProdajnaCijena;
      
    public Recept(){        
    }
    
    public Recept(java.sql.Date DatumIzdavanja, String BrojKnjizice, String JMBG, int SifraDoktora, int Barkod, String NazivArtikla, int Kolicina, double ProdajnaCijena) {
        this.DatumIzdavanja = DatumIzdavanja;
        this.BrojKnjizice = BrojKnjizice;
        this.JMBG = JMBG;
        this.SifraDoktora = SifraDoktora;
        this.Barkod = Barkod;
        this.NazivArtikla = NazivArtikla;
        this.Kolicina = Kolicina;
        this.ProdajnaCijena=ProdajnaCijena;
    }

    public java.sql.Date getDatumIzdavanja() {
        return DatumIzdavanja;
    }

    public void setDatumIzdavanja(java.sql.Date datum) {
        this.DatumIzdavanja = DatumIzdavanja;
    }
    
    public String getBrojKnjizice(){
        return BrojKnjizice;
    }
    
    public void setBrojKnjizice(String BrojKnjizice){
        this.BrojKnjizice=BrojKnjizice;
    }
    
    public String getJMBG(){
        return JMBG;
    }
    
    public void setJMBG(String JMBG){
        this.JMBG=JMBG;
    }
        
    public int getSifraDoktora(){
        return SifraDoktora;
    }
    
    public void setSifraDoktora(int SifraDoktora){
        this.SifraDoktora=SifraDoktora;
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
    
    public int getKolicina() {
        return Kolicina;
    }
    
    public void setKolicina(int Kolicina){
        this.Kolicina=Kolicina;
    }
    
    public double getProdajnaCijena() {
        return ProdajnaCijena;
    }

    public void setProdajnaCijena(double ProdajnaCijena) {
        this.ProdajnaCijena = ProdajnaCijena;
    }
}