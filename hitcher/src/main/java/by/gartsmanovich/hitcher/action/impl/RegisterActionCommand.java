package by.gartsmanovich.hitcher.action.impl;

import by.gartsmanovich.hitcher.action.ActionCommand;
import by.gartsmanovich.hitcher.action.manager.ConfigurationManager;
import by.gartsmanovich.hitcher.bean.User;
import by.gartsmanovich.hitcher.service.UserService;
import by.gartsmanovich.hitcher.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Class describes register action command that used to register user in the
 * system if it not exist and save user entity in the session.
 *
 * @author Dmitry Gartsmanovich
 */
public class RegisterActionCommand extends ActionCommand {

    /**
     * The logger for RegisterActionCommand class.
     */
    private static final Logger LOGGER = LogManager.getLogger(
            RegisterActionCommand.class);

    /**
     * Handles the request and response and invoke appropriate method in the
     * Service Layer.
     *
     * @param request  the provided request information for HTTP servlets.
     * @param response the provided response information for HTTP servlets.
     * @throws IOException      if an input or output error is
     *                          detected when the servlet handles
     *                          the request
     * @throws ServletException if the request for the GET or POST
     *                          could not be handled
     */
    @Override
    public void execute(final HttpServletRequest request,
            final HttpServletResponse response) throws IOException,
            ServletException {
        String login = request.getParameter("login");
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");

        try {
            UserService userService = getFactory().getUserService();
            User user = userService.save(login, email, pass);
            HttpSession session = request.getSession();
            session.setAttribute("authorizedUser", user);
            request.getRequestDispatcher(
                    ConfigurationManager.getProperty("path.page.index"))
                   .forward(request, response);
        } catch (ServiceException e) {
            String message = e.getMessage();
            LOGGER.warn(message);
            request.setAttribute("errorMessage", message);
            request.getRequestDispatcher(
                    ConfigurationManager.getProperty("path.page.error"))
                   .forward(request, response);
        }
    }
}
