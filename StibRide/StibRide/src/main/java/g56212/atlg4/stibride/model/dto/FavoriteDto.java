package g56212.atlg4.stibride.model.dto;

import javafx.util.Pair;

public class FavoriteDto extends Dto<String> {
    private StationDto source;
    private StationDto destination;

    public FavoriteDto(String key) {
        super(key);
    }

    public FavoriteDto(String key,StationDto source, StationDto dest) {
        super(key);
        this.source = source;
        this.destination = dest;
    }

    public StationDto getSource() {
        return source;
    }

    public StationDto getDestination() {
        return destination;
    }

    @Override
    public String toString() {
        return key;
    }
}