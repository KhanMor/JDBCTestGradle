package controllers.mvc;

import common.exceptions.DAOException;
import models.Student;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import services.servicesimpl.StudentService;

import java.util.List;

/**
 * Created by Mordr on 06.03.2017.
 */
@Controller
public class StudentController {
    private static final Logger logger = Logger.getLogger(StudentController.class);

    @Autowired
    StudentService studentService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String studentList(Model model) {
        List<Student> students = null;
        try {
            students = studentService.getAllStundents();
            model.addAttribute("students", students);
            return "list";
        } catch (DAOException e) {
            logger.error(e);
            return "error";
        }
    }
}
