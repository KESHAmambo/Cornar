package org.test.dbservice.dao;

/**
 * Created by Taras on 27.10.2016.
 */
public interface DAO<T> {
    void create(T entity);
    void delete(T entity);
    <T> T getById(Long id);
    void update(T entity);
    Integer deleteAll();
}
