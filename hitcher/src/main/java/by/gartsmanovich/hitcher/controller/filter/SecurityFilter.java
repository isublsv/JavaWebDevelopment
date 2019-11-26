package by.gartsmanovich.hitcher.controller.filter;

import by.gartsmanovich.hitcher.action.ActionCommand;
import by.gartsmanovich.hitcher.action.impl.MainActionCommand;
import by.gartsmanovich.hitcher.action.manager.ConfigurationManager;
import by.gartsmanovich.hitcher.bean.Role;
import by.gartsmanovich.hitcher.bean.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
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
@WebFilter(filterName = "SecurityFilter", urlPatterns = "*.do")
public class SecurityFilter implements Filter {

    /**
     * The logger for SecurityFilter class.
     */
    private static final Logger LOGGER = LogManager.getLogger(
            SecurityFilter.class);

    /**
     * Describes security message for attribute.
     */
    private static final String SECURITY_FILTER_MESSAGE =
            "SecurityFilterMessage";

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
            User user = null;
            if (session != null) {
                user = (User) session.getAttribute("authorizedUser");

                String errorMessage = (String) session.getAttribute(
                        SECURITY_FILTER_MESSAGE);
                if (errorMessage != null) {
                    req.setAttribute("message", errorMessage);
                    session.removeAttribute(SECURITY_FILTER_MESSAGE);
                }
            }
            boolean canExecute = allowRoles.isEmpty();

            if (user != null) {
                userName = String.format("\"%s\" user", user.getLogin());
                canExecute = canExecute || allowRoles.contains(user.getRole());
            }

            if (canExecute) {
                chain.doFilter(request, response);
            } else {
                String message = String.format(
                        "%s is trying to access to forbidden"
                        + " resource", userName);
                LOGGER.info(message);
                if (session != null && command.getClass()
                                       != MainActionCommand.class) {
                    session.setAttribute(SECURITY_FILTER_MESSAGE,
                                         "Access denied");
                }
                resp.sendRedirect(req.getContextPath()
                                  + ConfigurationManager
                                          .getProperty("path.page.index"));
            }
        } else {
            LOGGER.error("It is impossible to use HTTP filter");
            request.getServletContext()
                   .getRequestDispatcher(
                           ConfigurationManager.getProperty("path.page.error"))
                   .forward(request, response);
        }
    }
}
