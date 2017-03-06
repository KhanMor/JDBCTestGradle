package models.dao;

import common.exceptions.DAOException;
import models.Student;
import models.connector.Connector;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mordr on 01.03.2017.
 */
@Repository
public class StudentDAO2 {
    private static final Logger logger = Logger.getLogger(UserDAO.class);

    public Student studentToStudent(ResultSet resultSet) throws SQLException {
        Student student = new Student();
        Integer id = resultSet.getInt("id");
        String stname = resultSet.getString("name");
        Date birthdate = resultSet.getDate("birthdate");
        String sex = resultSet.getString("sex");
        student.setId(id);
        student.setName(stname);
        student.setBirthdate(birthdate);
        student.setSex(sex);
        return student;
    }
    public Student get(Integer id) throws DAOException {
        String sql = "select * from student where id = ?";
        Connector connector = Connector.getInstance();
        Connection conn = connector.getConnection();
        try(PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Student student = studentToStudent(resultSet);
                return student;
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException();
        }
        return null;
    }

    public List<Student> list2() throws DAOException {
        String sql = "select * from student";
        Connector connector = Connector.getInstance();
        Connection conn = connector.getConnection();
        List<Student> students = new ArrayList<>();
        try(Statement statement = conn.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Student student = studentToStudent(resultSet);
                students.add(student);
            }
            return students;
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException();
        }
    }

    public void edit(Student student) throws DAOException {
        String sql="update student set name = ?, birthdate = ?, sex = ? where id = ?";
        Connector connector = Connector.getInstance();
        Connection conn = connector.getConnection();
        try(PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, student.getName());
            logger.trace(student.getName());
            preparedStatement.setDate(2, student.getBirthdate());
            logger.trace(student.getSex());
            preparedStatement.setString(3, student.getSex());
            preparedStatement.setInt(4, student.getId());
            int updatedCount = preparedStatement.executeUpdate();
            if(updatedCount == 0) {
                throw new DAOException();
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException();
        }
    }

    public void insert(Student student) throws DAOException {
        String sql="insert into student (name, birthdate, sex, group_id) values(?, ?, ?, ?)";
        Connector connector = Connector.getInstance();
        Connection conn = connector.getConnection();
        try(PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, student.getName());
            preparedStatement.setDate(2, student.getBirthdate());
            preparedStatement.setString(3, student.getSex());
            preparedStatement.setInt(4, 1);
            int insertedCount = preparedStatement.executeUpdate();
            if(insertedCount == 0) {
                throw new DAOException();
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException();
        }
    }

    public void delete(Integer id) throws DAOException {
        String sql = "delete from student where id = ?";
        Connector connector = Connector.getInstance();
        Connection conn = connector.getConnection();
        try(PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            int deletedCount = preparedStatement.executeUpdate();
            if(deletedCount == 0) {
                throw new DAOException();
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException();
        }
    }
}
