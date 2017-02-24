package controllers;

import common.exceptions.UserDaoException;
import models.dao.UserDAO;
import org.apache.log4j.Logger;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Шмыга on 23.02.2017.
 */
public class LoginServlet extends HttpServlet {


    private static Logger logger = Logger.getLogger(LoginServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        try {
            if (UserService.autorize(login, password)) {
                HttpSession session = req.getSession();
                session.setAttribute("id", "user_"+login);
                session.setMaxInactiveInterval(30*60);
                resp.sendRedirect("/students/list");
                logger.trace("Authorization successfull");
            } else {
                // req.getRequestDispatcher("/login.jsp").forward(req, resp);
                logger.trace("Authorization unsuccessful");
                resp.sendRedirect("/students/login");
            }
        } catch (UserDaoException e) {
            logger.error(e);
            resp.sendRedirect("/students/error.jsp");
        }

    }
}
