package sort.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import sort.model.Difficulty;
import sort.model.SortingRace;
import sort.model.util.Observer;

public class Controller implements Observer {

    @FXML
    private Spinner<Integer> threadSpinner;
    @FXML
    private ChoiceBox configurationChoice;
    @FXML
    private ChoiceBox sortChoice;
    @FXML
    private Button start;

    public void initialize() {
        init();

    }

    private void init() {
        for (Difficulty diff : Difficulty.values()) {
            this.configurationChoice.getItems().add(diff);
        }
        this.configurationChoice.getSelectionModel().selectFirst();

        this.sortChoice.getItems().addAll("Merge Sort", "Bubble sort");
        this.sortChoice.getSelectionModel().selectFirst();

        start.setOnAction(actionEvent -> {
            start();
        });

    }

    private void start() {
        System.out.println(sortChoice.getValue().toString());
        System.out.println(configurationChoice.getValue());
    }

}
