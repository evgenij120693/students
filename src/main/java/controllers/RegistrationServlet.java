package controllers;

import common.exceptions.UserDaoException;
import org.apache.log4j.Logger;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Шмыга on 23.02.2017.
 */
public class RegistrationServlet extends HttpServlet {

    private static Logger logger = Logger.getLogger(RegistrationServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        try {
            if (UserService.register(login, password)) {
                resp.sendRedirect("/students/login");
                logger.trace("Registration successful");
            } else {
                logger.trace("Registration failde");
                resp.sendRedirect("/students/");
            }
        } catch (UserDaoException e) {
            logger.error(e);
            resp.sendRedirect("/students/error.jsp");
        }

    }
}
