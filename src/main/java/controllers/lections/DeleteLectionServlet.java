package controllers.lections;

import common.exceptions.DAOException;
import controllers.SuperServlet;
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
@WebServlet(urlPatterns = "/lections/delete")
public class DeleteLectionServlet extends SuperServlet {
    private static final Logger logger = Logger.getLogger(DeleteLectionServlet.class);

    @Autowired
    LectionService lectionService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Integer id = Integer.parseInt(req.getParameter("id"));
            lectionService.deleteLection(id);
            resp.sendRedirect("/students/lections");
        } catch (DAOException e) {
            logger.error(e);
            resp.sendRedirect("/students/error.jsp");
        }
    }
}
