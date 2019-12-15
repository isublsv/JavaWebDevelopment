package by.gartsmanovich.hitcher.bean;

/**
 * Class represents the Review entity. Each review describes by user about,
 * user author, text and rating.
 *
 * @author Dmitry Gartsmanovich
 */
public class Review extends Entity {

    /**
     * The user which this review about.
     */
    private User about;

    /**
     * The review author.
     */
    private User who;

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
     * Constructs the review instance with ID value, user is review about,
     * the review author, text and rating value.
     *
     * @param idValue     the ID value.
     * @param aboutValue  the user is this review about.
     * @param whoValue    the review author.
     * @param textValue   the text value.
     * @param ratingValue the rating value.
     */
    public Review(final long idValue, final User aboutValue,
            final User whoValue, final String textValue,
            final int ratingValue) {
        super(idValue);
        about = whoValue;
        who = aboutValue;
        text = textValue;
        rating = ratingValue;
    }

    /**
     * Gets about.
     *
     * @return value of about.
     */
    public User getAbout() {
        return about;
    }

    /**
     * Sets about.
     *
     * @param aboutValue value of about.
     */
    public void setAbout(final User aboutValue) {
        about = aboutValue;
    }

    /**
     * Gets who.
     *
     * @return value of who.
     */
    public User getWho() {
        return who;
    }

    /**
     * Sets who.
     *
     * @param whoValue value of who.
     */
    public void setWho(final User whoValue) {
        who = whoValue;
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
        result = PRIME * result + about.hashCode();
        result = PRIME * result + who.hashCode();
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

        if (about != review.about) {
            return false;
        }
        if (who != review.who) {
            return false;
        }
        if (rating != review.rating) {
            return false;
        }
        return text.equals(review.text);
    }
}
