package org.test.dbservice;

/**
 * Created by Аркадий on 27.10.2016.
 */
public class DatabaseServiceFactory {
    private static DatabaseServiceImpl ourInstance = new DatabaseServiceImpl();

    private DatabaseServiceFactory() {

    }

    public static DatabaseServiceImpl getService() {
        return ourInstance;
    }
}
