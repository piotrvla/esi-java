package g56212.atlg4.stibride.model.dao;

import g56212.atlg4.stibride.model.dto.FavoriteDto;
import g56212.atlg4.stibride.model.dto.StationDto;
import g56212.atlg4.stibride.model.repository.RepositoryException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FavoriteDao implements Dao<String, FavoriteDto> {
    private Connection connection;

    private FavoriteDao() throws RepositoryException {
        connection = DBManager.getInstance().getConnection();
    }

    public static FavoriteDao getInstance() throws RepositoryException {
        return new FavoriteDao();
    }
    @Override
    public List<FavoriteDto> selectAll() throws RepositoryException {
        List<FavoriteDto> dtos = new ArrayList<>();
        String sql =
                "SELECT F.name,S.id,S.name,D.id,D.name " +
                        "FROM FAVORITES F " +
                        "JOIN STATIONS S ON S.id = F.id_source " +
                        "JOIN STATIONS D ON D.id = F.id_dest";
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                StationDto source = new StationDto(rs.getInt(2), rs.getString(3));
                StationDto dest = new StationDto(rs.getInt(4), rs.getString(5));

                FavoriteDto dto = new FavoriteDto(rs.getString(1), source, dest);
                dtos.add(dto);
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return dtos;
    }

    @Override
    public FavoriteDto select(String key) throws RepositoryException {
        if (key == null) {
            throw new RepositoryException("Aucune clé donnée en paramètre");
        }
        FavoriteDto dto = null;
        String sql =
                "SELECT F.name,S.id,S.name,D.id,D.name" +
                        " FROM FAVORITES F " +
                        "JOIN STATIONS S ON S.id = F.id_source " +
                        "JOIN STATIONS D ON D.id = F.id_dest " +
                        "WHERE  F.name = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, key);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                StationDto source = new StationDto(rs.getInt(2), rs.getString(3));
                StationDto dest = new StationDto(rs.getInt(4), rs.getString(5));
                dto = new FavoriteDto(rs.getString(1), source, dest);
            }
            return dto;
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }

    public String insert(FavoriteDto item) throws RepositoryException {
        if (item == null) {
            throw new RepositoryException("Aucune élément donné en paramètre");
        }
        String id = "";
        String sql = "INSERT INTO FAVORITES(name,id_source,id_dest) values(?, ?,?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, item.getKey());
            pstmt.setInt(2, item.getSource().getKey());
            pstmt.setInt(3, item.getDestination().getKey());
            pstmt.executeUpdate();
            id = item.getKey();
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return id;
    }

    public void update(FavoriteDto item) throws RepositoryException {
        if (item == null) {
            throw new RepositoryException("Aucune élément donné en paramètre");
        }
        String sql = "UPDATE FAVORITES SET name=? ,id_source=?,id_dest =? where name=?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, item.getKey());
            pstmt.setInt(2, item.getSource().getKey());
            pstmt.setInt(3, item.getDestination().getKey());
            pstmt.setString(4, item.getKey());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    public void delete(String key) throws RepositoryException {
        if (key == null) {
            throw new RepositoryException("Aucune clé donnée en paramètre");
        }
        String sql = "DELETE FROM FAVORITES WHERE name = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, key);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }
}
