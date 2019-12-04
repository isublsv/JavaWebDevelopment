package by.gartsmanovich.hitcher.bean;

/**
 * Class represents Destination entity. Each destination describes by ID,
 * county ID, city ID, county and city names.
 *
 * @author Dmitry Gartsmanovich
 */
public class Destination extends Entity {

    /**
     * The country ID.
     */
    private long countryId;

    /**
     * The country name.
     */
    private String countryName;

    /**
     * The city name.
     */
    private String cityName;

    /**
     * Default constructor.
     */
    public Destination() {
    }

    /**
     * Constructs the entity instance with ID value.
     *
     * @param idValue the ID value.
     */
    public Destination(final long idValue) {
        super(idValue);
    }

    /**
     * Gets countryId.
     *
     * @return value of countryId.
     */
    public long getCountryId() {
        return countryId;
    }

    /**
     * Sets countryId.
     *
     * @param countryIdValue value of countryId.
     */
    public void setCountryId(final long countryIdValue) {
        countryId = countryIdValue;
    }

    /**
     * Gets countryName.
     *
     * @return value of countryName.
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * Sets countryName.
     *
     * @param countryNameValue value of countryName.
     */
    public void setCountryName(final String countryNameValue) {
        countryName = countryNameValue;
    }

    /**
     * Gets cityName.
     *
     * @return value of cityName.
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * Sets cityName.
     *
     * @param cityNameValue value of cityName.
     */
    public void setCityName(final String cityNameValue) {
        cityName = cityNameValue;
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = PRIME * result + (int) (countryId ^ (countryId >>> SHIFT));
        result = PRIME * result + countryName.hashCode();
        result = PRIME * result + cityName.hashCode();
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

        Destination that = (Destination) o;

        if (countryId != that.countryId) {
            return false;
        }
        if (!countryName.equals(that.countryName)) {
            return false;
        }
        return cityName.equals(that.cityName);
    }
}
