package g56212.atlg4.mentoring.repository;

import g56212.atlg4.mentoring.dto.StudentDto;
import java.io.IOException;
import java.util.List;

public class StudentRepository implements Repository<StudentDto> {
    private Dao<StudentDto> dao;

    public StudentRepository() {
        this.dao = StudentDao.getInstance();
    }

    @Override
    public void add(StudentDto item) throws IOException {
        if (contains(item)) {
            dao.update(item);
        } else {
            dao.insert(item);
        }
    }

    @Override
    public void remove(StudentDto item) throws IOException {
        if (contains(item)) {
            dao.delete(item);
        }
    }

    @Override
    public StudentDto get(StudentDto item) throws IOException {
        return dao.get(item);
    }

    @Override
    public List<StudentDto> getAll() throws IOException {
        return dao.getAll();
    }

    @Override
    public boolean contains(StudentDto item) throws IOException {
        return (dao.get(item) != null);
    }
}

