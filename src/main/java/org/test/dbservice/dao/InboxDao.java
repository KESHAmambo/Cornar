package org.test.dbservice.dao;

import org.test.dbservice.entity.InboxEntity;
import org.test.logic.InboxMessage;

import java.util.List;

/**
 * Created by Taras on 15.12.2016.
 */
public interface InboxDao extends DAO<InboxEntity> {
    void saveMessage(InboxMessage message);

    List<InboxEntity> getMessagesToUser(int userId);
}
