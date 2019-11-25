package by.gartsmanovich.hitcher.controller.filter;

import by.gartsmanovich.hitcher.action.ActionCommand;
import by.gartsmanovich.hitcher.action.manager.ConfigurationManager;
import by.gartsmanovich.hitcher.bean.Role;
import by.gartsmanovich.hitcher.bean.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

/**
 * Used to prohibit unauthorized access for users that are not allowed.
 *
 * @author Dmitry Gartsmanovich
 */
@WebFilter(filterName = "SecurityFilter", urlPatterns = "*.go")
public class SecurityFilter implements Filter {

    /**
     * The logger for SecurityFilter class.
     */
    private static final Logger LOGGER = LogManager.getLogger(
            SecurityFilter.class);

    /**
     * Called by the web container to indicate to a filter that it is being
     * placed into service.
     *
     * @param config a <code>FilterConfig</code> object containing the
     *               filter's configuration and initialization parameters
     * @throws ServletException if an exception has occurred that interferes
     *                          with the filter's normal operation
     */
    @Override
    public void init(final FilterConfig config) throws ServletException {
    }

    /**
     * The <code>doFilter</code> method of the Filter is called by the
     * container each time a request/response pair is passed through the
     * chain due to a client request for a resource at the end of the chain.
     *
     * @param request  the <code>ServletRequest</code> object contains the
     *                 client's request
     * @param response the <code>ServletResponse</code> object contains the
     *                 filter's response
     * @param chain    the <code>FilterChain</code> for invoking the next
     *                 filter or the resource
     * @throws IOException      if an I/O related error has occurred during
     *                          the processing
     * @throws ServletException if an exception occurs that interferes with the
     *                          filter's normal operation
     */
    @Override
    public void doFilter(final ServletRequest request,
            final ServletResponse response, final FilterChain chain) throws
            ServletException, IOException {

        if (request instanceof HttpServletRequest
            && response instanceof HttpServletResponse) {

            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse resp = (HttpServletResponse) response;

            ActionCommand command = (ActionCommand) req.getAttribute(
                    "command");

            String userName = "unauthorized user";

            HttpSession session = req.getSession(false);

            Set<Role> allowRoles = command.getAllowRoles();

            User user = (User) session.getAttribute("authorizedUser");
            command.setAuthorizedUser(user);
            String errorMessage = (String) session.getAttribute(
                    "SecurityFilterMessage");
            if (errorMessage != null) {
                req.setAttribute("message", errorMessage);
                session.removeAttribute("SecurityFilterMessage");
            }

            boolean canExecute = allowRoles == null;

            if (user != null) {
                userName = "\"" + user.getLogin() + "\" user";
                canExecute = canExecute || allowRoles.contains(user.getRole());
            }

            if (canExecute) {
                chain.doFilter(request, response);
            } else {
                String message = String.format("%s is trying to access to " +
                        "forbidden resource", userName);
                LOGGER.info(message);
                resp.sendRedirect(req.getContextPath() + ConfigurationManager.getProperty("path.page.index"));
            }
        } else {
            LOGGER.error("It is impossible to use HTTP filter");
            request.getServletContext()
                   .getRequestDispatcher(
                           ConfigurationManager.getProperty("path.page.error"))
                   .forward(request, response);
        }
    }

    /**
     * Called by the web container to indicate to a filter that it is being
     * taken out of service.
     */
    @Override
    public void destroy() {
    }
}
