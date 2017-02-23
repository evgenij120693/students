package controllers;

import common.exceptions.UserDaoException;
import models.dao.StudentDAO;
import models.pojo.Student;
import org.apache.log4j.Logger;
import services.StudentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Шмыга on 23.02.2017.
 */
public class AddServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(AddServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logger.trace("onPost");
        System.out.println("name " + req.getParameter("name"));
        String name = req.getParameter("name");
        String date = req.getParameter("bith_date");
        String sex = req.getParameter("sex");
        int id_group = Integer.valueOf(
                (req.getParameter("id_group") != null) ?
                        req.getParameter("id_group") : "0");
        Student student = new Student(0, name, date, sex, id_group);

        try {
            if (StudentService.insertStudent(student)) {
                resp.sendRedirect("/students/list");
            } else {
                resp.sendRedirect("/students/error.jsp");
            }
        } catch (UserDaoException e) {
            logger.error(e);
            resp.sendRedirect("/students/error.jsp");
        }
    }
}
