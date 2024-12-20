package dto;

import com.sun.javafx.binding.StringFormatter;

import java.util.concurrent.RecursiveTask;

/**
 *
 * @author 
 */
public class Artikal {
    public int Barkod;
    public String NazivArtikla;
    public int Zaliha;
    public int IdProizvodjac;

    public Artikal(int barkod, String nazivArtikla, int zaliha, int idProizvodjac) {
        this.Barkod = barkod;
        this.NazivArtikla = nazivArtikla;
        this.Zaliha = zaliha;
        this.IdProizvodjac = idProizvodjac;
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
    
    public int getIdProizvodjac(){
        return IdProizvodjac;
    }
    
    public void setIdProizvodjac(int IdProizvodjac){
        this.IdProizvodjac=IdProizvodjac;
    }
  
    @Override
    public String toString(){
        return Barkod + " - "+ NazivArtikla;
    }    
} 