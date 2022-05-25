package g56212.atlg4.stibride;

import g56212.atlg4.stibride.model.dto.FavoriteDto;
import g56212.atlg4.stibride.model.dto.StationDto;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import org.controlsfx.control.SearchableComboBox;

import java.util.List;

public class FXMLController {
    @FXML
    private Button btnSearch;

    @FXML
    private TableColumn<StationRow, List<Integer>> colLigne;

    @FXML
    private TableColumn<StationRow, StationDto> colStation;

    @FXML
    private ImageView imgMetro;

    @FXML
    private Label lNbStations;

    @FXML
    private Label lStatus;

    @FXML
    private SearchableComboBox<StationDto> scbDestination;

    @FXML
    private SearchableComboBox<StationDto> scbOrigine;

    @FXML
    private ScrollPane spMetro;

    @FXML
    private TableView<StationRow> tableStops;

    @FXML
    private SearchableComboBox<FavoriteDto> scbFavoris;

    @FXML
    private Button addFavoris;

    @FXML
    private Button removeFavoris;

    private TextInputDialog textInput;


    record StationRow(StationDto station, List<Integer> lines) {
    }

    @FXML
    public void initialize() {
        initTableView();

    }

    public void setButtonHandler(Presenter presenter) {
        this.btnSearch.setOnAction(event -> {
            presenter.searchShortestPath(scbOrigine.getValue(), scbDestination.getValue());
        });
        addFavoris.setOnAction(e -> {
            if (scbOrigine.getValue() != null && scbDestination.getValue() != null) {
                textInput = new TextInputDialog("");
                textInput.setResizable(true);
                textInput.setHeaderText("Entrée le nom du trajet \n(automatiquement mis à jour si le même nom est entrée)");
                textInput.setTitle("Trajet Favoris");
                textInput.setContentText("Nom : ");
                textInput.showAndWait();
                String fav_name = textInput.getResult();
                if (fav_name != null) {
                    FavoriteDto dto = new FavoriteDto(fav_name, scbOrigine.getValue(), scbDestination.getValue());
                    presenter.addFavorite(dto);
                    presenter.searchShortestPath(scbOrigine.getValue(), scbDestination.getValue());
                }
            }
        });
        removeFavoris.setOnAction(actionEvent -> {
            if (scbFavoris.getValue() != null) {
                presenter.deleteFavorite(scbFavoris.getValue().getKey());
            }
        });
    }
    public void setlNbStations(String text){
        lNbStations.setText("Nombre de stations: " + text);
    }

    public void addStation(StationRow row) {
        this.tableStops.getItems().add(row);
    }

    public void clearTable(){
        this.tableStops.getItems().removeAll();
    }

    public void initSearchableComboBox(List<StationDto> stations) {
        for (var station : stations) {
            scbOrigine.getItems().add(station);
            scbDestination.getItems().add(station);
        }
        scbOrigine.setValue(stations.get(0));
        scbDestination.setValue(stations.get(1));
    }

    private void initTableView() {
        colLigne.setCellValueFactory(element -> new ReadOnlyObjectWrapper<>(element.getValue().lines));
        colStation.setCellValueFactory(element -> new ReadOnlyObjectWrapper<>(element.getValue().station));
    }
    public void updateFavorite(List<FavoriteDto> list,Presenter presenter) {
        scbFavoris.setItems(FXCollections.observableArrayList(list));
        scbFavoris.setOnAction(e -> {
            if (scbFavoris.getValue() != null) {
                StationDto source = scbFavoris.getValue().getSource();
                StationDto dest = scbFavoris.getValue().getDestination();
                presenter.searchShortestPath(source, dest);
            }
        });
    }
}
