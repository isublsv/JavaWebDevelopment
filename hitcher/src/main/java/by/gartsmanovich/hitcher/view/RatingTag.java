package by.gartsmanovich.hitcher.view;

import by.gartsmanovich.hitcher.bean.Review;
import by.gartsmanovich.hitcher.bean.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.SkipPageException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.util.Map;

/**
 * Class tag used to calculates total user rating in the application.
 *
 * @author Dmitry Gartsmanovich
 */
public class RatingTag extends SimpleTagSupport {

    /**
     * The logger for RatingTag class.
     */
    private static final Logger LOGGER = LogManager.getLogger(RatingTag.class);

    /**
     * The map contains review and users.
     */
    private Map<Review, User> reviewUserMap;

    /**
     * Sets reviewUserMap.
     *
     * @param reviewUserMapValue value of reviewUserMap.
     */
    public void setMap(final Map<Review, User> reviewUserMapValue) {
        reviewUserMap = reviewUserMapValue;
    }

    /**
     * Calculates the total rating of the user.
     *
     * @throws JspException      Subclasses can throw JspException to indicate
     *                           an error occurred while processing this tag.
     * @throws SkipPageException If the page that (either directly or
     *                           indirectly) invoked this tag is to
     *                           cease evaluation.
     */
    @Override
    public void doTag() throws JspException {
        try {
            int rating = 0;
            if (!reviewUserMap.isEmpty()) {
                int sum = reviewUserMap.keySet().stream().mapToInt(
                        Review::getRating).sum();
                rating = sum / reviewUserMap.size();
            }
            getJspContext().getOut().write(rating);
        } catch (Exception e) {
            String message = "Cannot execute tag body";
            LOGGER.error(message, e);
            throw new SkipPageException(message, e);
        }
    }
}
