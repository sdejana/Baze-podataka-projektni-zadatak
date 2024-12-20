package apoteka.gui;

import dao.ArtikalDAO;
import dao.TipArtiklaDAO;
import dto.Artikal;
import dto.TipArtikla;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ArtikalDetaljiController {
    @FXML
    private Label nazivTipaArtikla;
    @FXML
    private ImageView drugImageView;
    @FXML
    private Label barkodLabel;
    @FXML
    private Label nazivLabel;
    @FXML
    private Label zalihaLabel;
    @FXML
    private Label proizvodjacLabel;
    @FXML
    private Label tipArtiklaLabel;

    private TipArtikla tipArtikla;

    @FXML
    public void initialize()
    {
        Image drug = new Image(getClass().getResourceAsStream("/slike/drug.png")); // Postavi pravi naziv i put do loga
        drugImageView.setImage(drug);
    }
    public void setArtikal(Artikal artikal) {
        //this.artikal = artikal;
        barkodLabel.setText("Barkod: " + artikal.getBarkod());
        nazivLabel.setText("Naziv: " + artikal.getNazivArtikla());
        zalihaLabel.setText("Zaliha: " + artikal.getZaliha());
        proizvodjacLabel.setText("Proizvođač ID: " + artikal.getIdProizvodjac());
        tipArtikla = TipArtiklaDAO.getTipArtiklaByBarcode(artikal.getBarkod());
        tipArtiklaLabel.setText("Tip: " + tipArtikla.getNazivTipa());
        System.out.println(ArtikalDAO.getArtikalKategorijaByBarkod(artikal.getBarkod()));
        nazivTipaArtikla.setText("Kategorija: " + ArtikalDAO.getArtikalKategorijaByBarkod(artikal.getBarkod()));
    }

    @FXML
    private void closeDetails() {
        Stage stage = (Stage) barkodLabel.getScene().getWindow();
        stage.close();
    }
}
