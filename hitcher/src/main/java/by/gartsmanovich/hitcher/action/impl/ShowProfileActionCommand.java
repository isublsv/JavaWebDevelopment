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
import java.io.IOException;

import static by.gartsmanovich.hitcher.service.exception.ServiceErrorCodes.INVALID_VALUES;

/**
 * Class describes show user profile action command that used to show
 * additional information about selected user.
 *
 * @author Dmitry Gartsmanovich
 */
public class ShowProfileActionCommand extends ActionCommand {

    /**
     * The logger for ShowProfileActionCommand class.
     */
    private static final Logger LOGGER = LogManager.getLogger(
            ShowProfileActionCommand.class);

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

        String id = request.getParameter("id");

        try {
            if (id != null) {
                UserService userService = getFactory().getUserService();
                User user = userService.findUserProfileByID(id);
                request.setAttribute("user", user);

                String message = String.format(
                        "Full data for user \"%s\" was loaded successfully",
                        user.getLogin());
                LOGGER.debug(message);
                request.getServletContext().getRequestDispatcher(
                        ConfigurationManager.getProperty(
                                "path.page.profile.show")).forward(request,
                                                                   response);
            } else {
                throw new ServiceException(INVALID_VALUES);
            }
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
