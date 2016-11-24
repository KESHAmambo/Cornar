package org.test.dbservice.impl;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.test.dbservice.dao.FilesDao;
import org.test.dbservice.entity.FilesEntity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Taras on 20.11.2016.
 */
public class FilesDaoImpl extends AbstractServiceSession implements FilesDao {
    @Override
    public int create(FilesEntity entity) {
        Session session = openCurrentSessionWithTransaction();
        System.out.println(entity.getFileData());
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
    public FilesEntity getById(Long id) {
        FilesEntity file;
        Session session = openCurrentSessionWithTransaction();
        file = (FilesEntity) session.get(FilesEntity.class, id);
        shutdownCurrentSession();
        return file;
    }

    @Override
    public void update(FilesEntity entity) {

    }

    @Override
    public void deleteAll() {

    }
    //TODO: add owner to file
    @Override
    public void saveFile(String filename, byte[] fileToSave) {
        FilesEntity file = new FilesEntity();
        file.setFileName(filename);
        file.setFileData(fileToSave);
        Calendar cal = Calendar.getInstance();
        final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date currentDate = new Date(dateFormat.format(cal.getTime()));
        System.out.println((currentDate.getTime()));
        file.setCreation_date(new java.sql.Timestamp(currentDate.getTime()));
        create(file);
    }

    @Override
    public byte[] getFileByName(String filename) {
        Session session = openCurrentSessionWithTransaction();
        FilesEntity file = (FilesEntity) session.createCriteria(FilesEntity.class)
                .add(Restrictions.eq("file_name", filename)).uniqueResult();
        shutdownCurrentSession();
        return file.getFileData();
    }

    @Override
    public List<FilesEntity> getAllFiles() {
        Session session = openCurrentSessionWithTransaction();
        List<FilesEntity> allFiles = new ArrayList<>();
        Criteria crtForAll = session.createCriteria(FilesEntity.class);
        crtForAll.setMaxResults(10);
        allFiles = crtForAll.list();
        shutdownAbsolutleyCurrentSession();
        return allFiles;
    }
}
