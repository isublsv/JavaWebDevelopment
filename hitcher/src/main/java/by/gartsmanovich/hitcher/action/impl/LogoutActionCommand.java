package by.gartsmanovich.hitcher.action.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class describes logout action command that used to invalidate
 * {@link javax.servlet.http.HttpSession} and redirect user to the index page.
 *
 * @author Dmitry Gartsmanovich
 */
public class LogoutActionCommand extends AuthorizedActionCommand {

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
        request.getSession(false).invalidate();
        String redirect = request.getHeader("referer");
        response.sendRedirect(redirect);
    }
}
