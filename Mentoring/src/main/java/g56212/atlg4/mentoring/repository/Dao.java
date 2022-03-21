package g56212.atlg4.mentoring.repository;

import java.util.List;

public interface Dao<T,K>{
    void insert(T item) throws RepositoryException;

    void delete(K key) throws RepositoryException;

    void update(T item) throws RepositoryException;

    T get(K key) throws RepositoryException;

    List<T> getAll() throws RepositoryException;
}
