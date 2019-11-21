package by.gartsmanovich.hitcher.controller.action.impl;

import by.gartsmanovich.hitcher.bean.User;
import by.gartsmanovich.hitcher.controller.action.ActionCommand;
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
 * Class describes Builder command that proceed user request and
 * invoke appropriate method from Service layer of the application. The result
 * depends on input parameters.
 *
 * @author Dmitry Gartsmanovich
 */
public class LoginActionCommand extends ActionCommand {

    /**
     * The logger for BuilderCommand class.
     */
    private static final Logger LOGGER = LogManager.getLogger(
            LoginActionCommand.class);


    /**
     * Handles the request parameters and passes its to the Service application
     * layer.
     *
     * @param request the provide request information for HTTP servlets.
     * @throws IOException      if an input or output error is
     *                          detected when the servlet handles
     *                          the request
     * @throws ServletException if the request for the GET or POST
     *                          could not be handled
     */
    @Override
    public void execute(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String pass = request.getParameter("pass");
        if (login != null && pass != null) {
            UserService userService = getFactory().getUserService();
            try {
                User user = userService.findByLoginAndPassword(login, pass);
                HttpSession session = request.getSession();
                session.setAttribute("authorizedUser", user);
            } catch (ServiceException e) {
                String message = e.getMessage();
                LOGGER.warn(message);
                request.setAttribute("errorMessage", message);
                request.getRequestDispatcher("/WEB-INF/jsp/error/error.jsp")
                       .forward(request, response);
            }
        }
    }
}
