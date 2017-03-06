package services;

import common.exceptions.UserDAOException;
import models.dao.UserDAO;
import models.pojo.User;
import org.springframework.stereotype.Service;

/**
 * Created by Mordr on 23.02.2017.
 */
public interface UserService {
    public boolean authorize(String login, String password) throws UserDAOException;

    public User authorizeWithUser(String login, String password) throws UserDAOException;

    public boolean registration(String login, String password, String role) throws UserDAOException;
}
