package controllers.journal;

import common.exceptions.DAOException;
import controllers.SuperServlet;
import models.Student;
import models.dao.JournalDAO2;
import models.dao.LectionDAO2;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Mordr on 24.02.2017.
 */
@WebServlet(urlPatterns = "/journal")
public class JournalServlet extends SuperServlet {
    private static final Logger logger = Logger.getLogger(LectionDAO2.class);

    @Autowired
    JournalDAO2 journalDAO2;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Integer lection_id = Integer.parseInt(req.getParameter("lection_id"));
            List<Student> students = journalDAO2.lectionJournal(lection_id);
            req.setAttribute("students", students);
            req.getRequestDispatcher("/journal.jsp").forward(req, resp);
        } catch (DAOException e) {
            logger.error(e);
            resp.sendRedirect("/students/error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
