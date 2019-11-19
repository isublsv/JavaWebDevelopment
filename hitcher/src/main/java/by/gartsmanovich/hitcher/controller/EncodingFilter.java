package by.gartsmanovich.hitcher.controller;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

/**
 * Used to set default encoding to UTF-8.
 *
 * @author Dmitry Gartsmanovich
 */
@WebFilter(filterName = "EncodingFilter", urlPatterns = "/*",
        initParams = {@WebInitParam(name = "defaultEncoding", value = "UTF-8")})
public class EncodingFilter implements Filter {

    /**
     * Represents the default encoding value.
     */
    private String defaultEncoding;

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
        defaultEncoding = config.getInitParameter("defaultEncoding");
    }

    /**
     * The <code>doFilter</code> method of the Filter is called by the
     * container each time a request/response pair is passed through the
     * chain due to a client request for a resource at the end of the chain.
     *
     * @param req   the <code>ServletRequest</code> object contains the
     *              client's request
     * @param resp  the <code>ServletResponse</code> object contains the
     *              filter's response
     * @param chain the <code>FilterChain</code> for invoking the next
     *              filter or the resource
     * @throws IOException      if an I/O related error has occurred during
     *                          the processing
     * @throws ServletException if an exception occurs that interferes with the
     *                          filter's normal operation
     */
    @Override
    public void doFilter(final ServletRequest req, final ServletResponse resp,
            final FilterChain chain) throws ServletException, IOException {
        req.setCharacterEncoding(defaultEncoding);
        chain.doFilter(req, resp);
    }

    /**
     * Called by the web container to indicate to a filter that it is being
     * taken out of service.
     */
    @Override
    public void destroy() {
        defaultEncoding = null;
    }

}
