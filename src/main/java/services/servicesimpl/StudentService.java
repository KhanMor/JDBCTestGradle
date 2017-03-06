package services.servicesimpl;

import common.exceptions.DAOException;
import models.Student;
import models.dao.StudentDAO2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Mordr on 23.02.2017.
 */
@Service
public class StudentService {
    @Autowired
    StudentDAO2 studentDAO2;

    public List<Student> getAllStundents() throws DAOException {
        return studentDAO2.list2();
    }

    public Student getStudent(Integer id) throws DAOException {
        return studentDAO2.get(id);
    }

    public void editStudent(Student student) throws DAOException {
        studentDAO2.edit(student);
    }

    public void deleteStudent(Integer id) throws DAOException {
        studentDAO2.delete(id);
    }

    public void insert(Student student) throws DAOException {
        studentDAO2.insert(student);
    }
}
