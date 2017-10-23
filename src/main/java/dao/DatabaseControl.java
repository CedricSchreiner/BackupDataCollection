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

    public DatabaseControl() {
        Configuration lo_configuration = new Configuration();
        lo_configuration.configure("hibernate/hibernate.cfg.xml");
        SessionFactory lo_sessionFactory = lo_configuration.buildSessionFactory();
        go_session = lo_sessionFactory.openSession();
    }

    public void addFile(File io_file) {
        go_session.beginTransaction();
        go_session.saveOrUpdate(io_file);
        go_session.getTransaction().commit();
        System.out.println(io_file.getFileId());
        go_session.close();
    }
}
