package by.gartsmanovich.hitcher.controller.action.impl;

import by.gartsmanovich.hitcher.controller.action.ActionCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class describes Builder command that proceed user request and
 * invoke appropriate method from Service layer of the application. The result
 * depends on input parameters.
 *
 * @author Dmitry Gartsmanovich
 */
public class LoginActionCommand extends ActionCommand {

    /**
     * The logger for BuilderCommand class.
     */
    private static final Logger LOGGER = LogManager.getLogger(
            LoginActionCommand.class);


    /**
     * Handles the request parameters and passes its to the Service application
     * layer.
     *
     * @param request the provide request information for HTTP servlets.
     * @throws IOException      if an input or output error is
     *                          detected when the servlet handles
     *                          the request
     * @throws ServletException if the request for the GET or POST
     *                          could not be handled
     */
    @Override
    public void execute(final HttpServletRequest request,
            final HttpServletResponse response) {
    }
}
