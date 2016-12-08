package org.test.dbservice.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.test.dbservice.utils.HibernateSessionFactory;

/**
 * Created by Taras on 09.11.2016.
 */
abstract class AbstractServiceSession  {
    private SessionFactory sessionFactory =  HibernateSessionFactory.getSessionFactory();

    private Session currentSession;
    private Transaction currentTransaction;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public Session getCurrentSession() {
        return currentSession;
    }

    public Session openCurrentSession(){
        currentSession = getSessionFactory().openSession();
        return currentSession;
    }

    public Session openCurrentSessionWithTransaction(){
        currentSession = openCurrentSession();
        openTransaction(currentSession);
        return currentSession;
    }

    public Session openTransaction(Session session){
        currentTransaction = session.beginTransaction();
        return session;
    }

    public void shutdownCurrentSession() {

        currentTransaction.commit();
    }
    public void shutdownAbsoluteleyCurrentSession() {
        currentTransaction.commit();
        currentSession.close();
    }

}
