package org.example.service;

import java.util.List;

public interface CrudService<T> {
    void create(T entity);

    T getById(Object id);

    List<T> getAll();

    void update(T entity);

    void delete(T entity);
}

