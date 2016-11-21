package org.test.dbservice.dao;

import org.test.dbservice.entity.FilesEntity;

/**
 * Created by Taras on 20.11.2016.
 */
public interface FilesDao extends DAO<FilesEntity> {
    void saveFile(String filename, byte[] fileToSave );

    byte[] getFileByName(String filename);
}
