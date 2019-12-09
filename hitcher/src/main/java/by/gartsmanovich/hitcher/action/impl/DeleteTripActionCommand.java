package by.gartsmanovich.hitcher.action.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class describes delete trip action command that used to delete trip for
 * current user.
 *
 * @author Dmitry Gartsmanovich
 */
public class DeleteTripActionCommand extends AuthorizedActionCommand {

    /**
     * The logger for DeleteTripActionCommand class.
     */
    private static final Logger LOGGER = LogManager.getLogger(
            DeleteTripActionCommand.class);

    /**
     * Handles the request and response and invoke appropriate method in the
     * Service Layer.
     *
     * @param request  the provided request information for HTTP servlets.
     * @param response the provided response information for HTTP servlets.
     * @throws IOException      if an input or output error is
     *                          detected when the servlet handles
     *                          the request
     * @throws ServletException if the request for the GET or POST
     *                          could not be handled
     */
    @Override
    public void execute(final HttpServletRequest request,
            final HttpServletResponse response) throws IOException,
            ServletException {

    }
}