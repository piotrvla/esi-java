package g56212.atlg4.stib.model.dao;

import g56212.atlg4.stib.model.dto.StopDto;
import g56212.atlg4.stib.model.repository.RepositoryException;
import g56212.atlg4.stib.utils.DBManager;
import javafx.util.Pair;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StopDao implements Dao<Pair<Integer, Integer>, StopDto> {

    private Connection connection;

    private StopDao() throws RepositoryException {
        connection = DBManager.getInstance().getConnection();
    }

    public static StopDao getInstance() throws RepositoryException {
        return StopDaoHolder.getInstance();
    }
    @Override
    public StopDto get(Pair<Integer, Integer> key) throws RepositoryException {
        if(key==null)
            throw new RepositoryException("Key does not exist");
        else if(key.getKey() == null || key.getValue()==null)
            throw new RepositoryException("Incomplete key");
        StopDto stop = null;
        String sql =  "SELECT STOPS.id_line, STOPS.id_station, STOPS.id_order, STATIONS.name " +
                "FROM STOPS " +
                "JOIN LINES ON LINES.id = STOPS.id_line " +
                "JOIN STATIONS ON STATIONS.id = STOPS.id_station " +
                "WHERE STOPS.id_line= ? AND STOPS.id_station = ?";
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, key.getKey());
            stmt.setInt(2, key.getValue());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                stop = new StopDto(
                        rs.getInt("id_line"),
                        rs.getInt("id_station"),
                        rs.getString("name"),
                        rs.getInt("id_order"));
            }
        }catch(SQLException e){
            throw new RepositoryException(e);
        }
        return stop;
    }

    @Override
    public List<StopDto> getAll() throws RepositoryException {
        List<StopDto> stops = new ArrayList<>();
        String sql =
                "SELECT STOPS.id_line, STOPS.id_station, STOPS.id_order, STATIONS.name " +
                        "FROM STOPS " +
                        "JOIN LINES ON LINES.id = STOPS.id_line " +
                        "JOIN STATIONS ON STATIONS.id = STOPS.id_station " +
                        "ORDER BY STOPS.id_line, STOPS.id_order";
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                stops.add(new StopDto(
                        rs.getInt("id_line"),
                        rs.getInt("id_station"),
                        rs.getString("name"),
                        rs.getInt("id_order")));
            }
        }catch(SQLException e){
            throw new RepositoryException(e);
        }
        return stops;
    }
    private static class StopDaoHolder {
        private static StopDao getInstance() throws RepositoryException {
            return new StopDao();
        }
    }
}
