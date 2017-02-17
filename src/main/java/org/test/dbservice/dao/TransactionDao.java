package org.test.dbservice.dao;

import org.test.dbservice.entity.TransactionEntity;

import java.util.List;

/**
 * Created by Taras on 30.11.2016.
 */
public interface TransactionDao extends DAO<TransactionEntity> {
    void saveTransaction(int course_id, int lesson_id, int user_id);
    List<TransactionEntity> getAllTransactionsToUser(int user_id);
    public List<TransactionEntity> getAllTransactionsToLesson(int lesson_id);

}
