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
@WebServlet(urlPatterns = "/lections/edit")
public class EditLectionServlet extends SuperServlet {
    private static final Logger logger = Logger.getLogger(EditLectionServlet.class);

    @Autowired
    LectionService lectionService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        try {
            Lection lection = lectionService.getLection(id);
            req.setAttribute("lection", lection);
            req.getRequestDispatcher("/lection.edit.jsp").forward(req, resp);
        } catch (DAOException e) {
            logger.error(e);
            resp.sendRedirect("/students/error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setCharacterEncoding("UTF-8");
            Integer id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            String subject = req.getParameter("subject");
            String text = req.getParameter("text");

            Lection lection = new Lection();
            lection.setId(id);
            lection.setName(name);
            lection.setSubject(subject);
            lection.setText(text);

            lectionService.updateLection(lection);
            resp.sendRedirect("/students/lections");
        } catch (DAOException e) {
            logger.error(e);
            resp.sendRedirect("/students/error.jsp");
        }
    }
}
