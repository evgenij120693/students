package controllers;

import common.exceptions.UserDaoException;
import models.pojo.Student;
import org.apache.log4j.Logger;
import services.StudentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Шмыга on 23.02.2017.
 */
public class ListServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(LoginServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Student> list = StudentService.listShow();
            req.setAttribute("list", list);
            req.getRequestDispatcher("/list.jsp").forward(req, resp);
        } catch (UserDaoException e) {
            logger.error(e);
            resp.sendRedirect("/student/error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
