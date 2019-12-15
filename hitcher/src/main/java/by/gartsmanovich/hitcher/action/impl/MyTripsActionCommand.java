package by.gartsmanovich.hitcher.action.impl;

import by.gartsmanovich.hitcher.action.manager.ConfigurationManager;
import by.gartsmanovich.hitcher.bean.Trip;
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
import java.util.List;

/**
 * Class describes my trips action command that used to load the user trip
 * list.
 *
 * @author Dmitry Gartsmanovich
 */
public class MyTripsActionCommand extends AuthorizedActionCommand {

    /**
     * The logger for MyTripsActionCommand class.
     */
    private static final Logger LOGGER = LogManager.getLogger(
            MyTripsActionCommand.class);

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
        User user = (User) session.getAttribute("authorizedUser");

        try {
            TripService tripService = getFactory().getTripService();
            List<Trip> trips = tripService.findTripsByUserId(user.getId());
            request.setAttribute("trips", trips);
            LOGGER.debug("Personal user trip list was loaded successfully");
            request.getServletContext()
                   .getRequestDispatcher(ConfigurationManager.getProperty(
                           "path.page.my.trips"))
                   .forward(request, response);
        } catch (ServiceException e) {
            processError(request, response, e);
        }
    }
}
