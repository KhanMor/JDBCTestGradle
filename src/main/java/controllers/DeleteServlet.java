package controllers;

import common.exceptions.DAOException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import services.servicesimpl.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Mordr on 23.02.2017.
 */
@WebServlet(urlPatterns = "/delete")
public class DeleteServlet extends SuperServlet {
    private static final Logger logger = Logger.getLogger(LoginServlet.class);

    @Autowired
    StudentService studentService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Integer id = Integer.parseInt(req.getParameter("id"));
            studentService.deleteStudent(id);
            resp.sendRedirect("/students/list");
        } catch (DAOException e) {
            logger.error(e);
            resp.sendRedirect("/students/error.jsp");
        }
    }
}
