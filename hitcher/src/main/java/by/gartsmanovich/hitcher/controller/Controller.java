package by.gartsmanovich.hitcher.controller;

import by.gartsmanovich.hitcher.controller.action.ActionCommand;
import by.gartsmanovich.hitcher.controller.action.ActionCommandFactory;
import by.gartsmanovich.hitcher.controller.action.ActionManager;
import by.gartsmanovich.hitcher.controller.action.ActionManagerFactory;
import by.gartsmanovich.hitcher.dao.exception.DaoException;
import by.gartsmanovich.hitcher.dao.pool.ConnectionPool;
import by.gartsmanovich.hitcher.dao.transaction.factory.TransactionFactoryImpl;
import by.gartsmanovich.hitcher.service.exception.ServiceException;
import by.gartsmanovich.hitcher.service.factory.ServiceFactory;
import by.gartsmanovich.hitcher.service.factory.ServiceFactoryImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class represents the Controller layer of the application. Created to handle
 * requests from user, invoke the commands and return the results from Service
 * layer of the application.
 *
 * @author Dmitry Gartsmanovich
 */
@WebServlet("/controller")
public class Controller extends HttpServlet {

    /**
     * The logger for Controller class.
     */
    private static final Logger LOGGER = LogManager.getLogger(Controller.class);

    /**
     * Called by the server (via the <code>service</code> method) to
     * allow a servlet to handle a GET request.
     *
     * @param req  an {@link HttpServletRequest} object that
     *             contains the request the client has made
     *             of the servlet
     * @param resp an {@link HttpServletResponse} object that
     *             contains the response the servlet sends
     *             to the client
     */
    @Override
    protected void doGet(final HttpServletRequest req,
            final HttpServletResponse resp) {
        try {
            process(req, resp);
        } catch (ServletException e) {
            LOGGER.error("request for the GET could not be handled", e);
        } catch (IOException e) {
            LOGGER.error("I/O error is detected when the servlet handles"
                         + " the GET request", e);
        }
    }

    /**
     * Called by the server (via the <code>service</code> method)
     * to allow a servlet to handle a POST request.
     *
     * @param req  an {@link HttpServletRequest} object that
     *             contains the request the client has made
     *             of the servlet
     * @param resp an {@link HttpServletResponse} object that
     *             contains the response the servlet sends
     *             to the client
     */
    @Override
    protected void doPost(final HttpServletRequest req,
            final HttpServletResponse resp) {
        try {
            process(req, resp);
        } catch (ServletException e) {
            LOGGER.error("request for the POST could not be handled", e);
        } catch (IOException e) {
            LOGGER.error("I/O error is detected when the servlet handles"
                         + " the POST request", e);
        }
    }

    /**
     * Creates Service Factory that gets a new connection to database from
     * connection pool for every request.
     *
     * @return the service factory.
     * @throws DaoException if failed to get connection.
     */
    private ServiceFactory getFactory() throws DaoException {
        return new ServiceFactoryImpl(new TransactionFactoryImpl());
    }

    /**
     * Called by the server to allow a servlet to handle a GET or POST request.
     *
     * @param req  an {@link HttpServletRequest} object that
     *             contains the request the client has made
     *             of the servlet
     * @param resp an {@link HttpServletResponse} object that
     *             contains the response the servlet sends
     *             to the client
     * @throws IOException      if an input or output error is
     *                          detected when the servlet handles
     *                          the request
     * @throws ServletException if the request for the GET or POST
     *                          could not be handled
     */
    private void process(final HttpServletRequest req,
            final HttpServletResponse resp) throws ServletException,
            IOException {
        try {
            ActionManager actionManager = ActionManagerFactory.getManager(
                    getFactory());
            ActionCommandFactory factory = new ActionCommandFactory();
            ActionCommand command = factory.defineCommand(req);

            actionManager.execute(command, req, resp);
            actionManager.close();
        } catch (DaoException | ServiceException e) {
            LOGGER.error("It is impossible to process request", e);
        }
    }

    /**
     * Called by the servlet container to indicate to a servlet that the
     * servlet is being taken out of service.
     */
    @Override
    public void destroy() {
        ConnectionPool.getInstance().closePool();
    }
}
