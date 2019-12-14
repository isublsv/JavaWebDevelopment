package by.gartsmanovich.hitcher.action.impl;

import by.gartsmanovich.hitcher.action.manager.ConfigurationManager;
import by.gartsmanovich.hitcher.bean.User;
import by.gartsmanovich.hitcher.service.TripService;
import by.gartsmanovich.hitcher.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.gartsmanovich.hitcher.service.exception.ServiceErrorCodes.INVALID_PARAMETER_VALUE;

/**
 * Class describes register to trip action command.
 *
 * @author Dmitry Gartsmanovich
 */
public class TripRegistrationActionCommand extends AuthorizedActionCommand {

    /**
     * The logger for RegisterToTripActionCommand class.
     */
    private static final Logger LOGGER = LogManager.getLogger(
            TripRegistrationActionCommand.class);

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

        String tripId = request.getParameter("id");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("authorizedUser");

        try {
            if (tripId != null) {
                TripService tripService = getFactory().getTripService();
                tripService.addPassenger(user.getId(), tripId);
                LOGGER.debug("Registration to trip was completed successfully");
                String redirect = request.getHeader("referer");
                response.sendRedirect(redirect);
            } else {
                throw new ServiceException(INVALID_PARAMETER_VALUE);
            }
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
