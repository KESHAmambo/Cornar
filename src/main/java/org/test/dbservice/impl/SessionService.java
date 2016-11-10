package org.test.dbservice.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.test.dbservice.utils.HibernateSessionFactory;

/**
 * Created by Taras on 09.11.2016.
 */
abstract class AbstractServiceSession  {
    private SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();;

    private Session currentSession;
    private Transaction currentTransaction;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public Session getCurrentSession() {
        return currentSession;
    }

    public Session openCurrentSession(){
        currentSession = getSessionFactory().getCurrentSession();
        return currentSession;
    }

    public Session openCurrentSessionWithTransaction(){
        currentSession = openCurrentSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    public void shutdownCurrentSession(){
        currentSession.close();
    }

    public void shutdownCurrentSessionWithTransaction(){
        currentTransaction.commit();
//        currentSession.close();
    }

}
