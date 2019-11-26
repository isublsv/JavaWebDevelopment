package by.gartsmanovich.hitcher.action;

import by.gartsmanovich.hitcher.bean.Role;
import by.gartsmanovich.hitcher.service.factory.ServiceFactory;

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
}
