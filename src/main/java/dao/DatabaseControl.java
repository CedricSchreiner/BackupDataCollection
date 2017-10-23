package dao;

import model.File;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by Cedric on 23.10.2017.
 * This class provides all functions to add, edit or remove objects from the database
 */
public class DatabaseControl {
    private Session go_session;
    private SessionFactory go_sessionFactory;

    public DatabaseControl() {
        Configuration lo_configuration = new Configuration();
        lo_configuration.configure("hibernate/hibernate.cfg.xml");
        go_sessionFactory = lo_configuration.buildSessionFactory();
    }

    public void addObject(Object io_object) {
        go_session = go_sessionFactory.openSession();
        go_session.beginTransaction();
        go_session.saveOrUpdate(io_object);
        go_session.getTransaction().commit();
        go_session.close();
    }
}
