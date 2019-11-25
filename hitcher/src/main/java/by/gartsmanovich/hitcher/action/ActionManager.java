package by.gartsmanovich.hitcher.action;

import by.gartsmanovich.hitcher.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Interface used to manage Action commands.
 *
 * @author Dmitry Gartsmanovich
 */
public interface ActionManager {

    /**
     * Sets the Service factory to provided Action Command. Encapsulates
     * Action command method invocation.
     *
     * @param command  the provided Action Command instance.
     * @param request  the provided request information for HTTP servlets.
     * @param response the provided response information for HTTP servlets.
     * @throws IOException      if an input or output error is
     *                          detected when the servlet handles
     *                          the request
     * @throws ServletException if the request for the GET or POST
     *                          could not be handled
     */
    void execute(ActionCommand command, HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException;

    /**
     * Closes action manager. Implicitly returns connection to the connection
     * pool.
     *
     * @throws ServiceException if failed to return connection.
     */
    void close() throws ServiceException;
}
