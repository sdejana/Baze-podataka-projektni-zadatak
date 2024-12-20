package apoteka.gui;

import dao.ArtikalDAO;
import dao.RacunDAO;
import dao.TipArtiklaDAO;
import dao.ZaposlenikDAO;
import dto.Artikal;
import dto.RacunStavka;
import dto.TipArtikla;
import dto.Zaposlenik;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.List;

public class GenerisiRacunController {
    @FXML
    private ListView stavkeListView;
    @FXML
    private Button zavrsiButton;
    @FXML
    private TextField barkodTextField;
    @FXML
    private TextField nazivArtiklaTextField;
    @FXML
    private TextField kolicinaTextField;
    @FXML
    private TextField cijenaTextField;
    @FXML
    private ComboBox<Zaposlenik> zaposlenikComboBox;
    @FXML
    private ComboBox<String> tipArtiklaComboBox;
    @FXML
    private Button dodajStavkuButton;

    private ObservableList<RacunStavka> listaStavki = FXCollections.observableArrayList();
    private Artikal artikal;

    @FXML
    private void initialize() {
        // Popuni combo box sa zaposlenicima

        List<Zaposlenik> zaposlenici = ZaposlenikDAO.getZaposlenik();
        ObservableList<Zaposlenik> observableZaposlenici = FXCollections.observableArrayList(zaposlenici);
        zaposlenikComboBox.setItems(observableZaposlenici);
        stavkeListView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                RacunStavka selectedStavka = (RacunStavka) stavkeListView.getSelectionModel().getSelectedItem();
                if (selectedStavka != null) {
                    prikaziInformacijeOArtiklu(selectedStavka);
                }
            }
        });
        // Ako želiš da prikažeš samo imena
        zaposlenikComboBox.setCellFactory(param -> new ListCell<Zaposlenik>() {
            @Override
            protected void updateItem(Zaposlenik item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getKorisnickoIme()); // Prilagodi naziv metode za ime
                }
            }
        });
        // Popuni combo box sa tipovima artikala
        tipArtiklaComboBox.setItems(FXCollections.observableArrayList("1", "2", "3"));
        stavkeListView.setCellFactory(param -> new ListCell<RacunStavka>() {
            @Override
            protected void updateItem(RacunStavka item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getNazivArtikla() + " - " + item.getKolicina() + " x " + item.getCijena());
                }
            }
        });

    }
    private void prikaziInformacijeOArtiklu(RacunStavka stavka) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
       TipArtikla tipArtikla =  TipArtiklaDAO.getTipArtiklaByBarcode(artikal.getBarkod());

                alert.setTitle("Informacije o artiklu");
        alert.setHeaderText(null);
        alert.setContentText("Barkod: " + stavka.getBarkod() +
                "\nNaziv: " + stavka.getNazivArtikla() +
                "\nKoličina: " + stavka.getKolicina() +
                "\nCijena: " + stavka.getCijena() +
                "\n--------------------" +
                "\nDetalji artikla: "+
                 "\nZaliha: " + artikal.getZaliha() +
                "\nProizvođač ID: " + artikal.getIdProizvodjac() +
                "\nTip: " + tipArtikla.getNazivTipa() +
                "\nKategorija: " + ArtikalDAO.getArtikalKategorijaByBarkod(artikal.getBarkod())
                + "\n--------------------"
        );

        alert.showAndWait();
    }
    @FXML
    private void onDodajStavkuButtonClicked() {
        try {
            // Uzimanje podataka iz formi
            int barkod = Integer.parseInt(barkodTextField.getText());
            String nazivArtikla = nazivArtiklaTextField.getText();
            int kolicina = Integer.parseInt(kolicinaTextField.getText());
            double cijena = Double.parseDouble(cijenaTextField.getText());
            int idZaposlenika = zaposlenikComboBox.getSelectionModel().getSelectedIndex(); // prilagodi
            int idTipArtikla = tipArtiklaComboBox.getSelectionModel().getSelectedIndex(); // prilagodi

            // Kreiranje nove stavke
            RacunStavka novaStavka = new RacunStavka(barkod, nazivArtikla, kolicina, cijena, idZaposlenika, idTipArtikla);

            // Dodavanje stavke u listu
            listaStavki.add(novaStavka);

            // Očisti polja za unos
            barkodTextField.clear();
            nazivArtiklaTextField.clear();
            kolicinaTextField.clear();
            cijenaTextField.clear();

            stavkeListView.setItems(listaStavki);
            artikal = ArtikalDAO.getArtikalByBarkod(barkod);
        } catch (NumberFormatException e) {
            // Obrada greške u unosu
            System.out.println("Greška u unosu podataka. Molimo proverite.");
        }
    }


    public void zavrsi(ActionEvent actionEvent) {
        RacunDAO.prodaj(listaStavki);

        Stage stage = (Stage) barkodTextField.getScene().getWindow();
        stage.close();
    }
}
