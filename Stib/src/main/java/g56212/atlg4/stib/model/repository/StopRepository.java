package g56212.atlg4.stib.model.repository;


import g56212.atlg4.stib.model.dao.StopDao;
import g56212.atlg4.stib.model.dto.StopDto;
import javafx.util.Pair;


import java.util.List;

public class StopRepository implements Repository<Pair<Integer, Integer>, StopDto>{
    private final StopDao stop;
    public StopRepository() throws RepositoryException {
        stop = StopDao.getInstance();
    }

    @Override
    public boolean contains(Pair<Integer, Integer> key) throws RepositoryException {
        return stop.get(key)!=null;
    }

    @Override
    public StopDto get(Pair<Integer, Integer> key) throws RepositoryException {
        if(contains(key))
            return stop.get(key);
        else
            throw new RepositoryException("Cannot find");
    }

    @Override
    public List<StopDto> getAll() throws RepositoryException {
        return stop.getAll();
    }
}