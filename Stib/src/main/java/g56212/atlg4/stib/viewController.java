package g56212.atlg4.stib;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import org.controlsfx.control.SearchableComboBox;

public class viewController {

    @FXML
    private Button btnSearch;

    @FXML
    private TableColumn<?, ?> colLigne;

    @FXML
    private TableColumn<?, ?> colStation;

    @FXML
    private ImageView imgMetro;

    @FXML
    private Label lNbStations;

    @FXML
    private Label lStatus;

    @FXML
    private SearchableComboBox<?> scbDestination;

    @FXML
    private SearchableComboBox<?> scbOrigine;

    @FXML
    private ScrollPane spMetro;

    @FXML
    private TableView<?> tableStops;

    @FXML
    void onSearchButtonClick(ActionEvent event) {

    }

}
