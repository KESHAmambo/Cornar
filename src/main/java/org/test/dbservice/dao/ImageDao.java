package org.test.dbservice.dao;

import org.test.dbservice.entity.ImageEntity;

import java.io.File;

/**
 * Created by Taras on 15.12.2016.
 */
public interface ImageDao extends DAO<ImageEntity> {
    void saveImage(int userId, byte[] imageData);
    File getImageByUserId(int userId);
}
