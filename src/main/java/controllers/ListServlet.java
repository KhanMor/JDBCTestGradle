package controllers;

import common.exceptions.DAOException;
import models.Student;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import services.servicesimpl.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Mordr on 23.02.2017.
 */
//@WebServlet(urlPatterns = "/list", loadOnStartup = 1)
public class ListServlet extends SuperServlet {
    private static final Logger logger = Logger.getLogger(ListServlet.class);

    @Autowired
    StudentService studentService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Student> students = studentService.getAllStundents();
            req.setAttribute("students", students);
            getServletContext().getRequestDispatcher("/list.jsp").forward(req, resp);
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
