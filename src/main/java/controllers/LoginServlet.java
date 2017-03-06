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
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Mordr on 23.02.2017.
 */
//@WebServlet(urlPatterns = "/login", loadOnStartup = 1)
public class LoginServlet extends SuperServlet {
    @Autowired
    private UserService userService;


    private static final Logger logger = Logger.getLogger(LoginServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        try {
            if(userService.authorize(login, password)) {
                logger.trace("successful login " + login + " " + password);
                resp.sendRedirect("/students/list");
                HttpSession session = req.getSession();
                session.setAttribute("user", login);
                session.setMaxInactiveInterval(30*60);
            }else{
                logger.trace("failed login " + login + " " + password);
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
            }
        } catch (UserDAOException e) {
            logger.error(e);
            resp.sendRedirect("/students/error.jsp");
        }
    }
}
