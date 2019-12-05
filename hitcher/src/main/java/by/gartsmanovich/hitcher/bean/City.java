package by.gartsmanovich.hitcher.bean;

import java.util.Objects;

/**
 * Class represents City entity. Each destination describes by ID,
 * country ID and city name.
 *
 * @author Dmitry Gartsmanovich
 */
public class City extends Entity {

    /**
     * The country ID.
     */
    private long countryID;

    /**
     * The city name.
     */
    private String cityName;

    /**
     * Default constructor.
     */
    public City() {
    }

    /**
     * Constructs the entity instance with ID value.
     *
     * @param idValue the ID value.
     */
    public City(final long idValue) {
        super(idValue);
    }

    /**
     * Gets countryID.
     *
     * @return value of countryID.
     */
    public long getCountryID() {
        return countryID;
    }

    /**
     * Sets countryID.
     *
     * @param countryIDValue value of countryID.
     */
    public void setCountryID(final long countryIDValue) {
        countryID = countryIDValue;
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
        result = PRIME * result + (int) (countryID ^ (countryID >>> SHIFT));
        if (cityName != null) {
            result = PRIME * result + cityName.hashCode();
        } else {
            result = PRIME * result;
        }
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

        City city = (City) o;

        if (countryID != city.countryID) {
            return false;
        }
        return Objects.equals(cityName, city.cityName);
    }
}
