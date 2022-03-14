package sort.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sort.model.Difficulty;
import sort.model.SortData;
import sort.model.SortingRace;
import sort.model.sorts.BubbleSort;
import sort.model.sorts.Sort;
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
    @FXML
    ProgressBar progressBar;
    @FXML
    Label rightStatus;
    @FXML
    Label leftStatus;
    @FXML
    LineChart<Integer, Long> chart;
    @FXML
    TableColumn<SortData, Integer> durationCol;
    @FXML
    TableColumn<SortData, Long> swapCol;
    @FXML
    TableColumn<SortData, Integer> sizeCol;
    @FXML
    TableColumn<SortData, String> nameCol;
    @FXML
    TableView<SortData> table;
    @FXML
    MenuItem quitItem;
 
    private XYChart.Series<Integer, Long> bubble;
    private XYChart.Series<Integer, Long> merge;

    private SortingRace sorting;

    public void initialize() {
        init();

    }

    private void init() {
        leftStatus.setText("Thread actif:" + Thread.activeCount());

        nameCol.setCellValueFactory(new PropertyValueFactory<>("sort"));
        sizeCol.setCellValueFactory(new PropertyValueFactory<>("size"));
        swapCol.setCellValueFactory(new PropertyValueFactory<>("operations"));
        durationCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        this.bubble = new XYChart.Series<>();
        this.bubble.setName("Bubble sort");
        chart.getData().add(bubble);
        this.merge = new XYChart.Series<>();
        this.merge.setName("Merge sort");
        chart.getData().add(merge);

        for (Difficulty diff : Difficulty.values()) {
            this.configurationChoice.getItems().add(diff);
        }
        this.configurationChoice.getSelectionModel().selectFirst();
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1);
        this.threadSpinner.setValueFactory(valueFactory);
        this.sortChoice.getItems().addAll("Merge Sort", "Bubble sort");
        this.sortChoice.getSelectionModel().selectFirst();

        start.setOnAction(actionEvent -> {
            start();
        });
        quitItem.setOnAction(actionEvent -> System.exit(0));

    }

    private void start() {
        this.sorting = new SortingRace(this.threadSpinner.getValue(), (Difficulty) configurationChoice.getValue(), (String) sortChoice.getValue());
        sorting.subscribe(this);
        sorting.runSorting();
        leftStatus.setText("Thread actif:" + Thread.activeCount());
        progressBar.setProgress(0.1);

    }

    @Override
    public void update(Sort sort, int time, long operations, int size) {

        Platform.runLater(() -> {
            leftStatus.setText("Thread actif:" + Thread.activeCount());
            SortData sortData;
            if (sort instanceof BubbleSort) {
                sortData = new SortData("Bubble sort", time, operations, size);
                table.getItems().add(sortData);
                bubble.getData().add(new XYChart.Data<>(sortData.getSize(), sortData.getOperations()));
            } else {
                sortData = new SortData("Merge sort", time, operations, size);
                table.getItems().add(sortData);
                merge.getData().add(new XYChart.Data<>(sortData.getSize(), sortData.getOperations()));

            }
            double progress = progressBar.getProgress() + 0.1;
            progressBar.setProgress(progress);
            rightStatus.setText("Dernière exécution : " + sortData.getTime() + "ms");
        });
    }
}
