package g56212.atlg4.stib.model.repository;

import g56212.atlg4.stib.model.dto.Dto;

import java.util.List;

public interface Repository<K,T extends Dto<K>>{

    boolean contains(K key) throws RepositoryException;

    T get(K key) throws RepositoryException;

    List<T> getAll() throws RepositoryException;

}
