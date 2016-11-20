package org.test.dbservice.impl;

import org.hibernate.Session;
import org.test.dbservice.dao.FilesDao;
import org.test.dbservice.entity.FilesEntity;

/**
 * Created by Taras on 20.11.2016.
 */
public class FilesDaoImpl extends AbstractServiceSession implements FilesDao {
    @Override
    public int create(FilesEntity entity) {
        Session session = openCurrentSessionWithTransaction();
        int count  = (int) session.save(entity);
        shutdownCurrentSession();
        return count;
    }

    @Override
    public void delete(FilesEntity entity) {
        Session session = openCurrentSessionWithTransaction();
        session.delete(entity);
        shutdownCurrentSession();
    }

    @Override
    public <T> T getById(Long id) {
        return null;
    }

    @Override
    public void update(FilesEntity entity) {

    }

    @Override
    public void deleteAll() {

    }
}
