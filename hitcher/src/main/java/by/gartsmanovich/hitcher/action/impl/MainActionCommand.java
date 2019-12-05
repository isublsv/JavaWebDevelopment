package by.gartsmanovich.hitcher.action.impl;

import by.gartsmanovich.hitcher.action.ActionCommand;
import by.gartsmanovich.hitcher.action.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class describes main action command that used to redirect user to the index
 * page.
 *
 * @author Dmitry Gartsmanovich
 */
public class MainActionCommand extends ActionCommand {

    /**
     * Handles the request and response and invoke appropriate method in the
     * Service Layer.
     *
     * @param request  the provided request information for HTTP servlets.
     * @param response the provided response information for HTTP servlets.
     * @throws IOException      if an input or output error is
     *                          detected when the servlet handles
     *                          the request
     */
    @Override
    public void execute(final HttpServletRequest request,
            final HttpServletResponse response) throws IOException {
        response.sendRedirect(request.getContextPath()
                + ConfigurationManager.getProperty("path.page.index"));
    }
}
