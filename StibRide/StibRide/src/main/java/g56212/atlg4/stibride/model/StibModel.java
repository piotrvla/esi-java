package g56212.atlg4.stibride.model;

import g56212.atlg4.stibride.config.ConfigManager;
import g56212.atlg4.stibride.model.dijkstra.Dijkstra;
import g56212.atlg4.stibride.model.dijkstra.Graph;
import g56212.atlg4.stibride.model.dijkstra.Node;
import g56212.atlg4.stibride.model.dto.FavoriteDto;
import g56212.atlg4.stibride.model.dto.StationDto;
import g56212.atlg4.stibride.model.dto.StopDto;
import g56212.atlg4.stibride.model.repository.FavoriteRepository;
import g56212.atlg4.stibride.model.repository.RepositoryException;
import g56212.atlg4.stibride.model.repository.StationRepository;
import g56212.atlg4.stibride.model.repository.StopsRepository;
import g56212.atlg4.stibride.utils.observer.Observable;
import g56212.atlg4.stibride.utils.observer.Observer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StibModel implements Observable {
    private Graph graph;
    private StopsRepository stopsRepository;
    private StationRepository stationRepository;
    private FavoriteRepository favoriteRepository;
    private List<Observer> observers;
    private List<StationDto> shortestPath;
    private final List<FavoriteDto> favorite;

    public StibModel() {
        observers = new ArrayList<>();
        try {
            ConfigManager.getInstance().load();
            this.stopsRepository = new StopsRepository();
            this.stationRepository = new StationRepository();
            this.favoriteRepository = new FavoriteRepository();
        } catch (RepositoryException | IOException e) {
            e.printStackTrace();
        }
        initGraph();

        favorite = new ArrayList<>();
    }
    public void initialize(){
        this.shortestPath=new ArrayList<>();
        notifyObservers("initialize");
    }

    /**
     * retrieves every stop from the Repository
     *
     * @return list containing every stop inside the repository
     */
    public List<StopDto> getStops() {
        List<StopDto> stops = null;
        try {
            stops = stopsRepository.getAll();
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
        return stops;
    }

    /**
     * retrieves every station from the Repository
     *
     * @return list containing every station
     */
    public List<StationDto> getStations() {
        List<StationDto> stations = null;
        try {
            stations = stationRepository.getAll();
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
        return stations;
    }

    /**
     * Initialises the graph with every station there is inside the Repository
     * Every node (station) has a list of adjacent nods (stations).
     * <p>
     * The distance between each adjacent node is set to 1
     */
    public void initGraph() {
        List<StationDto> stations = getStations();
        graph = new Graph();
        stations.forEach(stationDto -> {
            Node station = new Node(stationDto);
            graph.addNode(station);
        });
        graph.getNodes().forEach(node -> {
            List<StationDto> adjacentStations = getAdjacentStations(node.getStation());
            adjacentStations.forEach(adj -> {
                Node adjacentNode = graph.getNode(adj);
                node.addDestination(adjacentNode, 1);
            });
        });
    }

    /**
     * Searches for the shortest path between a source and a destination station
     * Uses the dijkstra algorithm.
     *
     * @param source      source station
     * @param destination destination station
     */
    public void searchPath(StationDto source, StationDto destination) {
        shortestPath.clear();
        initGraph();
        Node sourceNode = graph.getNode(source);
        Graph graphShortestPath = Dijkstra.calculateShortestPathFromSource(graph, sourceNode);
        Node destinationNode = graphShortestPath.getNode(destination);
        destinationNode.getShortestPath().forEach(node -> {
            shortestPath.add(node.getStation());
        });
        notifyObservers("search end");
    }

    /**
     * retrieves the adjacent stations to a given station, without taking the line in consideration
     *
     * @param station given station
     * @return list of stations to which the given station is adjacentF
     */
    private List<StationDto> getAdjacentStations(StationDto station) {
        List<StationDto> adjacentStations = new ArrayList<>();
        List<StopDto> sameStations = getStopsFromStation(station);
        for (StopDto stop : sameStations) {
            for (StopDto adjStop : getAdjacentStops(stop)) {
                if (!adjacentStations.contains(adjStop.getStation())) {
                    adjacentStations.add(adjStop.getStation());
                }
            }
        }
        return adjacentStations;
    }

    /**
     * For a given station, retrieve each stop in which it appears
     *
     * @param station given station
     * @return list of stops where this station appears
     */
    public List<StopDto> getStopsFromStation(StationDto station) {
        List<StopDto> stops = new ArrayList<>();
        for (StopDto s : getStops()) {
            if (s.getStation().equals(station)) {
                stops.add(s);
            }
        }
        return stops;
    }

    /**
     * Retrieves every adjacent stop on the same line to the given stop in parameter
     *
     * @param stop given stop
     * @return list containing every adjacent stop on same line as the given stop
     */
    private List<StopDto> getAdjacentStops(StopDto stop) {
        List<StopDto> adjacentStops = new ArrayList<>();
        for (StopDto s : getStops()) {
            if (s.getLine() == stop.getLine()) {
                if ((s.getOrder() == stop.getOrder() + 1) ||
                        (s.getOrder() == stop.getOrder() - 1)) {
                    adjacentStops.add(s);
                }
            }
        }
        return adjacentStops;
    }
    public List<StationDto> getShortestPath(){
        return shortestPath;
    }
    public void addFavorite(FavoriteDto item) {
        try {
            favoriteRepository.add(item);
            favorite.clear();
            favorite.addAll(favoriteRepository.getAll());
            notifyObservers("add fav");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteFavorite(String key) {
        try {
            favoriteRepository.remove(key);
            favorite.clear();
            favorite.addAll(favoriteRepository.getAll());
            notifyObservers("del fav");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<FavoriteDto> getFavorites(){
        return favorite;
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String update) {
        for (Observer observer : observers) {
            observer.update(update);
        }
    }
}
