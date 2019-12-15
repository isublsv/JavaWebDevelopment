package by.gartsmanovich.hitcher.view;

import by.gartsmanovich.hitcher.bean.Review;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.SkipPageException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.util.List;

/**
 * Class tag used to calculates total user rating in the application.
 *
 * @author Dmitry Gartsmanovich
 */
public class RatingTag extends SimpleTagSupport {

    /**
     * The list contains reviews.
     */
    private List<Review> reviews;

    /**
     * Sets reviewsValue.
     *
     * @param reviewsValue value of reviewsValue.
     */
    public void setList(final List<Review> reviewsValue) {
        reviews = reviewsValue;
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
            if (!reviews.isEmpty()) {
                int sum = reviews.stream().mapToInt(Review::getRating).sum();
                rating = sum / reviews.size();
            }
            getJspContext().getOut().write(String.valueOf(rating));
        } catch (Exception e) {
            String message = "Cannot execute tag body";
            throw new SkipPageException(message, e);
        }
    }
}
