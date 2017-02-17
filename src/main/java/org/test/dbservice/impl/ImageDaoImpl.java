package org.test.dbservice.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.test.dbservice.dao.ImageDao;
import org.test.dbservice.entity.ImageEntity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Taras on 15.12.2016.
 */
public class ImageDaoImpl extends AbstractServiceSession implements ImageDao {
    @Override
    public int create(ImageEntity entity) {
        Session session = openCurrentSessionWithTransaction();
        session.save(entity);
        shutdownCurrentSession();
        getCurrentSession().close();
        return 0;
    }

    @Override
    public void delete(ImageEntity entity) {

    }

    @Override
    public <T> T getById(int id) {
        return null;
    }

    @Override
    public void update(ImageEntity entity) {

    }

    @Override
    public void deleteAll() {

    }

    public void saveImage(int userId, byte[] imageData) {
        ImageEntity image = new ImageEntity();
        image.setUserId(userId);
        image.setData(imageData);
        create(image);
    }

    @Override
    public File getImageByUserId(int userId) {
        Session session = openCurrentSessionWithTransaction();
        Query query = session.createQuery("from ImageEntity where userId = :id");
        query.setParameter("id", userId);
        ImageEntity imageEntity = (ImageEntity) query.uniqueResult();
        if (imageEntity != null) {
            try {
                FileOutputStream fileOuputStream =
                        new FileOutputStream("image_" + imageEntity.getImageId());
                fileOuputStream.write(imageEntity.getData());
                fileOuputStream.close();
                return new File("image_" + imageEntity.getImageId());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        shutdownCurrentSession();
        getCurrentSession().close();
    return null;
    }
}
