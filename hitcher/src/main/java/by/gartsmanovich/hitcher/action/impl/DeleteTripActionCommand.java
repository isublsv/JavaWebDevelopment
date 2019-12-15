package by.gartsmanovich.hitcher.action.impl;

import by.gartsmanovich.hitcher.action.manager.ConfigurationManager;
import by.gartsmanovich.hitcher.bean.Role;
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
 * Class describes delete trip action command that used to delete trip for
 * current user.
 *
 * @author Dmitry Gartsmanovich
 */
public class DeleteTripActionCommand extends AuthorizedActionCommand {

    /**
     * The logger for DeleteTripActionCommand class.
     */
    private static final Logger LOGGER = LogManager.getLogger(
            DeleteTripActionCommand.class);

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
                tripService.deleteTripById(tripId);
                LOGGER.debug("Trip was deleted successfully");
                if (user.getRole() == Role.ADMIN) {
                    response.sendRedirect(request.getContextPath()
                                          + ConfigurationManager.getProperty(
                            "path.page.find.trip.action"));
                } else {
                    response.sendRedirect(request.getContextPath()
                                          + ConfigurationManager.getProperty(
                            "path.page.my.trips.action"));
                }
            } else {
                throw new ServiceException(INVALID_PARAMETER_VALUE);
            }
        } catch (ServiceException e) {
            processError(request, response, e);
        }
    }
}
