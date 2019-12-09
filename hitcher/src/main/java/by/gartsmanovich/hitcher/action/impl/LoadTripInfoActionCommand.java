package by.gartsmanovich.hitcher.action.impl;

import by.gartsmanovich.hitcher.action.ActionCommand;
import by.gartsmanovich.hitcher.action.manager.ConfigurationManager;
import by.gartsmanovich.hitcher.bean.Destination;
import by.gartsmanovich.hitcher.bean.Status;
import by.gartsmanovich.hitcher.bean.User;
import by.gartsmanovich.hitcher.service.DestinationService;
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
 * Class describes load trip info action command that used preload the list of
 * destinations.
 *
 * @author Dmitry Gartsmanovich
 */
public class LoadTripInfoActionCommand extends ActionCommand {

    /**
     * The logger for LoadTripActionCommand class.
     */
    private static final Logger LOGGER = LogManager.getLogger(
            LoadTripInfoActionCommand.class);
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

        String key = request.getParameter("action");
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("authorizedUser");

        try {
            DestinationService destinationService = getFactory()
                    .getDestinationService();
            List<Destination> destinations = destinationService.findAll();
            request.setAttribute("dest", destinations);
            LOGGER.debug("Destination list was successfully loaded");

            String forward;
            if (key != null && key.contains("offer")) {
                if (user != null && user.getStatus() != Status.BANNED) {
                    forward = ConfigurationManager.getProperty(
                            "path.page.offer.trip");
                    LOGGER.warn("Unauthorized access to offer trip page");
                } else {
                    forward = ConfigurationManager
                            .getProperty("path.page.find.trip");
                }
            } else {
                forward = ConfigurationManager
                        .getProperty("path.page.find.trip");
            }
            request.getServletContext().getRequestDispatcher(forward)
                   .forward(request, response);
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
