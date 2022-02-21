package sort;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sort.controller.Controller;


/**
 *
 * @author g56212
 */
public class Sort extends Application {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }
    /**
     *
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/sort.fxml"));
        Scene scene = new Scene(root);
        Controller ctrl = new Controller();
        stage.setTitle("Algorithmes de tri");
        stage.setScene(scene);
        stage.show();

    }

}
