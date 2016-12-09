package org.test.dbservice.impl;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.test.dbservice.dao.FriendsDao;
import org.test.dbservice.entity.FriendsEntity;

import java.util.List;

/**
 * Created by Taras on 09.12.2016.
 */
public class FriendsDaoImp extends AbstractServiceSession implements FriendsDao {
    @Override
    public int create(FriendsEntity entity) {
        Session session;
        session = openCurrentSessionWithTransaction();
        session.save(entity);
        shutdownCurrentSession();
        return 1;
    }

    @Override
    public void delete(FriendsEntity entity) {
        Session session;
        session = openCurrentSessionWithTransaction();
        session.delete(entity);
        shutdownCurrentSession();
    }

    @Override
    public <T> T getById(int id) {
        return null;
    }

    @Override
    public void update(FriendsEntity entity) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<FriendsEntity> getAllFriendBy(int user_id) {
        List<FriendsEntity> friends;
        Session session = openCurrentSessionWithTransaction();
        //Query query = session.createQuery("from FriendsEntity where userId=")
        friends = (List<FriendsEntity>) session.createCriteria(FriendsEntity.class)
                        .add(Restrictions.eq("userId", user_id)).list();
        shutdownCurrentSession();
        return friends;
    }
}
