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

/**
 * Created by Mordr on 24.02.2017.
 */
@WebServlet(urlPatterns = "/lections/add")
public class AddLectionServlet extends SuperServlet {
    private static final Logger logger = Logger.getLogger(AddLectionServlet.class);

    @Autowired
    LectionService lectionService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/lection.add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setCharacterEncoding("UTF-8");
            String name = req.getParameter("name");
            String subject = req.getParameter("subject");
            String text = req.getParameter("text");

            Lection lection = new Lection();
            lection.setName(name);
            lection.setSubject(subject);
            lection.setText(text);

            lectionService.createLection(lection);
            resp.sendRedirect("/students/lections");
        } catch (DAOException e) {
            logger.error(e);
            resp.sendRedirect("/students/error.jsp");
        }
    }
}
