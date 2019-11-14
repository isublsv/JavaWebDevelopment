package by.gartsmanovich.hitcher.bean;

/**
 * Class represents the Review entity. Each review describes by user ID about,
 * user owner ID, text and rating.
 *
 * @author Dmitry Gartsmanovich
 */
public class Review extends Entity {

    /**
     * The user ID which this review about.
     */
    private long aboutId;

    /**
     * The ID of the review owner.
     */
    private long whoId;

    /**
     * The text of review.
     */
    private String text;

    /**
     * The rating value of review.
     */
    private int rating;

    /**
     * Default constructor.
     */
    public Review() {
    }

    /**
     * Constructs the review instance with ID value which user review about,
     * ID value of the review owner, text and rating value.
     *
     * @param aboutIdValue the user ID value which this review about.
     * @param whoIdValue   the user ID value of the review owner.
     * @param textValue    the text value.
     * @param ratingValue  the rating value.
     */
    public Review(final long aboutIdValue, final long whoIdValue,
            final String textValue, final int ratingValue) {
        super(aboutIdValue);
        whoId = whoIdValue;
        text = textValue;
        rating = ratingValue;
    }

    /**
     * Gets about.
     *
     * @return value of about.
     */
    public long getAboutId() {
        return aboutId;
    }

    /**
     * Sets about.
     *
     * @param aboutIdValue value of about.
     */
    public void setAboutId(final long aboutIdValue) {
        aboutId = aboutIdValue;
    }

    /**
     * Gets who.
     *
     * @return value of who.
     */
    public long getWhoId() {
        return whoId;
    }

    /**
     * Sets who.
     *
     * @param whoIdValue value of who.
     */
    public void setWhoId(final long whoIdValue) {
        whoId = whoIdValue;
    }

    /**
     * Gets text.
     *
     * @return value of text.
     */
    public String getText() {
        return text;
    }

    /**
     * Sets text.
     *
     * @param textValue value of text.
     */
    public void setText(final String textValue) {
        text = textValue;
    }

    /**
     * Gets rating.
     *
     * @return value of rating.
     */
    public int getRating() {
        return rating;
    }

    /**
     * Sets rating.
     *
     * @param ratingValue value of rating.
     */
    public void setRating(final int ratingValue) {
        rating = ratingValue;
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = PRIME * result + (int) (aboutId ^ (aboutId >>> SHIFT));
        result = PRIME * result + (int) (whoId ^ (whoId >>> SHIFT));
        result = PRIME * result + text.hashCode();
        result = PRIME * result + rating;
        return result;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param o the reference object with which to compare.
     * @return {@code true} if this object is the same as the obj
     * argument; {@code false} otherwise.
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        Review review = (Review) o;

        if (aboutId != review.aboutId) {
            return false;
        }
        if (whoId != review.whoId) {
            return false;
        }
        if (rating != review.rating) {
            return false;
        }
        return text.equals(review.text);
    }
}
