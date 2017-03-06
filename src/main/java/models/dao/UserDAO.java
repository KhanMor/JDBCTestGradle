package models.dao;

import common.exceptions.UserDAOException;
import models.connector.Connector;
import models.pojo.User;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Mordr on 23.02.2017.
 */
@Repository
public class UserDAO {

    private static final Logger logger = Logger.getLogger(UserDAO.class);

    public User getUserByLoginAndPassword(String login, String password) throws UserDAOException {
        User user = new User();
        String sql = "select * from user where login = ? and password = ?";
        Connector connector = Connector.getInstance();
        Connection conn = connector.getConnection();
        try(PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(resultSet.getString("role"));
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new UserDAOException();
        }
        return user;
    }

    public boolean registrationUser(String login, String password, String role) throws UserDAOException{
        String sql = "insert into user (login, password, role) values (?, ?, ?)";
        Connector connector = Connector.getInstance();
        Connection conn = connector.getConnection();
        try(PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, role);
            int count = preparedStatement.executeUpdate();
            if(count > 0) {
                logger.trace("user inserted");
                return true;
            } else {
                logger.trace("user not inserted");
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new UserDAOException();
        }
        return false;
    }

}
