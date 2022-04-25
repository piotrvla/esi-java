package g56212.atlg4.stib;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StibRide extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StibRide.class.getResource("stib-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 768);
        stage.setTitle("G56212 - STIB Route");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}