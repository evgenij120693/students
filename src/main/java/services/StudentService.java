package services;

import common.exceptions.UserDaoException;
import models.dao.StudentDAO;
import models.dao.UserDAO;
import models.pojo.Student;

import java.util.List;

/**
 * Created by Шмыга on 23.02.2017.
 */
public class StudentService {
    public static List<Student> listShow() throws UserDaoException {
        return StudentDAO.selectAllStudent();
    }

    public static Student selectStudent(int id) throws UserDaoException {
        return StudentDAO.getStudentById(id);
    }

    public static  boolean updateStudent(Student student) throws UserDaoException {
        return StudentDAO.updateStudentById(student);
    }

    public static boolean insertStudent(Student student) throws UserDaoException {
        return StudentDAO.insertStudentById(student);
    }

    public static boolean deleteStudent(int id) throws UserDaoException {
        return StudentDAO.deleteStudent(id);
    }
}
