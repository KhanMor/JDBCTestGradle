package services.servicesimpl;

import common.exceptions.UserDAOException;
import models.dao.UserDAO;
import models.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.UserService;

/**
 * Created by Mordr on 01.03.2017.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;

    @Override
    public boolean authorize(String login, String password) throws UserDAOException {
        if(userDAO.getUserByLoginAndPassword(login, password).getId() != null) {
            return true;
        }else {
            return false;
        }
    }

    @Override
    public User authorizeWithUser(String login, String password) throws UserDAOException {
        return userDAO.getUserByLoginAndPassword(login, password);
    }

    @Override
    public boolean registration(String login, String password, String role) throws UserDAOException {
        return userDAO.registrationUser(login, password, role);
    }
}
