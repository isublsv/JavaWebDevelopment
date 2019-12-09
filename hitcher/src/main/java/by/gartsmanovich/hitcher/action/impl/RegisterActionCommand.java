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

/**
 * Class describes register action command that used to register user in the
 * system if it not exist and save user entity in the session.
 *
 * @author Dmitry Gartsmanovich
 */
public class RegisterActionCommand extends ActionCommand {

    /**
     * The logger for RegisterActionCommand class.
     */
    private static final Logger LOGGER = LogManager.getLogger(
            RegisterActionCommand.class);

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
        String login = request.getParameter("login");
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");

        Map<String, String> messageMap = new HashMap<>();
        String json;
        try {
            UserService userService = getFactory().getUserService();
            User user = userService.save(login, email, pass);
            HttpSession session = request.getSession();
            session.setAttribute("authorizedUser", user);
            String successMessage = String.format(
                    "User \"%s\" is successfully registered", user.getLogin());
            LOGGER.debug(successMessage);

            String redirect = request.getHeader("referer");
            messageMap.put("redirect", redirect);
            json = new Gson().toJson(messageMap);
        } catch (ServiceException e) {
            ServiceErrorCodes code = e.getErrorCode();
            LOGGER.error(code.getMessage());
            switch (code) {
                case INVALID_LOGIN:
                case INVALID_EMAIL:
                case USER_EXISTS:
                case EMAIL_EXISTS:
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
