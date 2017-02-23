package models.dao;

import common.exceptions.UserDaoException;
import models.connector.Connector;
import models.pojo.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Created by Шмыга on 23.02.2017.
 */
public class UserDAO {
    private static Logger logger = Logger.getLogger(UserDAO.class);
    private static final String SQL_FIND_USER = "Select * from example.user where login = ? and password = ?";
    private static final String SQL_INSERT_USER = "Insert into example.user (login, password, role)" +
            " values (?, ?, 'user')";

    public static User getUserByLoginAndPassword(String login, String password)
            throws UserDaoException {
        User user = null;

        try (Connection conn = Connector.getConnection();
             PreparedStatement prepS = conn.prepareStatement(SQL_FIND_USER)) {

            prepS.setString(1, login);
            prepS.setString(2, password);
            ResultSet resultSet = prepS.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setIdUser(resultSet.getInt("id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(resultSet.getString("role"));
            }else{
                logger.debug(login + " " + password + " not found");
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new UserDaoException();
        }
        return user;
    }

    public static boolean insertUser(String login, String password) throws UserDaoException {

        try (Connection conn = Connector.getConnection();
             PreparedStatement prepS = conn.prepareStatement(SQL_INSERT_USER)) {

            prepS.setString(1, login);
            prepS.setString(2, password);
            int countIsert = prepS.executeUpdate();
            if(countIsert > 0){
                logger.debug("Inserted "+countIsert);
                return true;
            }else{
                logger.debug(login + " " + password + " not inserted");
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new UserDaoException();
        }
        return false;
    }
}
