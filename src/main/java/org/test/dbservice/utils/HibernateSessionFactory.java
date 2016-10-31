package org.test.dbservice.utils;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.test.dbservice.entity.UsersEntity;

/**
 * Created by Taras on 27.10.2016.
 */
public class HibernateSessionFactory {
    private static SessionFactory sessionFactory;
//
//    private static SessionFactory buildSessionFactory() {
//        Configuration configuration = new Configuration().configure();
//        configuration.addResource("UsersEntity.hbm.xml");
//        final ServiceRegistry registry = new StandardServiceRegistryBuilder()
//                .applySettings(configuration.getProperties()).build();
//
//        System.out.println("Hibernate Java Config serviceRegistry created");
//        try {
//            sessionFactory = configuration.buildSessionFactory(registry);
//        }
//        catch (HibernateException e){
//            StandardServiceRegistryBuilder.destroy(registry);
//            throw (new ExceptionInInitializerError("Initial Session Factory" + e.getStackTrace()));
//        }
//        return sessionFactory;
//    }
//
//    private static Configuration getConfiguration() {
//        Configuration configuration = new Configuration();
//        configuration.addAnnotatedClass(UsersEntity.class);
//
//        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL9Dialect");
//        configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
//        configuration.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5876/cornar");
//        configuration.setProperty("hibernate.connection.username", "java_cornar");
//        configuration.setProperty("hibernate.connection.password", "sample");
//        configuration.setProperty("hibernate.show_sql", "true");
//        return configuration;
//    }
//private static SessionFactory createSessionFactory() {
//    Configuration configuration = getConfiguration();
//    StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
//    builder.applySettings(configuration.getProperties());
//    ServiceRegistry serviceRegistry = builder.build();
//    System.out.printf("Creating\n");
//    return configuration.buildSessionFactory(serviceRegistry);
//}



    static {
        try {
            sessionFactory =  new Configuration().configure()
                    .buildSessionFactory();
            System.out.printf("Success");

        } catch (Exception e) {
            System.err.println("Initial SessionFactory creation failed." + e);
            throw new ExceptionInInitializerError(e);
        }
    }
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
