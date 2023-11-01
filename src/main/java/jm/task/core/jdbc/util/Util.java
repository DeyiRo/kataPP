package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String dbURL = "jdbc:mysql://localhost:3306/kata";
    private static final String dbUserName = "root";
    private static final String dbPass = "AoraeSophiere215!";
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
    }
        public static Connection getConnection() {
            Connection connection = null;
            try {
                //Driver driver = new FabricMySQLDriver();
                //DriverManager.registerDriver(driver);
                connection = DriverManager.getConnection(dbURL,dbUserName,dbPass);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return connection;
        }
}
