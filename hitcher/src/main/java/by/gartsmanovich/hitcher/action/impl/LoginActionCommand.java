package by.gartsmanovich.hitcher.action.impl;

import by.gartsmanovich.hitcher.action.ActionCommand;
import by.gartsmanovich.hitcher.action.manager.ConfigurationManager;
import by.gartsmanovich.hitcher.bean.User;
import by.gartsmanovich.hitcher.service.UserService;
import by.gartsmanovich.hitcher.service.exception.ServiceErrorCodes;
import by.gartsmanovich.hitcher.service.exception.ServiceException;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static by.gartsmanovich.hitcher.service.exception.ServiceErrorCodes.WRONG_LOGIN_OR_PASS;

/**
 * Class describes login action command that used to login user in the system.
 *
 * @author Dmitry Gartsmanovich
 */
public class LoginActionCommand extends ActionCommand {

    /**
     * The logger for LoginActionCommand class.
     */
    private static final Logger LOGGER = LogManager.getLogger(
            LoginActionCommand.class);

    /**
     * Handles the request parameters and passes its to the Service application
     * layer.
     *
     * @param request the provide request information for HTTP servlets.
     * @throws IOException      if an input or output error is
     *                          detected when the servlet handles
     *                          the request
     * @throws ServletException if the request for the GET or POST
     *                          could not be handled
     */
    @Override
    public void execute(final HttpServletRequest request,
            final HttpServletResponse response)
            throws ServletException, IOException {
        String login = request.getParameter("login");
        String pass = request.getParameter("pass");
        Map<String, String> messageMap = new HashMap<>();
        String json;
        try {
            if (login != null && pass != null) {
                UserService userService = getFactory().getUserService();
                User user = userService.findByLoginAndPassword(login, pass);
                HttpSession session = request.getSession();
                session.setAttribute("authorizedUser", user);
                String successMessage = String.format(
                        "User \"%s\" is logged in", user.getLogin());
                LOGGER.debug(successMessage);

                String redirect = request.getHeader("referer");
                messageMap.put("redirect", redirect);
                json = new Gson().toJson(messageMap);
            } else {
                throw new ServiceException(WRONG_LOGIN_OR_PASS);
            }
        } catch (ServiceException e) {
            ServiceErrorCodes code = e.getErrorCode();
            LOGGER.warn(code.getMessage());
            switch (code) {
                case WRONG_LOGIN_OR_PASS:
                case USER_DOES_NOT_EXIST:
                    messageMap.put(code.getCodeValue(), code.getMessage());
                    break;
                default:
                    request.setAttribute("errorMessage", e.getMessage());
                    request.getRequestDispatcher(
                            ConfigurationManager.getProperty("path.page.error"))
                           .forward(request, response);
            }
            json = new Gson().toJson(messageMap);
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}
