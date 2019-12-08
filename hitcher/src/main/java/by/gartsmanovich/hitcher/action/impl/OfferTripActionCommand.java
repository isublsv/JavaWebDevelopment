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
import java.util.Map;

/**
 * Class describes offer trip action command that used to create a new trip
 * with additional parameters.
 *
 * @author Dmitry Gartsmanovich
 */
public class OfferTripActionCommand extends AuthorizedActionCommand {

    /**
     * The logger for OfferTripActionCommand class.
     */
    private static final Logger LOGGER = LogManager.getLogger(
            OfferTripActionCommand.class);

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

        Map<String, String[]> parameterMap = request.getParameterMap();

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("authorizedUser");

        try {
            TripService tripService = getFactory().getTripService();
            tripService.save(user.getId(), parameterMap);
            LOGGER.debug("Trip was created successfully");
            response.sendRedirect(request.getContextPath()
                    + ConfigurationManager.getProperty(
                            "path.page.my.trips.action"));
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
