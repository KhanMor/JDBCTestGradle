package models.dao;

import common.exceptions.DAOException;
import models.Lection;
import models.connector.Connector;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mordr on 24.02.2017.
 */
@Repository
public class LectionDAO2 {
    private static final Logger logger = Logger.getLogger(LectionDAO2.class);

    public Lection rsToLection(ResultSet rs) throws SQLException {
        Lection lection = new Lection();
        lection.setId(rs.getInt("id"));
        lection.setName(rs.getString("name"));
        lection.setSubject(rs.getString("subject"));
        lection.setText(rs.getString("text"));
        return  lection;
    }
    public Lection get(Integer id) throws DAOException {
        String sql = "select * from lection where id = ?";
        Connector connector = Connector.getInstance();
        Connection conn = connector.getConnection();
        try(PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Lection lection = rsToLection(resultSet);
                return lection;
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException();
        }
        return null;
    }

    public List<Lection> list() throws DAOException {
        String sql = "select * from lection";
        Connector connector = Connector.getInstance();
        Connection conn = connector.getConnection();
        List<Lection> lections = new ArrayList<>();
        try(Statement statement = conn.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Lection lection = rsToLection(resultSet);
                lections.add(lection);
            }
            return lections;
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException();
        }
    }

    public void add(Lection lection) throws DAOException {
        String sql="insert into lection (name, subject, text) values(?, ?, ?)";
        Connector connector = Connector.getInstance();
        Connection conn = connector.getConnection();
        try(PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, lection.getName());
            preparedStatement.setString(2, lection.getSubject());
            preparedStatement.setString(3, lection.getText());
            int insertedCount = preparedStatement.executeUpdate();
            if(insertedCount == 0) {
                throw new DAOException();
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException();
        }
    }

    public void delete(Integer id) throws DAOException{
        String sql = "delete from lection where id = ?";
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
    public void edit(Lection lection) throws DAOException {
        String sql="update lection set name = ?, subject = ?, text = ? where id = ?";
        Connector connector = Connector.getInstance();
        Connection conn = connector.getConnection();
        try(PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, lection.getName());
            preparedStatement.setString(2, lection.getSubject());
            preparedStatement.setString(3, lection.getText());
            preparedStatement.setInt(4, lection.getId());
            int updatedCount = preparedStatement.executeUpdate();
            if(updatedCount == 0) {
                throw new DAOException();
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException();
        }
    }

    public List<Lection> getNeariest() throws DAOException {
        String sql = "select * from lection a inner join jounal b on a.id = b.id_lection where date > ? and date <?";
        Connector connector = Connector.getInstance();
        Connection conn = connector.getConnection();
        List<Lection> lections = new ArrayList<>();
        try(PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
            preparedStatement.setTimestamp(2, new Timestamp(System.currentTimeMillis() + 60*60*100));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Lection lection = rsToLection(resultSet);
                lections.add(lection);
            }
            return lections;
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException();
        }
    }
}
