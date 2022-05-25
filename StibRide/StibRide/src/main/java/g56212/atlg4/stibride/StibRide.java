package g56212.atlg4.stibride;

import g56212.atlg4.stibride.model.StibModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class StibRide extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        StibModel model = new StibModel();
        FXMLLoader fxmlLoader = new FXMLLoader(StibRide.class.getResource("stib-view.fxml"));
        VBox root = fxmlLoader.load();
        FXMLController viewCtrl = fxmlLoader.getController();
        Presenter presenter = new Presenter(model, viewCtrl);
        model.addObserver(presenter);
        model.initialize();
        viewCtrl.setButtonHandler(presenter);

        Scene scene = new Scene(root, 1280, 768);
        stage.setTitle("56212 - STIB");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}