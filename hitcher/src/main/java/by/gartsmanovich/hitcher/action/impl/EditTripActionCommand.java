package by.gartsmanovich.hitcher.action.impl;

import by.gartsmanovich.hitcher.action.manager.ConfigurationManager;
import by.gartsmanovich.hitcher.service.TripService;
import by.gartsmanovich.hitcher.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Class describes edit trip action command that used to edit information about
 * selected trip.
 *
 * @author Dmitry Gartsmanovich
 */
public class EditTripActionCommand extends AuthorizedActionCommand {

    /**
     * The logger for EditTripActionCommand class.
     */
    private static final Logger LOGGER = LogManager.getLogger(
            EditTripActionCommand.class);

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

        Map<String, String[]> map = request.getParameterMap();

        try {
            TripService tripService = getFactory().getTripService();
            tripService.update(map);
            LOGGER.debug("Trip information was updated successfully");
            String redirect = request.getHeader("referer");
            response.sendRedirect(redirect);
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
