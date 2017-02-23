package controllers;

import common.exceptions.UserDaoException;
import org.apache.log4j.Logger;
import services.StudentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Шмыга on 23.02.2017.
 */
public class DeleteServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(AddServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.valueOf(
                (req.getParameter("id") != null) ?
                        req.getParameter("id") : "0");
        try {
            StudentService.deleteStudent(id);
            resp.sendRedirect("/students/list");
        } catch (UserDaoException e) {
            logger.error(e);
            resp.sendRedirect("/students/error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
