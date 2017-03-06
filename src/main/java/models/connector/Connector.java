package models.connector;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Mordr on 23.02.2017.
 */
public class Connector {
    private Connection connection;
    private static Connector ourInstance = new Connector();
    private static final Logger logger = Logger.getLogger(Connector.class);

    public static Connector getInstance() {
        return ourInstance;
    }

    private Connector() {
        String url= "jdbc:mysql://localhost:3306/students?autoReconnect=true&useSSL=false";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "khan";
        String password = "khan_megakey";
        try {
            Class.forName(driver).newInstance();
            this.connection = DriverManager.getConnection(url,userName,password);
        } catch (InstantiationException e) {
            logger.error(e.getMessage());
        } catch (IllegalAccessException e) {
            logger.error(e.getMessage());
        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage());
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
