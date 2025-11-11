package com.github.isaac.repositories.base;

import java.util.Collection;
import java.util.Optional;

public interface BaseRepository<T, ID> {
    void save(T entity);
    void update(T entity);
    void delete(T entity);
    void deleteById(ID id);
    Optional<T> findById(ID id);
    Collection<T> findAll();
}
