package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utils.ConnectionUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Delete_Controller implements Initializable {

    // Declararea variabilelor:

    @FXML
    private Button btnIesire;

    @FXML
    private TextField txtUsernameD;

    @FXML
    private PasswordField txtPasswordD;

    @FXML
    private Button btnBackLoginD;

    @FXML
    private Button btnCancelD;

    @FXML
    private Button btnDeleteA;

    @FXML
    private AnchorPane rootPane;

    // Functionalitatea aplicatiei:

    // 1. Butonul de EXIT din program:
    @FXML
    void btnIesire (ActionEvent e) {

        btnIesire.setOnAction(event -> {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Esti sigur?");
            ButtonType userOption = alert.showAndWait().get();
            if (userOption == ButtonType.CANCEL) {
                System.out.println("Utilizatorul a anulat sesiunea inchiderii aplicatiei!");

            }
            if (userOption == ButtonType.OK) {
                System.out.println("Utilizatorul a decis sa inchida aplicatia de Delete a contului!");

            }
            System.exit(0);


        });
    }

    // 2. Butonul de CANCEL din program:
    @FXML
    void btnCancelD (ActionEvent e ){
        btnCancelD.setOnAction(ev ->{
            txtUsernameD.setText(null);
            txtPasswordD.setText(null);
        });

    }

    // 3. Butonul de BACK - cel de revenire la formularul de LOGIN:
    @FXML
    void btnBackLoginD (ActionEvent e){
        btnBackLoginD.setOnAction(event ->{
            try {
                Parent backParent;
                backParent = (AnchorPane) FXMLLoader.load(getClass().getResource("/sample/sample.fxml"));

                Scene nscene = new Scene(backParent);
                Stage mainWindow;
                mainWindow = (Stage) rootPane.getScene().getWindow();
                mainWindow.setTitle("Login Form");
                mainWindow.setScene(nscene);
            } catch (IOException ex) {
                Logger.getLogger(Main_Controller.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
    }

    // 4. Butonul de DELETE din program:

    // - Conectarea la baza de DATE:
    Connection con = null;

    public Delete_Controller() {
        con = ConnectionUtil.conDB();
    }
    @FXML
    void btnDeleteA (ActionEvent e ) {
        btnDeleteA.setOnAction(event ->{
            // -> Declararea variabilei unde ii spunem sa caute pentru a sterge contul:

            String uname = txtUsernameD.getText();

            // -> Inserarea datelor in baza de date

                try {
                    //query
                    String sql = "DELETE FROM register WHERE uname =?";

                    PreparedStatement preparedStatement = null;
                    preparedStatement = con.prepareStatement(sql);
                    preparedStatement.setString(1, uname);


                    int resultSet = preparedStatement.executeUpdate();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.initStyle(StageStyle.UTILITY);
                    alert.setTitle("Delete Information");
                    alert.setHeaderText(null);
                    alert.setContentText("Contul a fost sters cu success! Va puteti intoarce la formularul de Login!");
                    alert.showAndWait();
                } catch (Exception ex) {
                    System.err.println("Eroare!");

                }
        });
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) { }
}
