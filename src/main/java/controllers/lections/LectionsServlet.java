package controllers.lections;

import common.exceptions.DAOException;
import controllers.SuperServlet;
import models.Lection;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import services.servicesimpl.LectionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Mordr on 24.02.2017.
 */
@WebServlet(urlPatterns = "/lections")
public class LectionsServlet extends SuperServlet {
    private static final Logger logger = Logger.getLogger(LectionsServlet.class);

    @Autowired
    LectionService lectionService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Lection> lections = lectionService.getLections();
            req.setAttribute("lections", lections);
            req.getRequestDispatcher("/lections.jsp").forward(req, resp);
        } catch (DAOException e) {
            logger.error(e);
            resp.sendRedirect("/students/error.jsp");
        }
    }
}
