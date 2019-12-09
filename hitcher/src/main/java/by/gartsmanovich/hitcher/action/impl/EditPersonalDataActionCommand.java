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
 * a personal user information such as name, surname, patronymic, phone
 * and address.
 *
 * @author Dmitry Gartsmanovich
 */
public class EditPersonalDataActionCommand extends AuthorizedActionCommand {

    /**
     * The logger for EditPersonalDataActionCommand class.
     */
    private static final Logger LOGGER = LogManager.getLogger(
            EditPersonalDataActionCommand.class);

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

        User authorizedUser = (User) session.getAttribute("authorizedUser");

        User editedUser = getEditedUser(request);
        editedUser.setId(authorizedUser.getId());
        try {
            UserService userService = getFactory().getUserService();
            User updatedUser = userService.updatePersonalData(editedUser);
            request.setAttribute("authorizedUser", updatedUser);
            request.setAttribute("activeTab", "personalDataTab");
            LOGGER.debug("Personal user information was updated successfully");
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

    /**
     * Gets user data from request parameters.
     *
     * @param request the provided request
     * @return the user entity.
     */
    private User getEditedUser(final HttpServletRequest request) {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String patronymic = request.getParameter("patronymic");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");

        User editedUser = new User();
        editedUser.setName(name);
        editedUser.setSurname(surname);
        editedUser.setPatronymic(patronymic);
        editedUser.setPhoneNumber(phone);
        editedUser.setAddress(address);

        return editedUser;
    }
}
