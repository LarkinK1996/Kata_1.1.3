package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {

        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Ben", "Ivanov", (byte) 21);
        userService.saveUser("Anna", "Ivanova", (byte) 22);
        userService.saveUser("Patric", "Vasilev", (byte) 23);
        userService.saveUser("Stive", "Pupkin", (byte) 24);
        userService.getAllUsers().forEach(System.out::println);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}

