package apoteka.gui;

import dao.ZaposlenikDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    private ImageView logoImageView;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private PasswordField txtLozinka; // Polje za unos lozinke

    @FXML
    private Button btnLogin;

    private static final String PODRAZUMIJEVANA_LOZINKA = "apoteka123"; // Podrazumijevana lozinka

    // Inicijalizacija
    public void initialize() {

        // Možeš inicijalizovati sve potrebne objekte ovdje
        Image logo = new Image(getClass().getResourceAsStream("/slike/logo.png")); // Postavi pravi naziv i put do loga
        logoImageView.setImage(logo);
    }

    // Metoda za login
    @FXML
    public void login(ActionEvent event) {
        String unijetaLozinka = txtLozinka.getText();

        if (unijetaLozinka.equals(PODRAZUMIJEVANA_LOZINKA)) {
            // Ako je lozinka tačna, prijavi se kao farmaceutski tehničar
            pokreniRadTehnicara();
        } else {
            // Ako lozinka nije tačna, prikaži grešku
            prikaziGresku("Pogrešna lozinka! Molimo pokušajte ponovo.");
        }
    }

    // Prikaz greške
    private void prikaziGresku(String poruka) {
        // Ovdje možeš dodati logiku za prikazivanje greške (alert dialog)
        System.out.println(poruka);
    }

    // Pokretanje aplikacije kao farmaceutski tehničar
    @FXML
    private void pokreniRadTehnicara() {
        System.out.println("Ulogovan kao farmaceutski tehničar.");
        try {
            // Učitaj novi FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/apoteka/gui/ZaposlenikController.fxml")); // Postavi pravi put
            Parent root = loader.load();

            // Kreiraj novi Stage
            Stage stage = new Stage();
            stage.setTitle("Glavni prozor farmaceutskog tehničara");
            stage.setScene(new Scene(root, 800, 600)); // Postavi dimenzije prema potrebi
            stage.show();

            // Zatvori trenutni prozor (prijava)
            Stage currentStage = (Stage) btnLogin.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
            prikaziGresku("Greška pri otvaranju glavnog prozora.");
        }
    }
}
