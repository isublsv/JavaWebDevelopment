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
 * Class describes edit personal data action command that used to edit
 * a personal user preferences such as music and communication.
 *
 * @author Dmitry Gartsmanovich
 */
public class EditPreferencesActionCommand extends AuthorizedActionCommand {

    /**
     * The logger for EditPreferencesActionCommand class.
     */
    private static final Logger LOGGER = LogManager.getLogger(
            EditPreferencesActionCommand.class);

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
        String communication = request.getParameter("communication");
        String music = request.getParameter("music");
        User authorizedUser = (User) session.getAttribute("authorizedUser");

        try {
            UserService userService = getFactory().getUserService();
            User updatedUser = userService.updatePreferences(
                    authorizedUser.getId(), music, communication);
            request.setAttribute("authorizedUser", updatedUser);
            request.setAttribute("activeTab", "preferencesTab");
            LOGGER.debug("Personal user preferences were updated successfully");
            response.sendRedirect(
                    request.getContextPath() + ConfigurationManager
                            .getProperty("path.page.profile.action"));
        } catch (ServiceException e) {
            String message = e.getErrorCode().getMessage();
            LOGGER.warn(message);
            request.setAttribute("errorMessage", message);
            request.getRequestDispatcher(
                    ConfigurationManager.getProperty("path.page.error"))
                   .forward(request, response);
        }
    }
}
