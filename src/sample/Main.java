package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        // Facem conexiunea cu fisierul FXML:
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        // Renuntam la cele 3 butoane ale windows-ului:
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setMaximized(false);

        // Cream scena
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        // Aici avem editarile stage ului.
        primaryStage.setTitle("Enterprise Invoice Application");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
