package by.gartsmanovich.hitcher.controller.filter;

import by.gartsmanovich.hitcher.action.ActionCommand;
import by.gartsmanovich.hitcher.action.impl.LoginActionCommand;
import by.gartsmanovich.hitcher.action.impl.LogoutActionCommand;
import by.gartsmanovich.hitcher.action.impl.MainActionCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 */
@WebFilter(filterName = "ActionFilter", urlPatterns = "*.go")
public class ActionFilter implements Filter {

    /**
     * The logger for ActionFilter class.
     */
    private static final Logger LOGGER = LogManager.getLogger(
            ActionFilter.class);

    private static Map<String, ActionCommand> actions = new ConcurrentHashMap<>();

    static {
        actions.put("/", new MainActionCommand());
        actions.put("/index", new MainActionCommand());
        actions.put("/login", new LoginActionCommand());
        actions.put("/logout", new LogoutActionCommand());

        /*actions.put("/profile/edit", ProfileEditAction.class);
        actions.put("/profile/save", ProfileSaveAction.class);

        actions.put("/reader/list", ReaderListAction.class);
        actions.put("/reader/edit", ReaderEditAction.class);
        actions.put("/reader/save", ReaderSaveAction.class);
        actions.put("/reader/delete", ReaderDeleteAction.class);

        actions.put("/user/list", UserListAction.class);
        actions.put("/user/edit", UserEditAction.class);
        actions.put("/user/save", UserSaveAction.class);
        actions.put("/user/delete", UserDeleteAction.class);

        actions.put("/author/list", AuthorListAction.class);
        actions.put("/author/edit", AuthorEditAction.class);
        actions.put("/author/save", AuthorSaveAction.class);
        actions.put("/author/delete", AuthorDeleteAction.class);

        actions.put("/author/book/list", BookListAction.class);
        actions.put("/author/book/edit", BookEditAction.class);
        actions.put("/author/book/save", BookSaveAction.class);
        actions.put("/author/book/delete", BookDeleteAction.class);

        actions.put("/search/book/form", SearchBookFormAction.class);
        actions.put("/search/book/result", SearchBookResultAction.class);
        actions.put("/author/book/usages", BookUsageListAction.class);

        actions.put("/search/reader/form", SearchReaderFormAction.class);
        actions.put("/search/reader/result", SearchReaderResultAction.class);
        actions.put("/reader/usages", ReaderUsageListAction.class);

        actions.put("/author/book/deliver", DeliverBookAction.class);
        actions.put("/author/book/return", ReturnBookAction.class);*/
    }

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

            ActionCommand actionCommand;
            try {
                actionCommand = actions.get(actionName);
                req.setAttribute("command", actionCommand);
                chain.doFilter(request, response);
            } catch (IllegalArgumentException | NullPointerException e) {
                LOGGER.error(
                        "It is impossible to create action handler object", e);
                req.setAttribute("errorMessage", String.format(
                        "Запрошенный адрес %s не может быть"
                        + " обработан сервером", uri));
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

    /**
     * Called by the web container to indicate to a filter that it is being
     * taken out of service.
     */
    @Override
    public void destroy() {
    }

}
