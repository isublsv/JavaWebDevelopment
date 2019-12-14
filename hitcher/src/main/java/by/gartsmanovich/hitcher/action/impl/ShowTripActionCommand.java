package by.gartsmanovich.hitcher.action.impl;

import by.gartsmanovich.hitcher.action.manager.ConfigurationManager;
import by.gartsmanovich.hitcher.bean.Trip;
import by.gartsmanovich.hitcher.service.TripService;
import by.gartsmanovich.hitcher.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class describes show trip action command that used to show user additional
 * information about selected trip.
 *
 * @author Dmitry Gartsmanovich
 */
public class ShowTripActionCommand extends AuthorizedActionCommand {

    /**
     * The logger for ShowTripActionCommand class.
     */
    private static final Logger LOGGER = LogManager.getLogger(
            ShowTripActionCommand.class);

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

        try {
            if (tripId != null) {
                TripService tripService = getFactory().getTripService();
                Trip trip = tripService.findTripById(tripId);
                request.setAttribute("trip", trip);
                LOGGER.debug("Trip was found by id");
                request.getServletContext().getRequestDispatcher(
                        ConfigurationManager.getProperty(
                                "path.page.my.trip.show"))
                       .forward(request, response);
            } else {
                request.getServletContext().getRequestDispatcher(
                        ConfigurationManager.getProperty(
                                "path.page.my.trips.action"))
                       .forward(request, response);
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
