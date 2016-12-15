package org.test.dbservice.impl;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.test.dbservice.dao.InboxDao;
import org.test.dbservice.entity.InboxEntity;
import org.test.logic.InboxMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Taras on 15.12.2016.
 */
public class InboxDaoImpl extends AbstractServiceSession implements InboxDao {
    @Override
    public int create(InboxEntity entity) {
        Session session = openCurrentSessionWithTransaction();
        session.save(entity);
        shutdownCurrentSession();
        return 1;
    }

    @Override
    public void delete(InboxEntity entity) {
        Session session = openCurrentSessionWithTransaction();
        session.delete(entity);
        shutdownCurrentSession();
    }

    @Override
    public InboxEntity getById(int id) {
        InboxEntity message = new InboxEntity();
        Session session;
        session = openCurrentSession();
        message = (InboxEntity) session.get(InboxEntity.class,id);
        shutdownCurrentSession();
        return message;
    }

    @Override
    public void update(InboxEntity entity) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public void saveMessage(InboxMessage message) {
        InboxEntity inboxEntity = new InboxEntity();
        inboxEntity.setSender_id(message.getSenderProfile().getId());
        inboxEntity.setReceiver_id(message.getReceiverProfile().getId());
        inboxEntity.setDate(new java.sql.Timestamp(message.getDate().getTime()));
        inboxEntity.setText(message.getText());
        inboxEntity.setTheme(message.getTheme());
        inboxEntity.setWasRead(message.isWasRead());
        create(inboxEntity);
    }

    @Override
    public List<InboxEntity> getMessagesToUser(int userId) {
        List<InboxEntity> messages = new ArrayList<>();
        Session session = openCurrentSessionWithTransaction();
        messages = (List<InboxEntity>) session.createCriteria(InboxEntity.class)
                    .add(Restrictions.eq("receiver_id", userId)).list();
        return messages;
    }
}
