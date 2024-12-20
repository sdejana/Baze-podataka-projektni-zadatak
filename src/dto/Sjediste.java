package dto;

/**
 *
 * @author 
 */
public class Sjediste {
    public int BrojPoste;
    public String Naziv;
    
    public Sjediste(int BrojPoste, String Naziv){
        this.BrojPoste=BrojPoste;
        this.Naziv=Naziv;
    } 

    public int getIdBrojPoste(){
        return BrojPoste;
    }
    
    public void setIdArtikla(int BrojPoste){
        this.BrojPoste=BrojPoste;
    }
    
    public String getNaziv(){
        return Naziv;
    }
    
    public void setNaziv(String Naziv){
        this.Naziv=Naziv;
    }

     @Override
    public String toString(){
        return Naziv;
    }   
}