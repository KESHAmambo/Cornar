package org.test.dbservice.impl;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.test.dbservice.dao.FilesDao;
import org.test.dbservice.entity.FilesEntity;

import java.util.Arrays;

/**
 * Created by Taras on 20.11.2016.
 */
public class FilesDaoImpl extends AbstractServiceSession implements FilesDao {
    @Override
    public int create(FilesEntity entity) {
        Session session = openCurrentSessionWithTransaction();
        System.out.println(entity.getFileData());
        int count  = (int) session.save(entity);
        shutdownCurrentSessionWithTransaction();
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

    @Override
    public void saveFile(String filename, byte[] fileToSave) {
        FilesEntity file = new FilesEntity();
        file.setFileName(filename);
        file.setFileData(fileToSave);
        create(file);
    }

    @Override
    public byte[] getFileByName(String filename) {
        Session session = openCurrentSessionWithTransaction();
        FilesEntity file = (FilesEntity) session.createCriteria(FilesEntity.class)
                .add(Restrictions.eq("file_name", filename)).uniqueResult();
        shutdownCurrentSessionWithTransaction();
        return file.getFileData();
    }
}
