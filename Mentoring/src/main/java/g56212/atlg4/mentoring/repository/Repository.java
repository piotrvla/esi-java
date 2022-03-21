package g56212.atlg4.mentoring.repository;

import g56212.atlg4.mentoring.dto.Dto;
import java.util.List;


public interface Repository<K,T extends Dto<K>>{
    
    void add(T item) throws RepositoryException;

    void remove(K item) throws RepositoryException;

    boolean contains(K key) throws RepositoryException;

    T get(K key) throws RepositoryException;

    List<T> getAll() throws RepositoryException;

}
