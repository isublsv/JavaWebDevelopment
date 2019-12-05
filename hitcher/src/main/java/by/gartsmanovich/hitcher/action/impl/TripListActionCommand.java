package by.gartsmanovich.hitcher.action.impl;

import by.gartsmanovich.hitcher.action.ActionCommand;
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
import java.util.List;

/**
 * Class describes show trip info action command that used to display the list
 * of available trip with provided parameters.
 *
 * @author Dmitry Gartsmanovich
 */
public class TripListActionCommand extends ActionCommand {

    /**
     * The logger for ShowTripsActionCommand class.
     */
    private static final Logger LOGGER = LogManager.getLogger(
            TripListActionCommand.class);

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

        String cityFrom = request.getParameter("city_from");
        String cityTo = request.getParameter("city_to");
        String departure = request.getParameter("departure");

        try {
            TripService tripService = getFactory().getTripService();
            List<Trip> trips = tripService
                    .findTripsByValues(cityFrom, cityTo, departure);
            request.setAttribute("dest", trips);
            LOGGER.debug("Trip list was successfully loaded");

            if (trips.isEmpty()) {
                request.setAttribute("emptyTripList", "trip.show.empty");
                LOGGER.debug("No trips found");
            }

            request.getServletContext()
                   .getRequestDispatcher(ConfigurationManager.getProperty(
                           "path.page.find.list"))
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
