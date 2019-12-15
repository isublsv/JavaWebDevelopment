package by.gartsmanovich.hitcher.action.impl;

import by.gartsmanovich.hitcher.action.manager.ConfigurationManager;
import by.gartsmanovich.hitcher.bean.User;
import by.gartsmanovich.hitcher.service.UserService;
import by.gartsmanovich.hitcher.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Class describes user list action command that used to get the list of all
 * users except administrators.
 *
 * @author Dmitry Gartsmanovich
 */
public class UserListActionCommand extends AdminActionCommand {

    /**
     * The logger for UserListActionCommand class.
     */
    private static final Logger LOGGER = LogManager.getLogger(
            UserListActionCommand.class);

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

        try {
            UserService userService = getFactory().getUserService();
            List<User> users = userService.findAll();
            request.setAttribute("users", users);

            String message = "User list was loaded successfully";
            LOGGER.debug(message);
            request.getServletContext()
                   .getRequestDispatcher(ConfigurationManager.getProperty(
                           "path.page.user.list"))
                   .forward(request, response);
        } catch (ServiceException e) {
            processError(request, response, e);
        }
    }
}
