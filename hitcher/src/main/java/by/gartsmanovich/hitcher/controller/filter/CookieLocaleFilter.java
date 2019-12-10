package by.gartsmanovich.hitcher.controller.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Filter used to save "locale" parameter value to the cookie.
 *
 * @author Dmitry Gartsmanovich
 */
public class CookieLocaleFilter implements Filter {

    /**
     * Sets the maximum age in seconds for this Cookie.
     */
    private static final int MAX_AGE = 30 * 60 * 60;


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

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;


        String locale = req.getParameter("locale");
        if (locale != null) {
            Cookie[] cookies = req.getCookies();
            boolean isLangFound = false;
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    String name = cookie.getName();
                    if ("lang".equals(name)) {
                        cookie.setValue(locale);
                        resp.addCookie(cookie);
                        isLangFound = true;
                    }
                }
            }
            if (!isLangFound) {
                Cookie cookie = new Cookie("lang", locale);
                cookie.setMaxAge(MAX_AGE);
                cookie.setHttpOnly(true);
                resp.addCookie(cookie);
            }
        }

        chain.doFilter(request, response);
    }
}
