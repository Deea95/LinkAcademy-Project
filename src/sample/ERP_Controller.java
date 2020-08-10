package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utils.ConnectionUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ERP_Controller implements Initializable {



    // Declararea variabilelor:

    @FXML
    private Button btnIesire;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TextField txtTitular;

    @FXML
    private TextField txtNrFF;

    @FXML
    private TextField txtSocietate;

    @FXML
    private TextField txtContract;

    @FXML
    private DatePicker dpDataContabila;

    @FXML
    private DatePicker dpDataRegistru;

    @FXML
    private TextField txtFurnizor;

    @FXML
    private TextField txtAdresaFurnizor;

    @FXML
    private TextArea taDescriere;

    @FXML
    private TextField txtReprezentant;

    @FXML
    private TextField txtContBancar;

    @FXML
    private TextField txtModalitatePlata;

    @FXML
    private TextField txtValuta;

    @FXML
    private DatePicker dpDataScadenta;

    @FXML
    private TextField txtTipFactura;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnCancel;

    @FXML
    private TextField txtCampanie;

    @FXML
    private Label lblTVA;

    @FXML
    private Label lblSumacuTVA;

    @FXML
    private TextField txtSumafaraTVA;



    // Functionalitatea butoanelor:


    // 1. Rezolvarea butoanelor:

    // a. Butonul de CANCEL:

    @FXML
    void btnCancel(ActionEvent event) {
        btnCancel.setOnAction(ev ->{
            txtTitular.setText(null);
            txtNrFF.setText(null);
            txtSocietate.setText(null);
            txtContract.setText(null);
            dpDataContabila.setValue(null);
            dpDataRegistru.setValue(null);
            txtFurnizor.setText(null);
            txtAdresaFurnizor.setText(null);
            txtReprezentant.setText(null);
            txtContBancar.setText(null);
            txtValuta.setText(null);
            dpDataScadenta.setValue(null);
            txtTipFactura.setText(null);
            taDescriere.setText(null);
            txtSumafaraTVA.setText(null);
            lblTVA.setText(null);
            lblSumacuTVA.setText(null);
            txtCampanie.setText(null);
        });
    }



    // b. Butonul FINALIZAT:

    // - Conectarea la baza de DATE:
    Connection con = null;
    public ERP_Controller()
    { con = ConnectionUtil.conDB(); }


    @FXML
    void btnFinalizat(ActionEvent event) {


           // Declararea variabilelor in BAZA de DATE:

            String titular = txtTitular.getText();
            int nr_ff = Integer.valueOf(txtNrFF.getText());
            LocalDate dataContabila = dpDataContabila.getValue();
            LocalDate dataRegistru = dpDataRegistru.getValue();
            String furnizor = txtFurnizor.getText();
            String modalitatePlata = txtModalitatePlata.getText();
            LocalDate dataScadenta = dpDataScadenta.getValue();
            String tipFactura = txtTipFactura.getText();
            String descriere = taDescriere.getText();
            int tva = Integer.valueOf(lblTVA.getText());
            String reprezentant = txtReprezentant.getText();
            String societate = txtSocietate.getText();
            int contract = Integer.valueOf(txtContract.getText());
            String adresa = txtAdresaFurnizor.getText();
            String contBancar = txtContBancar.getText();
            String valuta = txtValuta.getText();
            int sumacuTVA = Integer.valueOf(lblSumacuTVA.getText());
            String campanie = txtCampanie.getText();
            int sumafaraTVA = Integer.valueOf(txtSumafaraTVA.getText());



            // -> Inserarea datelor in baza de date :


                //query
                String sql = "INSERT INTO erp(titular,nr_ff,dataContabila,dataRegistru,furnizor,modalitatePlata ,dataScadenta,tipFactura,descriere,tva,reprezentant,societate,contract,adresa,contBancar, valuta,sumacuTVA,campanie, sumafaraTVA) VALUES (?,?, ?, ?, ?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                try {
                    PreparedStatement preparedStatement = null;
                    preparedStatement = con.prepareStatement(sql);
                    preparedStatement.setString(1, titular);
                    preparedStatement.setInt(2, nr_ff);
                    preparedStatement.setDate(3, Date.valueOf(dataContabila));
                    preparedStatement.setDate(4, Date.valueOf(dataRegistru));
                    preparedStatement.setString(5, furnizor);
                    preparedStatement.setString(6, modalitatePlata);
                    preparedStatement.setDate(7, Date.valueOf(dataScadenta));
                    preparedStatement.setString(8, tipFactura);
                    preparedStatement.setString(9, descriere);
                    preparedStatement.setInt(10, tva);
                    preparedStatement.setString(11, reprezentant);
                    preparedStatement.setString(12, societate);
                    preparedStatement.setInt(13, contract);
                    preparedStatement.setString(14, adresa);
                    preparedStatement.setString(15, contBancar);
                    preparedStatement.setString(16, valuta);
                    preparedStatement.setInt(17, sumacuTVA);
                    preparedStatement.setString(18, campanie);
                    preparedStatement.setInt(19, sumafaraTVA);
                     preparedStatement.executeUpdate();


                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.initStyle(StageStyle.UTILITY);
                        alert.setTitle("Finalizare");
                        alert.setHeaderText(null);
                        alert.setContentText("Factura completata cu success!");
                        alert.showAndWait();



                } catch (SQLException e) {
                    System.err.println("Eroare! Datele au fost completate deja!");
                }

            }

    // d. Butonul de IESIRE:

    @FXML
    void btnIesire(ActionEvent event) {
        btnIesire.setOnAction(ev -> {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Esti sigur?");
            ButtonType userOption = alert.showAndWait().get();
            if (userOption == ButtonType.CANCEL) {
                System.out.println("Utilizatorul a anulat sesiunea inchiderii aplicatiei!");

            }
            if (userOption == ButtonType.OK) {
                System.out.println("Utilizatorul a decis sa inchida aplicatia de ERP!");

            }
            System.exit(0);


        });
    }

    // e. Butonul de LOGOUT:

    @FXML
    void btnLogout(ActionEvent event) {
        btnLogout.setOnAction(ev -> {
            try {
                Parent logoutParent;
                logoutParent = (AnchorPane) FXMLLoader.load(getClass().getResource("/sample/sample.fxml"));

                Scene nscene = new Scene(logoutParent);
                Stage mainWindow;
                mainWindow = (Stage) rootPane.getScene().getWindow();
                mainWindow.setTitle("Login Form");
                mainWindow.setScene(nscene);
            } catch (IOException ex) {
                Logger.getLogger(Main_Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    // f. Cele 2 label-uri:
    @FXML
    private void lblTVA (MouseEvent ev){
        lblTVA.setOnMouseClicked(e->{
            lblTVA.setText("" + Integer.parseInt(txtSumafaraTVA.getText()) *19/100);
        });
    }

    @FXML
    private void lblSumacuTVA (MouseEvent ev){
        lblSumacuTVA.setOnMouseClicked(e->{
            lblSumacuTVA.setText(Integer.parseInt(lblTVA.getText()) + Integer.parseInt(txtSumafaraTVA.getText()) + "");
        });
    }




    @Override
    public void initialize(URL location, ResourceBundle resources) { }
}
