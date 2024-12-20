package apoteka;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Apoteka extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/apoteka/gui/LoginController.fxml"))); // Postavi put do FXML datoteke
        primaryStage.setTitle("Apoteka - Prijava");
        primaryStage.setScene(new Scene(root, 600, 300));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    // ove greske nmp sta je to, ko ce znati, bilo ih nije xd
}
