package org.test.dbservice.dao;

import org.test.dbservice.entity.FriendsEntity;

import java.util.List;

/**
 * Created by Taras on 09.12.2016.
 */
public interface FriendsDao extends DAO<FriendsEntity> {
    List<FriendsEntity> getAllFriendBy(int user_id);
}
