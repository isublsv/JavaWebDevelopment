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
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Class describes edit password action command that used to edit user password.
 *
 * @author Dmitry Gartsmanovich
 */
public class EditPasswordActionCommand extends AuthorizedActionCommand {

    /**
     * The logger for EditPasswordActionCommand class.
     */
    private static final Logger LOGGER = LogManager.getLogger(
            EditPasswordActionCommand.class);

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

        HttpSession session = request.getSession();
        String currentPass = request.getParameter("currentpass");
        String newPass = request.getParameter("newpass");
        User authorizedUser = (User) session.getAttribute("authorizedUser");

        try {
            UserService userService = getFactory().getUserService();
            User updatedUser = userService
                    .updatePassword(authorizedUser.getId(),
                                    currentPass, newPass);
            request.setAttribute("authorizedUser", updatedUser);
            LOGGER.debug("Personal user password were updated successfully");
            request.getServletContext()
                   .getRequestDispatcher(ConfigurationManager.getProperty(
                           "path.page.profile"))
                   .forward(request, response);
        } catch (ServiceException e) {
            String message = e.getCode().getMessage();
            LOGGER.warn(message);
            request.setAttribute("errorMessage", message);
            request.getRequestDispatcher(
                    ConfigurationManager.getProperty("path.page.error"))
                   .forward(request, response);
        }
    }
}
