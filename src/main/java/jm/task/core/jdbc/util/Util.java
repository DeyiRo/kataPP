package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД
    //private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    //private static final String DBURL = "jdbc:mysql://localhost:3306/kata";
   // private static final String DBUSERNAME = "root";
   // private static final String DBPASS = "AoraeSophiere215!";

        private static SessionFactory sessionFactory;

        public static SessionFactory getSessionFactory() {
            if (sessionFactory == null) {
                try {
                    Configuration configuration = new Configuration();
                    Properties settings = new Properties();

                    settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                    settings.put(Environment.URL, "jdbc:mysql://localhost:3306/kata");
                    settings.put(Environment.USER, "root");
                    settings.put(Environment.PASS, "AoraeSophiere215!");
                    settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
                    settings.put(Environment.SHOW_SQL, "true");
                    settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                    settings.put(Environment.HBM2DDL_AUTO, "");

                    configuration.setProperties(settings);
                    configuration.addAnnotatedClass(User.class);

                    ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                            .applySettings(configuration.getProperties()).build();

                    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                } catch (Exception e) {
                    System.out.println("Problem creating session factory");
                    e.printStackTrace();
                }
            }

            return sessionFactory;
        }

        public static void closeSessionFactory() {
            getSessionFactory().close();
        }
    }

       /* public static Connection getConnection() {
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
}*/
