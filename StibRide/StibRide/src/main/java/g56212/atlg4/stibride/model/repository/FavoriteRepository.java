package g56212.atlg4.stibride.model.repository;

import g56212.atlg4.stibride.model.dao.FavoriteDao;
import g56212.atlg4.stibride.model.dto.FavoriteDto;

import java.util.List;

public class FavoriteRepository implements Repository<String, FavoriteDto> {
    private final FavoriteDao dao;

    public FavoriteRepository() throws RepositoryException {
        dao = FavoriteDao.getInstance();
    }

    public String add(FavoriteDto item) throws RepositoryException {
        String key = item.getKey();
        if (contains(item.getKey())) {
            dao.update(item);
        } else {
            key = dao.insert(item);
        }
        return key;
    }

    public void remove(String key) throws RepositoryException {
        dao.delete(key);
    }


    public boolean contains(String key) throws RepositoryException {
        FavoriteDto dto = dao.select(key);
        return dto != null;
    }


    @Override
    public List<FavoriteDto> getAll() throws RepositoryException {
        return dao.selectAll();
    }

    @Override
    public FavoriteDto get(String key) throws RepositoryException {
        return dao.select(key);
    }
}

