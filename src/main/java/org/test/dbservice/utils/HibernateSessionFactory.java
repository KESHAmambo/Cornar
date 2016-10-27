package org.test.dbservice.utils;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Created by Taras on 27.10.2016.
 */
public class HibernateSessionFactory {
    private static SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
//        configuration.addResource("UsersEntity.hbm.xml");
//        final ServiceRegistry registry = new StandardServiceRegistryBuilder()
//                .applySettings(configuration.getProperties()).build();

        try {
            sessionFactory = configuration.buildSessionFactory();
        }
        catch (HibernateException e){

            throw (new ExceptionInInitializerError("Initial Session Factory"
                                                + e.getStackTrace()));
        }
        return sessionFactory;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
