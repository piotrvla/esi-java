package g56212.atlg4.stibride;

import g56212.atlg4.stibride.model.StibModel;
import g56212.atlg4.stibride.model.dto.FavoriteDto;
import g56212.atlg4.stibride.model.dto.StationDto;
import g56212.atlg4.stibride.model.dto.StopDto;
import g56212.atlg4.stibride.utils.observer.Observer;
import g56212.atlg4.stibride.FXMLController.StationRow;

import java.util.ArrayList;
import java.util.List;

public class Presenter implements Observer {

    private StibModel model;
    private FXMLController view;

    public Presenter(StibModel model, FXMLController view) {
        this.model = model;
        this.view = view;
    }

    public void searchShortestPath(StationDto source, StationDto destination) {
        model.searchPath(source, destination);
    }

    @Override
    public void update(String update) {
        if(update.equals("search end")){
            view.clearTable();
            for (StationDto station : model.getShortestPath()) {
                List<StopDto> stops = model.getStopsFromStation(station);
                List<Integer> lines = new ArrayList<>();
                for (StopDto stop : stops) {
                    lines.add(stop.getLine());
                }
                view.addStation(new StationRow(station, lines));
            }
        }
        if(update.equals("initialize")){
            view.initialize();
            view.setlNbStations(""+model.getStations().size());
            view.initSearchableComboBox(model.getStations());
        }
        if(update.equals("del fav") || update.equals("add fav")){
            view.updateFavorite(model.getFavorites(),this);
        }
    }

    public void addFavorite(FavoriteDto item) {
        model.addFavorite(item);
    }

    public void deleteFavorite(String key) {
        model.deleteFavorite(key);
    }
}
