package by.gartsmanovich.hitcher.bean;

import java.time.LocalDate;
import java.util.List;

/**
 * Class represents Trip entity. Each trip describes by ID, driver's ID,
 * destination and departure places and datetime, number of free seat, price
 * and specific trip options.
 *
 * @author Dmitry Gartsmanovich
 */
public class Trip extends Entity {

    /**
     * The user driver ID.
     */
    private User driver;

    /**
     * The place of departure value.
     */
    private City from;

    /**
     * The place of destination value.
     */
    private City to;

    /**
     * The departure datetime value.
     */
    private LocalDate departureDatetime;

    /**
     * The arrival datetime value.
     */
    private LocalDate arrivalDatetime;

    /**
     * The number of free seats.
     */
    private int freeSeats;

    /**
     * The trip price per one passenger.
     */
    private double price;

    /**
     * The smoking permission. Default is false.
     */
    private boolean isSmokingAllowed;

    /**
     * The pets permission. Default is false.
     */
    private boolean isPetsAllowed;

    /**
     * The list of passengers.
     */
    private List<User> passengers;

    /**
     * Default constructor.
     */
    public Trip() {
    }

    /**
     * Constructs the trip instance with ID value.
     *
     * @param idValue the ID value.
     */
    public Trip(final long idValue) {
        super(idValue);
    }

    /**
     * Gets driver.
     *
     * @return value of driver.
     */
    public User getDriver() {
        return driver;
    }

    /**
     * Sets driver.
     *
     * @param driverValue value of driver.
     */
    public void setDriver(final User driverValue) {
        driver = driverValue;
    }

    /**
     * Gets form.
     *
     * @return value of form.
     */
    public City getFrom() {
        return from;
    }

    /**
     * Sets form.
     *
     * @param fromValue value of form.
     */
    public void setFrom(final City fromValue) {
        from = fromValue;
    }

    /**
     * Gets to.
     *
     * @return value of to.
     */
    public City getTo() {
        return to;
    }

    /**
     * Sets to.
     *
     * @param toValue value of to.
     */
    public void setTo(final City toValue) {
        to = toValue;
    }

    /**
     * Gets departureDatetime.
     *
     * @return value of departureDatetime.
     */
    public LocalDate getDepartureDatetime() {
        return departureDatetime;
    }

    /**
     * Sets departureDatetime.
     *
     * @param departureDatetimeValue value of departureDatetime.
     */
    public void setDepartureDatetime(final LocalDate departureDatetimeValue) {
        departureDatetime = departureDatetimeValue;
    }

    /**
     * Gets arrivalDatetime.
     *
     * @return value of arrivalDatetime.
     */
    public LocalDate getArrivalDatetime() {
        return arrivalDatetime;
    }

    /**
     * Sets arrivalDatetime.
     *
     * @param arrivalDatetimeValue value of arrivalDatetime.
     */
    public void setArrivalDatetime(final LocalDate arrivalDatetimeValue) {
        arrivalDatetime = arrivalDatetimeValue;
    }

    /**
     * Gets freeSeats.
     *
     * @return value of freeSeats.
     */
    public int getFreeSeats() {
        return freeSeats;
    }

    /**
     * Sets freeSeats.
     *
     * @param freeSeatsValue value of freeSeats.
     */
    public void setFreeSeats(final int freeSeatsValue) {
        freeSeats = freeSeatsValue;
    }

    /**
     * Gets price.
     *
     * @return value of price.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets price.
     *
     * @param priceValue value of price.
     */
    public void setPrice(final double priceValue) {
        price = priceValue;
    }

    /**
     * Gets isSmokingAllowed.
     *
     * @return value of isSmokingAllowed.
     */
    public boolean isSmokingAllowed() {
        return isSmokingAllowed;
    }

    /**
     * Sets isSmokingAllowed.
     *
     * @param smokingAllowedValue value of isSmokingAllowed.
     */
    public void setSmokingAllowed(final boolean smokingAllowedValue) {
        isSmokingAllowed = smokingAllowedValue;
    }

    /**
     * Gets isPetsAllowed.
     *
     * @return value of isPetsAllowed.
     */
    public boolean isPetsAllowed() {
        return isPetsAllowed;
    }

    /**
     * Sets isPetsAllowed.
     *
     * @param petsAllowedValue value of isPetsAllowed.
     */
    public void setPetsAllowed(final boolean petsAllowedValue) {
        isPetsAllowed = petsAllowedValue;
    }

    /**
     * Gets passengers.
     *
     * @return value of passengers.
     */
    public List<User> getPassengers() {
        return passengers;
    }

    /**
     * Sets passengers.
     *
     * @param passengersValue value of passengers.
     */
    public void setPassengers(final List<User> passengersValue) {
        passengers = passengersValue;
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        result = PRIME * result + driver.hashCode();
        result = PRIME * result + from.hashCode();
        result = PRIME * result + to.hashCode();
        result = PRIME * result + departureDatetime.hashCode();
        result = PRIME * result + arrivalDatetime.hashCode();
        result = PRIME * result + freeSeats;
        temp = Double.doubleToLongBits(price);
        result = PRIME * result + (int) (temp ^ (temp >>> SHIFT));
        result = PRIME * result + passengers.hashCode();
        if (isSmokingAllowed) {
            result = PRIME * result + 1;
        } else {
            result = PRIME * result;
        }
        if (isPetsAllowed) {
            result = PRIME * result + 1;
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

        Trip trip = (Trip) o;

        if (!driver.equals(trip.driver)) {
            return false;
        }
        if (freeSeats != trip.freeSeats) {
            return false;
        }
        if (Double.compare(trip.price, price) != 0) {
            return false;
        }
        if (isSmokingAllowed != trip.isSmokingAllowed) {
            return false;
        }
        if (isPetsAllowed != trip.isPetsAllowed) {
            return false;
        }
        if (!from.equals(trip.from)) {
            return false;
        }
        if (!to.equals(trip.to)) {
            return false;
        }
        if (!passengers.equals(trip.passengers)) {
            return false;
        }
        if (!departureDatetime.equals(trip.departureDatetime)) {
            return false;
        }
        return arrivalDatetime.equals(trip.arrivalDatetime);
    }

    /**
     * Returns a string representation of the object. In general, the
     * {@code toString} method returns a string that
     * "textually represents" this object.
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return String.format(
                "Trip{driver=%s, from=%s, to=%s, departureDatetime=%s, "
                + "arrivalDatetime=%s, freeSeats=%d, price=%s, "
                + "isSmokingAllowed=%s, isPetsAllowed=%s, passengers=%s}",
                driver, from, to, departureDatetime, arrivalDatetime,
                freeSeats, price, isSmokingAllowed, isPetsAllowed, passengers);
    }
}
