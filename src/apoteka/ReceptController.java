package apoteka;

import dao.ReceptDAO;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author 
 */
public class ReceptController implements Initializable {


    @FXML
    private DatePicker datumIzdavanjaField;
    @FXML
    private TextField brojKnjiziceField;
    @FXML
    private TextField jmbgField;
    @FXML
    private TextField sifraDoktoraField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        datumIzdavanjaField.setValue(LocalDate.now());
    }    

    @FXML
    private void dodajReceptAction(ActionEvent event) {
        
        if(!brojKnjiziceField.getText().equals("") && !jmbgField.getText().equals("") && !sifraDoktoraField.getText().equals("")){
            String brojKnjizice = brojKnjiziceField.getText();
            if(brojKnjiziceField.getText().length() != 11){
                brojKnjiziceLengthAlert();
                return;
            }
            String jmbg = jmbgField.getText();
            if(jmbgField.getText().length() != 13){
                jmbgLengthAlert();
                return;
            }
            int sifraDoktora = Integer.parseInt(sifraDoktoraField.getText()); 
            Date datum = java.sql.Date.valueOf(datumIzdavanjaField.getValue());
                try {
                    ReceptDAO.dodajRecept(datum, brojKnjizice, jmbg, sifraDoktora);
                    
                    Image image=new Image("img/mooo.png");
                        Notifications notifications=Notifications.create()
                            .text("Uspješan upis novog recepta.")
                            .hideAfter(Duration.seconds(5))
                            .graphic(new ImageView(image))
                            .position(Pos.BOTTOM_RIGHT);
                    notifications.darkStyle();
                    notifications.show();
                    
                    datumIzdavanjaField.setValue(LocalDate.now());
                    brojKnjiziceField.setText("");
                    jmbgField.setText("");
                    sifraDoktoraField.setText("");
                    
                } catch (Exception ex){
                    Image image=new Image("img/delete.png");
			Notifications notifications=Notifications.create()                    
                                .text("Pogresan unos podataka!")
                                .hideAfter(Duration.seconds(5))
                                .graphic(new ImageView(image))
                                .position(Pos.BOTTOM_RIGHT);
                        notifications.darkStyle();
                        notifications.show();
                }
                
        }else{
                    Image image=new Image("img/delete.png");
			Notifications notifications=Notifications.create()                    
                                .text("Popunite sva polja!")
                                .hideAfter(Duration.seconds(5))
                                .graphic(new ImageView(image))
                                .position(Pos.BOTTOM_RIGHT);
                        notifications.darkStyle();
                        notifications.show();                        
                        }
    }
    
    private void jmbgLengthAlert() {
        Image image=new Image("img/delete.png");
			Notifications notifications=Notifications.create()                    
                                .text("Unesite 13 cifara za jmbg!")
                                .hideAfter(Duration.seconds(5))
                                .graphic(new ImageView(image))
                                .position(Pos.CENTER_RIGHT);
                        notifications.darkStyle();
                        notifications.show();
    }
    
    private void brojKnjiziceLengthAlert(){
        Image image=new Image("img/delete.png");
			Notifications notifications=Notifications.create()                    
                                .text("Unesite 11 cifara za zdravstvenu knjižicu!")
                                .hideAfter(Duration.seconds(5))
                                .graphic(new ImageView(image))
                                .position(Pos.CENTER_RIGHT);
                        notifications.darkStyle();
                        notifications.show();
    }
}