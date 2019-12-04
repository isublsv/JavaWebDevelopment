package by.gartsmanovich.hitcher.controller.filter;

import by.gartsmanovich.hitcher.action.ActionCommand;
import by.gartsmanovich.hitcher.action.impl.BanActionCommand;
import by.gartsmanovich.hitcher.action.impl.EditDriverInfoActionCommand;
import by.gartsmanovich.hitcher.action.impl.EditEmailActionCommand;
import by.gartsmanovich.hitcher.action.impl.EditPasswordActionCommand;
import by.gartsmanovich.hitcher.action.impl.EditPersonalDataActionCommand;
import by.gartsmanovich.hitcher.action.impl.EditPreferencesActionCommand;
import by.gartsmanovich.hitcher.action.impl.LoadTripInfoActionCommand;
import by.gartsmanovich.hitcher.action.impl.LoginActionCommand;
import by.gartsmanovich.hitcher.action.impl.LogoutActionCommand;
import by.gartsmanovich.hitcher.action.impl.MainActionCommand;
import by.gartsmanovich.hitcher.action.impl.ProfileActionCommand;
import by.gartsmanovich.hitcher.action.impl.RegisterActionCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Filter used to find Action Command in the request URI and set it to the
 * request attribute.
 *
 * @author Dmitry Gartsmanovich
 */
public class ActionFilter implements Filter {

    /**
     * The logger for ActionFilter class.
     */
    private static final Logger LOGGER = LogManager.getLogger(
            ActionFilter.class);

    /**
     * Contains a full action list that exist int the application.
     */
    private static Map<String, ActionCommand> actions =
            new ConcurrentHashMap<>();

    /**
     * Called by the web container to indicate to a filter that it is being
     * placed into service.
     *
     * @param config a <code>FilterConfig</code> object containing the
     *               filter's configuration and initialization parameters
     */
    @Override
    public void init(final FilterConfig config) {
        //unauthorized user actions
        actions.put("/", new MainActionCommand());
        actions.put("/index", new MainActionCommand());
        actions.put("/login", new LoginActionCommand());
        actions.put("/register", new RegisterActionCommand());
        actions.put("/trip/load", new LoadTripInfoActionCommand());
        //user actions
        actions.put("/logout", new LogoutActionCommand());
        actions.put("/profile", new ProfileActionCommand());
        actions.put("/profile/edit/personal_data",
                    new EditPersonalDataActionCommand());
        actions.put("/profile/edit/preferences",
                    new EditPreferencesActionCommand());
        actions.put("/profile/edit/email", new EditEmailActionCommand());
        actions.put("/profile/edit/password", new EditPasswordActionCommand());
        actions.put("/profile/edit/driver_info",
                    new EditDriverInfoActionCommand());
        //admin actions
        actions.put("/ban", new BanActionCommand());
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

        if (request instanceof HttpServletRequest) {
            HttpServletRequest req = (HttpServletRequest) request;
            String contextPath = req.getContextPath();
            String uri = req.getRequestURI();

            int beginAction = contextPath.length();
            int endAction = uri.lastIndexOf('.');
            String actionName;

            if (endAction >= 0) {
                actionName = uri.substring(beginAction, endAction);
            } else {
                actionName = uri.substring(beginAction);
            }

            if (actions.get(actionName) != null) {
                req.setAttribute("command", actions.get(actionName));
                chain.doFilter(request, response);
            } else {
                LOGGER.error(
                        "It is impossible to create action handler object");
                String message = String.format(
                        "Requested address %s cant be handled by server", uri);
                req.setAttribute("errorMessage", message);
                req.getServletContext()
                   .getRequestDispatcher("path.page.error")
                   .forward(request, response);
            }
        } else {
            LOGGER.error("It is impossible to use HTTP filter");
            request.getServletContext()
                   .getRequestDispatcher("path.page.error")
                   .forward(request, response);
        }
    }
}
