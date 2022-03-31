package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.SQLException;

public class Util {

    private static final String USERNAME = "root";
    private static final String PASSWORD = "gfhjkmbd1900";
    private static final String URL = "jdbc:mysql://localhost:3306/testDB";

    private static SessionFactory factory;

    private Util() {

    }

    private static void buildSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", URL);
        configuration.setProperty("hibernate.connection.username", USERNAME);
        configuration.setProperty("hibernate.connection.password", PASSWORD);
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.current_session_context_class", "thread");
        factory = configuration.addAnnotatedClass(User.class).buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        if (factory == null) {
            buildSessionFactory();
        }
        return factory;
    }

    public static Connection getConnection() {
        try (BasicDataSource ds = new BasicDataSource()) {
            ds.setUrl(URL);
            ds.setUsername(USERNAME);
            ds.setPassword(PASSWORD);
            return ds.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}

