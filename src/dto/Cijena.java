package dto;


/**
 *
 * @author
 */
public class Cijena {
    public int IdCijena;    
    public double NabavnaCijena;
    public double ProdajnaCijena;
    public int Barkod;
    public int IdTipArtikla;
    
    public Cijena(int IdCijena, double NabavnaCijena, double ProdajnaCijena, int Barkod, int IdTipArtikla) {
        this.IdCijena = IdCijena;
        this.NabavnaCijena=NabavnaCijena;
        this.ProdajnaCijena = ProdajnaCijena;  
        this.Barkod = Barkod;
        this.IdTipArtikla = IdTipArtikla;
    }
    
    public int getIdCijena() {
        return IdCijena;
    }

    public void setIdCijena(int IdCijena) {
        this.IdCijena = IdCijena;
    }
    
    public double getProdajnaCijena() {
        return ProdajnaCijena;
    }

    public void setProdajnaCijena(double ProdajnaCijena) {
        this.ProdajnaCijena = ProdajnaCijena;
    }
    
    public double getNabavnaCijena() {
        return NabavnaCijena;
    }

    public void setNabavnaCijena(double NabavnaCijena) {
        this.NabavnaCijena = NabavnaCijena;
    }
    
    public int getBarkod() {
        return Barkod;
    }

    public void setBarkod(int Barkod) {
        this.Barkod = Barkod;
    }
    
    public int getIdTipArtikla() {
        return IdTipArtikla;
    }

    public void setIdTipArtikla(int IdTipArtikla) {
        this.IdTipArtikla = IdTipArtikla;
    }   
}