package by.gartsmanovich.hitcher.action.impl;

import by.gartsmanovich.hitcher.action.manager.ConfigurationManager;
import by.gartsmanovich.hitcher.bean.Review;
import by.gartsmanovich.hitcher.bean.User;
import by.gartsmanovich.hitcher.service.ReviewService;
import by.gartsmanovich.hitcher.service.UserService;
import by.gartsmanovich.hitcher.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * Class describes profile action command that used to display full information
 * about user.
 *
 * @author Dmitry Gartsmanovich
 */
public class ProfileActionCommand extends AuthorizedActionCommand {

    /**
     * The logger for ProfileActionCommand class.
     */
    private static final Logger LOGGER = LogManager.getLogger(
            ProfileActionCommand.class);

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
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("authorizedUser");

        try {
            UserService userService = getFactory().getUserService();
            user = userService.findByID(user.getId());
            request.setAttribute("authorizedUser", user);

            ReviewService reviewService = getFactory().getReviewService();
            Map<Review, User> aboutIdMap = reviewService.findReviewsByAboutID(
                    user.getId());
            request.setAttribute("received", aboutIdMap);

            Map<Review, User> byWhoID = reviewService.findReviewsByWhoID(
                    user.getId());
            request.setAttribute("left", byWhoID);

            String message = String.format(
                    "Full data %s was loaded successfully", user.getLogin());
            LOGGER.debug(message);
            request.getServletContext()
                   .getRequestDispatcher(ConfigurationManager.getProperty(
                           "path.page.profile"))
                   .forward(request, response);
        } catch (ServiceException e) {
            String message = e.getMessage();
            LOGGER.warn(message);
            request.setAttribute("errorMessage", message);
            request.getRequestDispatcher(
                    ConfigurationManager.getProperty("path.page.error"))
                   .forward(request, response);
        }

    }
}
