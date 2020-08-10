package sample;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import utils.ConnectionUtil;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Main_Controller implements Initializable {

    // Declararea variabilelor aplicatiei:

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Label lblErrors;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Button btnERP;

    @FXML
    private Button btnDelete;



    // Functionalitatea aplicatiei:


    // 1. Butonul de Login:
    @FXML
    private String btnLogin(MouseEvent event) {
        String status = "Success";
        String uname = txtUsername.getText();
        String pass = txtPassword.getText();
        if(uname.isEmpty() || pass.isEmpty()) {
            setLblError(Color.TOMATO, "");
            status = "Eroare";


        } else {
            //query
            String sql = "SELECT * FROM register Where uname = ? and pass = ?";
            try {
                PreparedStatement preparedStatement = null;
                preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, uname);
                preparedStatement.setString(2, pass);
                ResultSet resultSet = preparedStatement.executeQuery();
                resultSet = preparedStatement.executeQuery();
                if (!resultSet.next()) {
                    setLblError(Color.TOMATO, "Introduceti corect email-ul sau parola! Daca nu aveti un cont, va rugam apasati pe SignUP!");
                    status = "Eroare!";
                    btnERP.setDisable(true);
                } else {
                    setLblError(Color.GREEN, "V-ati logat cu success! Puteti accesa aplicatia de tip ERP! Enjoy !");
                    btnERP.setDisable(false);
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                status = "Exceptie!";
            }
        }
        return status;
    }

    // 2. Facem conexiunea la baza de date:
    Connection con = null;
    public Main_Controller(){
        con = ConnectionUtil.conDB();
    }

    // 3. Aici avem label-ul de eroare:
    private void setLblError(Color color, String text) {
        lblErrors.setTextFill(color);
        lblErrors.setText(text);
        System.out.println(text);
        btnERP.setDisable(true);
    }

    // 4. Butonul de Sign UP:
    @FXML
    private void signUP (ActionEvent e){
    try{
        Parent signupParent;
        signupParent = (AnchorPane) FXMLLoader.load(getClass().getResource("/sample/SignUP.fxml"));

        Scene nscene = new Scene(signupParent);
        Stage mainWindow;
        mainWindow = (Stage) rootPane.getScene().getWindow();
        mainWindow.setTitle("Sign UP");
        mainWindow.setScene(nscene);
        btnERP.setDisable(true);

    } catch (IOException ex){
        btnERP.setDisable(true);
        Logger.getLogger(Main_Controller.class.getName()).log(Level.SEVERE, null, ex);
    }
    }



    // 5. Verificam conexiunea cu baza de date:
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (con == null) {
            lblErrors.setTextFill(Color.RED);
            btnERP.setDisable(true);
            lblErrors.setText("Eroare de server : Va rugam verificati conexiunea la baza de date!");
        } else {
            lblErrors.setTextFill(Color.GREEN);
            btnERP.setDisable(true);
            lblErrors.setText("Server deschis: Puteti intra!");
        }
    }

    // 6. Butonul de ERP (Enterprise Resource Planning):
    @FXML
    void btnERP (ActionEvent e) {
        btnERP.setOnAction(event ->{
            try {
                Parent btnERPParent;
                btnERPParent = (AnchorPane) FXMLLoader.load(getClass().getResource("/sample/ERP.fxml"));

                Scene nescene = new Scene(btnERPParent);
                Stage mainWindow;
                mainWindow = (Stage) rootPane.getScene().getWindow();
                mainWindow.setTitle("ERP System");
                mainWindow.setScene(nescene);




            } catch (IOException ex) {

                Logger.getLogger(Main_Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }


    // 7. Butonul de DELETE:
    @FXML
    void btnDelete (ActionEvent e) {
        btnDelete.setOnAction(event ->{
            try {
                Parent btnUpdateParent;
                btnUpdateParent = (AnchorPane) FXMLLoader.load(getClass().getResource("/sample/Delete.fxml"));

                Scene nescene = new Scene(btnUpdateParent);
                Stage mainWindow;
                mainWindow = (Stage) rootPane.getScene().getWindow();
                mainWindow.setTitle("Delete Account Form");
                mainWindow.setScene(nescene);
                btnERP.setDisable(true);


            } catch (IOException ex) {
                btnERP.setDisable(true);
                Logger.getLogger(Main_Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    // 8. Butonul de Exit :
    @FXML
    void btnExit(ActionEvent e){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Esti sigur?");
        ButtonType userOption = alert.showAndWait().get();
        if(userOption == ButtonType.CANCEL) {

            System.out.println("Utilizatorul a anulat sesiunea inchiderii aplicatiei!");

        }
        if(userOption == ButtonType.OK) {
            System.out.println("Utilizatorul a decis sa inchida aplicatia de Login & Register!");

        }
        System.exit(0);
    }

}
