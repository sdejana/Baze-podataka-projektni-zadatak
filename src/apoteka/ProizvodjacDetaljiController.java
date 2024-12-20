package apoteka;

import dto.Proizvodjac;
import dao.ProizvodjacDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ProizvodjacDetaljiController
{
    @FXML
    private Label nazivLabel;
    @FXML
    private Label adresaLabel;
    @FXML
    private Label brojPosteLabel;
    @FXML
    private Label sjedisteLabel;
    @FXML
    private Label telefonLabel;

    @FXML
    private TextField nazivTextField;
    @FXML
    private TextField adresaTextField;
    @FXML
    private TextField brojPosteTextField;
    @FXML
    private TextField sjedisteTextField;
    @FXML
    private TextField telefonTextField;

    @FXML
    private Label statusLabel;

    @FXML
    private void closeDetails() {
        Stage stage = (Stage) nazivLabel.getScene().getWindow();
        stage.close();
    }
    public void setProizvodjac(Proizvodjac proizvodjac) {
        nazivTextField.setText(proizvodjac.getNaziv());
        adresaTextField.setText(proizvodjac.getAdresa());
        brojPosteTextField.setText(String.valueOf(proizvodjac.getBrojPoste()));
        sjedisteTextField.setText(proizvodjac.getSjediste());
        telefonTextField.setText(proizvodjac.getTelefon());
    }

    // Metoda za dodavanje novog proizvođača
    @FXML
    private void dodajProizvodjaca() {
        String naziv = nazivTextField.getText();
        String adresa = adresaTextField.getText();
        int brojPoste;
        try {
            brojPoste = Integer.parseInt(brojPosteTextField.getText());
        } catch (NumberFormatException e) {
            showAlert("Neispravan unos", "Broj pošte mora biti validan broj.");
            return;
        }
        String sjediste = sjedisteTextField.getText();
        String telefon = telefonTextField.getText();

        if (naziv.isEmpty() || adresa.isEmpty() || sjediste.isEmpty() || telefon.isEmpty()) {
            showAlert("Neispravan unos", "Sva polja moraju biti popunjena.");
            return;
        }

        boolean uspjeh = ProizvodjacDAO.upisiProizvodjaca(naziv, adresa, brojPoste, telefon);
        if (uspjeh) {
            statusLabel.setText("Proizvođač uspješno dodat.");
        } else {
            statusLabel.setText("Greška prilikom dodavanja proizvođača.");
        }
    }

    // Metoda za brisanje proizvođača
    @FXML
    private void obrisiProizvodjaca() {
        String naziv = nazivTextField.getText();

        if (naziv.isEmpty()) {
            showAlert("Neispravan unos", "Naziv proizvođača mora biti unesen.");
            return;
        }

        Proizvodjac proizvodjac = new Proizvodjac(naziv, adresaTextField.getText(),
                Integer.parseInt(brojPosteTextField.getText()),
                sjedisteTextField.getText(), telefonTextField.getText());
        System.out.println(proizvodjac);
        boolean uspjeh = ProizvodjacDAO.obrisiProizvodjaca(proizvodjac);
        if (uspjeh) {
            statusLabel.setText("Proizvođač uspješno obrisan.");
        } else {
            statusLabel.setText("Greška prilikom brisanja proizvođača.");
        }
    }

    // Pomoćna metoda za prikaz poruka o grešci
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Metoda za zatvaranje detalja

}
