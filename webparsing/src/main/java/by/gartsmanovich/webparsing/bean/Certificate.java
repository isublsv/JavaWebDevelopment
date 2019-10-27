package by.gartsmanovich.webparsing.bean;

import java.time.LocalDate;
import java.util.Objects;

/**
 * The class contains the data that describes certificate of the pharmacy
 * and its properties.
 *
 * @author Dmitry Gartsmanovich
 */
public class Certificate {

    /**
     * The prime number for hashcode.
     */
    private static final int PRIME = 31;

    /**
     * The number for shifting double value to int value.
     */
    private static final int SHIFT = 32;

    /**
     * The number of certificate.
     */
    private long number;

    /**
     * The issue date of certificate.
     */
    private LocalDate issueDate;

    /**
     * The expiration date of certificate.
     */
    private LocalDate expirationDate;

    /**
     * The registration of certificate.
     */
    private String registration;

    /**
     * Constructs the instance of certificate with specific parameters.
     *
     * @param numberValue         the number of certificate.
     * @param issueDateValue      the issue date of certificate.
     * @param expirationDateValue the expiration date of certificate.
     * @param registrationValue   the registration of certificate.
     */
    public Certificate(final long numberValue, final LocalDate issueDateValue,
            final LocalDate expirationDateValue,
            final String registrationValue) {
        number = numberValue;
        issueDate = issueDateValue;
        expirationDate = expirationDateValue;
        registration = registrationValue;
    }

    /**
     * Gets number.
     *
     * @return value of number.
     */
    public long getNumber() {
        return number;
    }

    /**
     * Sets number.
     *
     * @param numberValue value of number.
     */
    public void setNumber(final long numberValue) {
        number = numberValue;
    }

    /**
     * Gets issueDate.
     *
     * @return value of issueDate.
     */
    public LocalDate getIssueDate() {
        return issueDate;
    }

    /**
     * Sets issueDate.
     *
     * @param issueDateValue value of issueDate.
     */
    public void setIssueDate(final LocalDate issueDateValue) {
        issueDate = issueDateValue;
    }

    /**
     * Gets expirationDate.
     *
     * @return value of expirationDate.
     */
    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    /**
     * Sets expirationDate.
     *
     * @param expirationDateValue value of expirationDate.
     */
    public void setExpirationDate(final LocalDate expirationDateValue) {
        expirationDate = expirationDateValue;
    }

    /**
     * Gets registration.
     *
     * @return value of registration.
     */
    public String getRegistration() {
        return registration;
    }

    /**
     * Sets registration.
     *
     * @param registrationValue value of registration.
     */
    public void setRegistration(final String registrationValue) {
        registration = registrationValue;
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int result = (int) (number ^ (number >>> SHIFT));
        result = PRIME * result + issueDate.hashCode();
        result = PRIME * result + expirationDate.hashCode();
        result = PRIME * result + registration.hashCode();
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

        Certificate that = (Certificate) o;

        if (number != that.number) {
            return false;
        }
        if (!Objects.equals(issueDate, that.issueDate)) {
            return false;
        }
        if (!Objects.equals(expirationDate, that.expirationDate)) {
            return false;
        }
        return Objects.equals(registration, that.registration);
    }
}
