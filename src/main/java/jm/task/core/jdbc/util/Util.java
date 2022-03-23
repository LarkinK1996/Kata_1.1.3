package jm.task.core.jdbc.util;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class Util {

    private static final String USERNAME = "root";
    private static final String PASSWORD = "gfhjkmbd1900";
    private static final String URL = "jdbc:mysql://localhost:3306/testDB";

    static BasicDataSource ds = new BasicDataSource();

    private static void connectDataBase() {
        ds.setUrl(URL);
        ds.setUsername(USERNAME);
        ds.setPassword(PASSWORD);
    }

    public static Connection getConnection() {
        connectDataBase();
        Connection connection = null;
        try {
            connection = ds.getConnection();
            System.out.println("Connection is ok");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}

