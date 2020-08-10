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

public class SignUP_Controller implements Initializable {

    // Declararea variabilelor aplicatiei:

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnIesire;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtUsernameRegister;

    @FXML
    private TextField txtPasswordRegister;

    @FXML
    private TextField txtRetypePass;

    @FXML
    private TextArea taAddress;

    @FXML
    private Button btnBack;

    @FXML
    private TextField txtID;


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
                System.out.println("Utilizatorul a decis sa inchida aplicatia de Register!");

            }
            System.exit(0);


        });
    }

    // 2. Butonul de REGISTER din program:

    // - Conectarea la baza de DATE:
    Connection con = null;
    public SignUP_Controller()
    { con = ConnectionUtil.conDB(); }

    // - Butonul propriu-zis:
    @FXML
    private void btnRegister(ActionEvent e ) {

        // -> Declararea variabilelor:

        int id = Integer.valueOf(txtID.getText());
        String fname = txtFirstName.getText();
        String lname = txtLastName.getText();
        String uname = txtUsernameRegister.getText();
        String pass = String.valueOf(txtPasswordRegister.getText());
        String re_pass = String.valueOf(txtRetypePass.getText());
        String addr = taAddress.getText();
        String status ;

        // -> Inserarea datelor in baza de date + AlertType:
        
        if (fname.isEmpty() || lname.isEmpty() || uname.isEmpty() || pass.isEmpty() ||
                re_pass.isEmpty() || addr.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Register Information");
            alert.setHeaderText(null);
            alert.setContentText("Va rugam completati toate datele!");
            alert.showAndWait();
        } else {
            //query
            String sql = "INSERT INTO register (id,fname,lname,uname,pass,re_pass" +
                    ", addr) VALUES (?,?, ?, ?, ?, ?, ?)";
            try {
                PreparedStatement preparedStatement = null;
                preparedStatement = con.prepareStatement(sql);
                preparedStatement.setInt(1, id);
                preparedStatement.setString(2, fname);
                preparedStatement.setString(3, lname);
                preparedStatement.setString(4, uname);
                preparedStatement.setString(5, pass);
                preparedStatement.setString(6, re_pass);
                preparedStatement.setString(7, addr);


                int resultSet = preparedStatement.executeUpdate();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("Register Information");
                alert.setHeaderText(null);
                alert.setContentText("Cont creat cu success! Va rugam reveniti in formularul de Login!");
                alert.showAndWait();
            } catch (Exception ex) {
                System.err.println("Eroare! Contul a fost creat deja! Va rugam utilizati un alt ID si un alt username!");

            }
        }
    }


    // 3. Butonul de CANCEL din program:
    @FXML
    void btnCancel (ActionEvent e ){
        btnCancel.setOnAction(ev ->{
            txtFirstName.setText(null);
            txtLastName.setText(null);
            txtUsernameRegister.setText(null);
            txtPasswordRegister.setText(null);
            txtRetypePass.setText(null);
            taAddress.setText(null);
        });

    }

    // 4. Butonul de BACK - cel de revenire la formularul de LOGIN:
    @FXML
    void btnBack (ActionEvent e){
        btnBack.setOnAction(event ->{
            try {
                Parent backParent;
                backParent = (AnchorPane) FXMLLoader.load(getClass().getResource("/sample/sample.fxml"));

                Scene nscene = new Scene(backParent);
                Stage mainWindow;
                mainWindow = (Stage) rootPane.getScene().getWindow();
                mainWindow.setTitle("Login");
                mainWindow.setScene(nscene);
            } catch (IOException ex) {
                Logger.getLogger(Main_Controller.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) { }
}
