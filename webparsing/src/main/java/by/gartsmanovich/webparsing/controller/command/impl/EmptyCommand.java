package by.gartsmanovich.webparsing.controller.command.impl;

import by.gartsmanovich.webparsing.controller.command.Command;
import by.gartsmanovich.webparsing.controller.command.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

/**
 * Class describes Empty command that proceed user request and redirect user to
 * the index page if the any error occurs.
 *
 * @author Dmitry Gartsmanovich
 */
public class EmptyCommand implements Command {

    /**
     * Handles the request parameters and passes its to the Service application
     * layer.
     *
     * @param request the provide request information for HTTP servlets.
     * @return the result string of correct or incorrect execution of the
     * command.
     */
    @Override
    public String execute(final HttpServletRequest request) {
        return ConfigurationManager.getProperty("path.page.index");
    }
}
