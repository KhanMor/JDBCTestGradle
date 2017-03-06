package controllers;

import common.exceptions.UserDAOException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Mordr on 23.02.2017.
 */
@WebServlet(urlPatterns = "/registration", loadOnStartup = 1)
public class RegistrationServlet extends SuperServlet {
    @Autowired
    private UserService userService;

    private static final Logger logger = Logger.getLogger(LoginServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String role = req.getParameter("role");
        try {
            if(userService.registration(login, password, role)) {
                logger.trace("successful user creation " + login + " " + password);
                resp.sendRedirect("/students/login");
            }else{
                logger.trace("failed user creation " + login + " " + password);
                /*req.getRequestDispatcher("/login.jsp").forward(req, resp);*/
            }
        } catch (UserDAOException e) {
            resp.sendRedirect("/students/error.jsp");
        }
    }
}
