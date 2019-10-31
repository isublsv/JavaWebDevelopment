package by.gartsmanovich.webparsing.controller.command;

import javax.servlet.http.HttpServletRequest;

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
     */
    String execute(HttpServletRequest request);
}
