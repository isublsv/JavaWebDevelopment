package by.gartsmanovich.hitcher.controller.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.regex.Pattern;

/**
 * Class-wrapper used to override the
 * {@link HttpServletRequestWrapper#getParameterValues(String)},
 * {@link HttpServletRequestWrapper#getParameter(String)}
 * and {@link HttpServletRequestWrapper#getHeader(String)} methods to execute
 * the filtering before returning the desired field to the caller.
 *
 * @author Dmitry Gartsmanovich
 */
public class XSSRequestWrapper extends HttpServletRequestWrapper {

    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request the {@link HttpServletRequest} to be wrapped.
     * @throws IllegalArgumentException if the request is null
     */
    public XSSRequestWrapper(final HttpServletRequest request) {
        super(request);
    }

    /**
     * Strips XSS attacks from parameter values.
     *
     * @param parameter the request parameter.
     * @return the encoded parameter values.
     */
    @Override
    public String[] getParameterValues(final String parameter) {
        String[] values = super.getParameterValues(parameter);

        if (values == null) {
            return new String[0];
        }

        int count = values.length;
        String[] encodedValues = new String[count];
        for (int i = 0; i < count; i++) {
            encodedValues[i] = stripXSS(values[i]);
        }

        return encodedValues;
    }

    /**
     * Strips XSS attacks from parameters.
     *
     * @param parameter the request parameter.
     * @return striped parameter value.
     */
    @Override
    public String getParameter(final String parameter) {
        String value = super.getParameter(parameter);

        return stripXSS(value);
    }

    /**
     * Strips XSS attacks from request header.
     *
     * @param name the request header name.
     * @return striped header value.
     */
    @Override
    public String getHeader(final String name) {
        String value = super.getHeader(name);
        return stripXSS(value);
    }

    /**
     * Checks provided string and removes XSS attacks from it.
     *
     * @param value the provided value.
     * @return the striped value.
     */
    private String stripXSS(String value) {
        if (value != null) {
            // Avoid encoded attacks.
            //value = ESAPI.encoder().canonicalize(value);

            // Avoid null characters
            value = value.replaceAll("\0", "");

            // Avoid anything between script tags
            Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>",
                                                    Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid anything in a src='...' type of expression
            scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*'(.*?)'",
                             Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
                             | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\"(.*?)\"",
                                            Pattern.CASE_INSENSITIVE
                                            | Pattern.MULTILINE
                                            | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            // Remove any lonesome </script> tag
            scriptPattern = Pattern.compile("</script>",
                                            Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");

            // Remove any lonesome <script ...> tag
            scriptPattern = Pattern.compile("<script(.*?)>",
                                            Pattern.CASE_INSENSITIVE
                                            | Pattern.MULTILINE
                                            | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid eval(...) expressions
            scriptPattern = Pattern.compile("eval\\((.*?)\\)",
                                            Pattern.CASE_INSENSITIVE
                                            | Pattern.MULTILINE
                                            | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid expression(...) expressions
            scriptPattern = Pattern.compile("expression\\((.*?)\\)",
                                            Pattern.CASE_INSENSITIVE
                                            | Pattern.MULTILINE
                                            | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid javascript:... expressions
            scriptPattern = Pattern.compile("javascript:",
                                            Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid vbscript:... expressions
            scriptPattern = Pattern.compile("vbscript:",
                                            Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid onload= expressions
            scriptPattern = Pattern.compile("onload(.*?)=",
                                            Pattern.CASE_INSENSITIVE
                                            | Pattern.MULTILINE
                                            | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");
        }
        return value;
    }
}
