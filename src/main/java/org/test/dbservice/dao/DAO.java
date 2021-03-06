package org.test.dbservice.dao;

/**
 * Created by Taras on 27.10.2016.
 */
public interface DAO<T> {
    int create(T entity);
    void delete(T entity);
    <T> T getById(int id);
    void update(T entity);
    void deleteAll();
}
