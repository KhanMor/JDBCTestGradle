package dao;

import common.exceptions.DAOException;
import models.Student;
import models.connector.Connector;
import models.dao.UserDAO;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mordr on 16.02.2017.
 */
@Deprecated
public class StudentDAO {
    private final Connection conn;
    private static final Logger logger = Logger.getLogger(UserDAO.class);

    public StudentDAO(Connection conn) {
        this.conn = conn;
    }

    private void studentToConsole(ResultSet resultSet) throws SQLException {
        System.out.println(
                resultSet.getString("name") + " " +
                resultSet.getDate("birthdate") + " " +
                resultSet.getString("sex")
        );
    }

    public void list() throws SQLException {
        String sql = "select * from student";
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        System.out.println("student list: ");
        while(resultSet.next()) {
            studentToConsole(resultSet);
        }
    }

    public void select(String name) throws SQLException {
        String sql = "select * from student where name = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, name);
        ResultSet resultSet = preparedStatement.executeQuery();
        System.out.print("student selected :");
        if(resultSet!=null) {
            while (resultSet.next()) {
                studentToConsole(resultSet);
            }
        }
    }

    public void update(String oldname, String newname, Date birthdate, String sex, Integer group_id) throws SQLException {
        String sql = "update student set " +
                        "name = ?," +
                        "birthdate = ?," +
                        "sex = ?," +
                        "group_id = ? " +
                        "where name = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, newname);
        preparedStatement.setDate(2, birthdate);
        preparedStatement.setString(3, sex);
        preparedStatement.setInt(4, group_id);
        preparedStatement.setString(5, oldname);
        preparedStatement.executeUpdate();
        System.out.println("student " + oldname + " updated to " + newname);
    }

    public  void insert(String name, Date birthdate, String sex, Integer group_id) throws SQLException {
        String sql = "insert into student (name, birthdate, sex, group_id) values (?,?,?,?)";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, name);
        preparedStatement.setDate(2, birthdate);
        preparedStatement.setString(3, sex);
        preparedStatement.setInt(4, group_id);
        preparedStatement.executeUpdate();
        System.out.println("added student " + name);
    }

    public void delete(String name) throws SQLException {
        String sql = "delete from student where name = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, name);
        preparedStatement.executeUpdate();
        System.out.println("student " + name + " deleted");
    }

    public static void delete(Integer id) throws DAOException {
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
