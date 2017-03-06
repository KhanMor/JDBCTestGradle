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
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by Mordr on 23.02.2017.
 */
@WebServlet(urlPatterns = "/create")
public class CreateServlet extends SuperServlet {
    private static final Logger logger = Logger.getLogger(LoginServlet.class);

    @Autowired
    StudentService studentService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/insert.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setCharacterEncoding("UTF-8");
            String name = req.getParameter("name");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = dateFormat.parse(req.getParameter("birthdate"));
            Date birthdate = new Date(date.getTime());
            String sex = req.getParameter("sex");

            Student student = new Student();
            student.setName(name);
            student.setBirthdate(birthdate);
            student.setSex(sex);
            studentService.insert(student);

            resp.sendRedirect("/students/list");
        } catch (DAOException e) {
            logger.error(e);
            resp.sendRedirect("/students/error.jsp");
        } catch (ParseException e) {
            logger.error(e);
            resp.sendRedirect("/students/error.jsp");
        }
    }
}
