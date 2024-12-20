package dto;

/**
 *
 * @author 
 */
public class Racun {
    public int IdRacuna;
    public double UkupanIznos;
    public int IdZaposlenika;
    
    public Racun(int IdRacuna, double UkupanIznos, int IdZaposlenika){
        this.IdRacuna=IdRacuna;
        this.UkupanIznos=UkupanIznos;
        this.IdZaposlenika=IdZaposlenika;
    }
   
    public int getIdRacuna(){
        return IdRacuna;
    }
    
    public void setIdRacuna(int IdRacuna){
        this.IdRacuna=IdRacuna;
    }
   
    public double getUkupanIznos(){
        return UkupanIznos;
    }
    
    public void setUkupanIznos(double UkupanIznos){
        this.UkupanIznos=UkupanIznos;
    }
    
    public int getIdZaposlenika(){
        return IdZaposlenika;
    }
    
    public void setIdZaposlenika(int IdZaposlenika){
        this.IdZaposlenika=IdZaposlenika;
    }    
}