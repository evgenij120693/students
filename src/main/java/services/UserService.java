package services;

import common.exceptions.UserDaoException;
import models.dao.UserDAO;

/**
 * Created by Шмыга on 23.02.2017.
 */
public class UserService {
    public static boolean autorize(String login, String password) throws UserDaoException {
        if(UserDAO.getUserByLoginAndPassword(login, password)!=null){
            return true;
        }else{
            return false;
        }
    }

    public static boolean register(String login, String password) throws UserDaoException {
        if(UserDAO.insertUser(login, password)){
            return true;
        }else{
            return false;
        }
    }
}
