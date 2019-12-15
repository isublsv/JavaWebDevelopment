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
 * Class describes edit driver info action command that used to add or edit
 * driver license, car and car color.
 *
 * @author Dmitry Gartsmanovich
 */
public class EditDriverInfoActionCommand extends AuthorizedActionCommand {

    /**
     * The logger for EditDriverInfoActionCommand class.
     */
    private static final Logger LOGGER = LogManager.getLogger(
            EditDriverInfoActionCommand.class);

    /**
     * Contains authorized user attribute value.
     */
    private static final String AUTHORIZED_USER = "authorizedUser";

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
        String license = request.getParameter("number");
        String carModel = request.getParameter("model");
        String carColor = request.getParameter("color");
        User authorizedUser = (User) session.getAttribute(AUTHORIZED_USER);

        try {
            UserService userService = getFactory().getUserService();
            User updatedUser = userService
                    .updateDriverInfo(authorizedUser.getId(),
                                    license, carModel, carColor);
            request.setAttribute(AUTHORIZED_USER, updatedUser);
            session.setAttribute("activeTab", "driverInfoTab");
            LOGGER.debug("Personal user driver info were updated successfully");
            response.sendRedirect(
                    request.getContextPath() + ConfigurationManager
                            .getProperty("path.page.profile.action"));
        } catch (ServiceException e) {
            processError(request, response, e);
        }
    }
}
