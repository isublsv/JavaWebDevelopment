package by.gartsmanovich.webparsing.controller;

import by.gartsmanovich.webparsing.controller.command.Command;
import by.gartsmanovich.webparsing.controller.command.CommandFactory;
import by.gartsmanovich.webparsing.controller.command.manager.ConfigurationManager;
import by.gartsmanovich.webparsing.controller.command.manager.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
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
    private static final Logger LOGGER = LogManager.getLogger(
            Controller.class);

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
            processRequest(req, resp);
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
            processRequest(req, resp);
        } catch (ServletException e) {
            LOGGER.error("request for the POST could not be handled", e);
        } catch (IOException e) {
            LOGGER.error("I/O error is detected when the servlet handles"
                         + " the POST request", e);
        }
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
    private void processRequest(final HttpServletRequest req,
            final HttpServletResponse resp) throws ServletException,
            IOException {
        String page;

        String path = getServletContext().getContextPath() + "/WEB-INF/classes/data/Medicines.xml";
        LOGGER.error(getServletContext().getContextPath() + "/WEB-INF/classes/data/");

        CommandFactory factory = new CommandFactory();
        Command command = factory.defineCommand(req);

        page = command.execute(req);

        if (page != null) {
            RequestDispatcher dispatcher =
                    getServletContext().getRequestDispatcher(
                    page);
            dispatcher.forward(req, resp);
        } else {
            page = ConfigurationManager.getProperty("path.page.index");
            req.getSession().setAttribute("nullPage",
                                          MessageManager.getProperty(
                                                  "message.null.page"));
            resp.sendRedirect(req.getContextPath() + page);
        }
    }
}
