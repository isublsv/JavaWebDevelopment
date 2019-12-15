package by.gartsmanovich.hitcher.action;

import by.gartsmanovich.hitcher.action.manager.ConfigurationManager;
import by.gartsmanovich.hitcher.bean.Role;
import by.gartsmanovich.hitcher.service.exception.ServiceException;
import by.gartsmanovich.hitcher.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Common class for Commands that exist in the application.
 *
 * @author Dmitry Gartsmanovich
 */
public abstract class ActionCommand {

    /**
     * The logger for ActionCommand class.
     */
    private static final Logger LOGGER = LogManager.getLogger(
            ActionCommand.class);

    /**
     * Represents Service factory.
     */
    private ServiceFactory factory;

    /**
     * Set of roles that Action command supports.
     */
    private Set<Role> allowRoles = new HashSet<>();

    /**
     * Gets factory.
     *
     * @return value of factory.
     */
    public ServiceFactory getFactory() {
        return factory;
    }

    /**
     * Sets factory.
     *
     * @param factoryValue value of factory.
     */
    public void setFactory(final ServiceFactory factoryValue) {
        factory = factoryValue;
    }

    /**
     * Handles the request and response and invoke appropriate method in the
     * Service Layer.
     *
     * @param request the provided request information for HTTP servlets.
     * @param response the provided response information for HTTP servlets.
     * @throws IOException      if an input or output error is
     *                          detected when the servlet handles
     *                          the request
     * @throws ServletException if the request for the GET or POST
     *                          could not be handled
     */
    public abstract void execute(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException;

    /**
     * Gets the list of roles that Action command supports.
     *
     * @return the list of roles.
     */
    public Set<Role> getAllowRoles() {
        return allowRoles;
    }

    /**
     * Logs the exception message and forwards to the error page.
     *
     * @param request  the provided request information for HTTP servlets.
     * @param response the provided response information for HTTP servlets.
     * @param e        the provided exception value that appears during command
     *                 execution.
     * @throws IOException      if an input or output error is
     *                          detected when the servlet handles
     *                          the request
     * @throws ServletException if the request for the GET or POST
     *                          could not be handled
     */
    public void processError(final HttpServletRequest request,
            final HttpServletResponse response,
            final ServiceException e) throws ServletException, IOException {
        String message = e.getErrorCode().getMessage();
        LOGGER.warn(message);
        request.setAttribute("errorMessage", message);
        request.getRequestDispatcher(
                ConfigurationManager.getProperty("path.page.error")).forward(
                request, response);
    }
}
