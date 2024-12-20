package apoteka.gui;

import dao.ArtikalDAO;
import dao.ZaposlenikDAO;
import dto.Artikal;
import dto.TipArtikla;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DodajArtikalController {

    @FXML
    private TextField barkodTextField;
    @FXML
    private TextField nazivTextField;
    @FXML
    private TextField zalihaTextField;
    @FXML
    private TextField cijenaTextField;
    @FXML
    private TextField proizvodjacTextField;
    @FXML
    private ComboBox<String> tipArtiklaComboBox;
    @FXML
    private ComboBox<String> zaposlenikComboBox;

    @FXML
    private void initialize() {
        zaposlenikComboBox.setItems(FXCollections.observableArrayList(ZaposlenikDAO.getZaposlenik().toString()));
    }

    @FXML
    private void dodajArtikal() {
        try {
            // Preuzmi unete podatke
            int barkod = Integer.parseInt(barkodTextField.getText());
            String naziv = nazivTextField.getText();
            int zaliha = Integer.parseInt(zalihaTextField.getText());
            double cijena = Double.parseDouble(cijenaTextField.getText());
            int proizvodjacId = Integer.parseInt(proizvodjacTextField.getText());
            String tipArtikla = tipArtiklaComboBox.getValue();

            // Kreiraj novi artikal
            Artikal artikal = new Artikal(barkod, naziv, zaliha, proizvodjacId);
            ArtikalDAO.dodajArtikal(barkod, naziv, zaliha, proizvodjacId, tipArtikla);

        } catch (Exception e) {
            e.printStackTrace();
            // Ovde možeš dodati validaciju podataka ili obavještenje o grešci
        }
    }

    public void zatvori(ActionEvent actionEvent) {
        Stage stage = (Stage) barkodTextField.getScene().getWindow();
        stage.close();
    }
}
