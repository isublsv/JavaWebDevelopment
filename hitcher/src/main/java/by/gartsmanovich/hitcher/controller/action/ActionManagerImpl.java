package by.gartsmanovich.hitcher.controller.action;

import by.gartsmanovich.hitcher.service.exception.ServiceException;
import by.gartsmanovich.hitcher.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Concrete Action Manager implementation.
 *
 * @author Dmitry Gartsmanovich
 */
public class ActionManagerImpl implements ActionManager {

    /**
     * Represents Service Factory.
     */
    private ServiceFactory factory;

    /**
     * Constructs Actrion Manager Implementation instance with provided Service
     * factory.
     *
     * @param factoryValue the provided Service factory instance.
     */
    public ActionManagerImpl(final ServiceFactory factoryValue) {
        this.factory = factoryValue;
    }

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
    @Override
    public void execute(final ActionCommand command,
            final HttpServletRequest request,
            final HttpServletResponse response) throws IOException,
            ServletException {
        command.setFactory(factory);
        command.execute(request, response);
    }

    /**
     * Closes action manager. Implicitly returns connection to the connection
     * pool.
     *
     * @throws ServiceException if failed to return connection.
     */
    @Override
    public void close() throws ServiceException {
        factory.close();
    }
}
