package by.gartsmanovich.hitcher.action.impl;

import by.gartsmanovich.hitcher.action.ActionCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class describes Empty command that proceed user request and redirect user to
 * the index page if the any error occurs.
 *
 * @author Dmitry Gartsmanovich
 */
public class EmptyActionCommand extends ActionCommand {

    /**
     * Handles the request parameters and passes its to the Service application
     * layer.
     *
     * @param request the provide request information for HTTP servlets.
     */
    @Override
    public void execute(final HttpServletRequest request,
            final HttpServletResponse response) {
    }
}
