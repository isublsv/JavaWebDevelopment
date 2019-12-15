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
import java.util.List;

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
     * Contains active tab attribute value.
     */
    private static final String ACTIVE_TAB = "activeTab";

    /**
     * Contains authorized user attribute value.
     */
    private static final String AUTHORIZED_USER = "authorizedUser";

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
        User user = (User) session.getAttribute(AUTHORIZED_USER);
        String tab = (String) session.getAttribute(ACTIVE_TAB);

        try {
            UserService userService = getFactory().getUserService();
            user = userService.findByID(user.getId());
            request.setAttribute(AUTHORIZED_USER, user);

            if (tab != null) {
                request.setAttribute(ACTIVE_TAB, tab);
                session.removeAttribute(ACTIVE_TAB);
            }

            ReviewService reviewService = getFactory().getReviewService();
            List<Review> reviewAbout = reviewService.findReviewsByAboutID(
                    user.getId());
            request.setAttribute("received", reviewAbout);

            List<Review> reviewAuthors = reviewService.findReviewsByWhoID(
                    user.getId());
            request.setAttribute("left", reviewAuthors);

            String message = String.format(
                    "Full data for user \"%s\" was loaded successfully",
                    user.getLogin());
            LOGGER.debug(message);
            request.getServletContext()
                   .getRequestDispatcher(ConfigurationManager.getProperty(
                           "path.page.profile"))
                   .forward(request, response);
        } catch (ServiceException e) {
            processError(request, response, e);
        }

    }
}
