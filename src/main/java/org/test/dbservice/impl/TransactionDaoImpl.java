package org.test.dbservice.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.test.dbservice.dao.TransactionDao;
import org.test.dbservice.entity.TransactionEntity;
import org.test.logic.Lesson;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Taras on 30.11.2016.
 */
public class TransactionDaoImpl extends AbstractServiceSession implements TransactionDao {
    @Override
    public int create(TransactionEntity entity) {
        Session session;
        session = openCurrentSessionWithTransaction();
        int count = (int) session.save(entity);
        shutdownCurrentSession();
        return count-1;
    }

    @Override
    public void delete(TransactionEntity entity) {
        Session session;
        session = openCurrentSessionWithTransaction();
        session.delete(entity);
        shutdownCurrentSession();
    }

    @Override
    public TransactionEntity getById(int id) {
        return null;
    }

    @Override
    public void update(TransactionEntity entity) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public void saveTransaction(int course_id, int lesson_id, int user_id) {
        TransactionEntity transaction = new TransactionEntity();
        transaction.setCourseId(course_id);
        transaction.setLessonsId(lesson_id);
        transaction.setUserId(user_id);
        create(transaction);
        getCurrentSession().close();
    }

    @Override
    public List<TransactionEntity> getAllTransactionsToUser(int user_id) {
        Session session = openCurrentSessionWithTransaction();
        Query query = session.createQuery("from TransactionEntity as t where t.userId=:idOfUser");
        query.setInteger("idOfUser", user_id);
        List<TransactionEntity> transactions =  query.list();
        Logger.getLogger(TransactionDao.class.getName()).log(Level.INFO, "received transaction by user id");
        shutdownCurrentSession();
        getCurrentSession().close();
        return transactions;
    }
    public List<TransactionEntity> getAllTransactionsToLesson(int lesson_id) {
        Session session = openCurrentSessionWithTransaction();
        Query query = session.createQuery("from TransactionEntity as t where t.lessonsId=:idOfLesson");
        query.setInteger("idOfLesson", lesson_id);
        List<TransactionEntity> transactions =  query.list();
        Logger.getLogger(TransactionDao.class.getName()).log(Level.INFO,"received transaction by lesson id");
        shutdownCurrentSession();
        getCurrentSession().close();
        return transactions;
    }
}
