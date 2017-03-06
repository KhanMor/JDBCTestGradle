package controllers.mvc;

import common.exceptions.UserDAOException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import services.UserService;

import javax.servlet.http.HttpSession;

/**
 * Created by Mordr on 06.03.2017.
 */
@Controller
public class LoginController {
    private static final Logger logger = Logger.getLogger(LoginController.class);
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginPage() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String doLogin(@RequestParam String login, @RequestParam String password, HttpSession session) {
        logger.trace("successful login " + login + " " + password);
        try {
            if(userService.authorize(login, password)) {
                session.setAttribute("user", login);
                session.setMaxInactiveInterval(30 * 60);
                return "redirect:/list";
            }
            else {
                logger.trace("failed login " + login + " " + password);
                return "/login";
            }
        } catch (UserDAOException e) {
            logger.error(e);
            return "error";
        }
    }

    public String doLogout(HttpSession session) {
        if(session != null){
            session.invalidate();
        }
        return "/login";
    }
}
