package org.test.dbservice.dao;

import org.test.dbservice.entity.FilesEntity;

import java.util.List;

/**
 * Created by Taras on 20.11.2016.
 */
public interface FilesDao extends DAO<FilesEntity> {
    void saveFile(String filename, byte[] fileToSave );

    byte[] getFileByName(String filename);

    List<FilesEntity> getAllFiles();
}
