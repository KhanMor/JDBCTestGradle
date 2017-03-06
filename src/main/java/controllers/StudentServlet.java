package controllers;

import common.exceptions.DAOException;
import dao.StudentDAO;
import models.Student;
import models.dao.StudentDAO2;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Mordr on 22.02.2017.
 */
@WebServlet(urlPatterns = "/students", loadOnStartup = 1)
public class StudentServlet extends SuperServlet {
    @Autowired
    StudentDAO2 studentDAO2;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/students";
            String login = "khan";
            String password = "khan_megakey";

            try {
                List<Student> students = studentDAO2.list2();
                req.setAttribute("students", students);
            } catch (DAOException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
