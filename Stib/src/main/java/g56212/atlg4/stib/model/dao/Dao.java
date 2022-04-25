package g56212.atlg4.stib.model.dao;

import g56212.atlg4.stib.model.dto.Dto;
import g56212.atlg4.stib.model.repository.RepositoryException;

import java.util.List;

public interface Dao<K, T extends Dto<K>> {

    T get(K key) throws RepositoryException;

    List<T> getAll() throws RepositoryException;
}
