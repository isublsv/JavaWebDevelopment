package by.gartsmanovich.hitcher.bean;

import java.util.List;

/**
 * Class represents Destination entity. Each destination describes by
 * country ID, country name and city list.
 *
 * @author Dmitry Gartsmanovich
 */
public class Destination extends Entity {

    /**
     * The country name.
     */
    private String countryName;

    /**
     * The city list.
     */
    private List<City> cities;

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
     * Sets cities.
     *
     * @param citiesValue value of cities.
     */
    public void setCities(final List<City> citiesValue) {
        cities = citiesValue;
    }

    /**
     * Gets cityName.
     *
     * @return value of cityName.
     */
    public List<City> getCities() {
        return cities;
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = PRIME * result + countryName.hashCode();
        result = PRIME * result + cities.hashCode();
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

        if (!countryName.equals(that.countryName)) {
            return false;
        }
        return cities.equals(that.cities);
    }
}
