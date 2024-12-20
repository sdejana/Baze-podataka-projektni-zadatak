package apoteka.gui;

import apoteka.ProizvodjacDetaljiController;
import dao.ArtikalDAO;
import dao.ProizvodjacDAO;
import dto.Artikal;
import dto.Proizvodjac;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ZaposlenikController {
    @FXML
    private ImageView userImageView;
    @FXML
    private Button pregledajProizvodjaceButton;
    @FXML
    private Button pregledajZalihuButton;
    @FXML
    private Label welcomeLabel;


    public void initialize() {
        welcomeLabel.setText("Dobrodošli, farmaceutski tehničar!");
        Image logo = new Image(getClass().getResourceAsStream("/slike/user.png"));
        userImageView.setImage(logo);
    }

    private void openDetailWindow(Artikal artikal) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/apoteka/gui/ArtikalDetaljiController.fxml"));
            Parent root = loader.load();

            ArtikalDetaljiController controller = loader.getController();
            controller.setArtikal(artikal);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Detalji Artikla");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void pregledajZalihu() {
        // Kreiraj novu TableView
        TableView<Artikal> artikalTableView = new TableView<>();

        // Kreiraj kolone
        TableColumn<Artikal, Integer> barkodColumn = new TableColumn<>("Barkod");
        barkodColumn.setCellValueFactory(new PropertyValueFactory<>("barkod"));

        TableColumn<Artikal, String> nazivColumn = new TableColumn<>("Naziv");
        nazivColumn.setCellValueFactory(new PropertyValueFactory<>("nazivArtikla"));

        TableColumn<Artikal, Integer> zalihaColumn = new TableColumn<>("Zaliha");
        zalihaColumn.setCellValueFactory(new PropertyValueFactory<>("zaliha"));

        TableColumn<Artikal, Integer> proizvodjacColumn = new TableColumn<>("Proizvođač ID");
        proizvodjacColumn.setCellValueFactory(new PropertyValueFactory<>("idProizvodjac"));

        // Dodaj kolone u TableView
        artikalTableView.getColumns().addAll(barkodColumn, nazivColumn, zalihaColumn, proizvodjacColumn);

        // Popuni tabelu sa podacima
        ObservableList<Artikal> artikli = ArtikalDAO.getArtikal();
        artikalTableView.setItems(artikli);

        artikalTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) { // Dvostruki klik
                Artikal selectedArtikal = artikalTableView.getSelectionModel().getSelectedItem();
                if (selectedArtikal != null) {
                    openDetailWindow(selectedArtikal);
                }
            }
        });

        // Prikaži tabelu u novom prozoru
        VBox vbox = new VBox(artikalTableView);
        Scene scene = new Scene(vbox);

        Stage stage = new Stage();
        stage.setTitle("Zaliha artikala");
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private Button dodajArtikalButton;
    @FXML
    private Button generisiRacunButton;


    @FXML
    private void otvoriDodajArtikalProzor() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/apoteka/gui/DodajArtikalController.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Dodaj novi artikal");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void generisiRacun() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/apoteka/gui/GenerisiRacunController.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Generisanje računa: ");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void pregledajProizvodjace() {

        // Kreiraj novu TableView
        TableView<Proizvodjac> artikalTableView = new TableView<>();

        // Kreiraj kolone
        TableColumn<Proizvodjac, Integer> NazivColumn = new TableColumn<>("Naziv");
        NazivColumn.setCellValueFactory(new PropertyValueFactory<>("Naziv"));

        TableColumn<Proizvodjac, String> AdresaColumn = new TableColumn<>("Adresa");
        AdresaColumn.setCellValueFactory(new PropertyValueFactory<>("Adresa"));

        TableColumn<Proizvodjac, Integer> BrojPosteColumn = new TableColumn<>("Broj pošte");
        BrojPosteColumn.setCellValueFactory(new PropertyValueFactory<>("BrojPoste"));

        TableColumn<Proizvodjac, Integer> SjedisteColumn = new TableColumn<>("Sjedište");
        SjedisteColumn.setCellValueFactory(new PropertyValueFactory<>("Sjediste"));

        TableColumn<Proizvodjac, Integer> TelefonColumn = new TableColumn<>("Telefon");
        TelefonColumn.setCellValueFactory(new PropertyValueFactory<>("Telefon"));

        // Dodaj kolone u TableView
        artikalTableView.getColumns().addAll(NazivColumn, AdresaColumn, BrojPosteColumn, SjedisteColumn, TelefonColumn);

        // Popuni tabelu sa podacima
        ObservableList<Proizvodjac> artikli = ProizvodjacDAO.getProizvodjac();
        artikalTableView.setItems(artikli);

        artikalTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) { // Dvostruki klik
                Proizvodjac selectedArtikal = artikalTableView.getSelectionModel().getSelectedItem();
                if (selectedArtikal != null) {
                    openDetailWindow2(selectedArtikal);
                }
            }
        });

        // Prikaži tabelu u novom prozoru
        VBox vbox = new VBox(artikalTableView);
        Scene scene = new Scene(vbox);

        Stage stage = new Stage();
        stage.setTitle("Proizvodjaci: ");
        stage.setScene(scene);
        stage.show();

    }
    private void openDetailWindow2(Proizvodjac proizvodjac) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/apoteka/gui/ProizvodjacDetaljiController.fxml"));
            Parent root = loader.load();

            ProizvodjacDetaljiController controller = loader.getController();
            controller.setProizvodjac(proizvodjac);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Detalji Proizvođača: ");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
