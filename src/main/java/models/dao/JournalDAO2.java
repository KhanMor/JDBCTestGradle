package models.dao;

import common.exceptions.DAOException;
import dao.StudentDAO;
import models.Lection;
import models.Student;
import models.connector.Connector;
import models.pojo.Journal;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mordr on 24.02.2017.
 */

public class JournalDAO2 {
    @Autowired
    StudentDAO2 studentDAO2;

    private static final Logger logger = Logger.getLogger(LectionDAO2.class);

    public List<Student> lectionJournal(Integer lection_id) throws DAOException {
        String sql = "select * from journal a " +
                        "inner join student b on a.id_student = b.id " +
                        " where a.id_lection = ?";
        Connector connector = Connector.getInstance();
        Connection conn = connector.getConnection();
        List<Student> students = new ArrayList<>();
        try(PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, lection_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Student student = studentDAO2.studentToStudent(resultSet);
                students.add(student);
            }
            return students;
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException();
        }
    }

    public static void addJournal(Journal journal) throws DAOException {
        String sql="insert into journal (id_lection, id_student, date) values(?, ?, ?)";
        Connector connector = Connector.getInstance();
        Connection conn = connector.getConnection();
        try(PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, journal.getLection_id());
            preparedStatement.setInt(2, journal.getStudent_id());
            preparedStatement.setTimestamp(3, journal.getDate());
            int insertedCount = preparedStatement.executeUpdate();
            if(insertedCount == 0) {
                throw new DAOException();
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException();
        }
    }

    public static void deleteJournal(Integer id) throws DAOException {
        String sql = "delete from journal where id = ?";
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
