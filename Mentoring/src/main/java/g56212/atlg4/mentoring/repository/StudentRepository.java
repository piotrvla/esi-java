package g56212.atlg4.mentoring.repository;

import g56212.atlg4.mentoring.dto.StudentDto;
import java.util.List;

public class StudentRepository implements Repository<Integer, StudentDto> {

    private final StudentDao dao;

    public StudentRepository() {
        dao = StudentDao.getInstance();
    }

    @Override
    public void add(StudentDto item) throws RepositoryException {
        if (contains(item.getKey())) {
            dao.update(item);
        } else {
            dao.insert(item);
        }
    }

    @Override
    public void remove(Integer key) throws RepositoryException {
        if (contains(key)) {
            dao.delete(key);
        }
    }

    @Override
    public List<StudentDto> getAll() throws RepositoryException {
        return dao.getAll();
    }

    @Override
    public boolean contains(Integer key) throws RepositoryException {
        return (dao.get(key) != null);

    }

    @Override
    public StudentDto get(Integer key) throws RepositoryException {
        return dao.get(key);
    }
}
