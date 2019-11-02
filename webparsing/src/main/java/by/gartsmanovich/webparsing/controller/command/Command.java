package by.gartsmanovich.webparsing.controller.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Interface for Commands.
 *
 * @author Dmitry Gartsmanovich
 */
public interface Command {

    /**
     * Handles the request parameters and passes its to the Service application
     * layer.
     *
     * @param request the provide request information for HTTP servlets.
     * @return the result string of correct or incorrect execution of the
     * command.
     * @throws IOException      if an input or output error is
     *                          detected when the servlet handles
     *                          the request
     * @throws ServletException if the request for the GET or POST
     *                          could not be handled
     */
    String execute(HttpServletRequest request) throws IOException,
            ServletException;
}
