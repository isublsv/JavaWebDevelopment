package by.gartsmanovich.webparsing.bean;

import java.util.Objects;

/**
 * The class Pharmacy stores the data about certificate, dosage and package
 * type of the drug.
 *
 * @author Dmitry Gartsmanovich
 */
public class Pharmacy {

    /**
     * The prime number for hashcode.
     */
    private static final int PRIME = 31;

    /**
     * The number for shifting double value to int value.
     */
    private static final int SHIFT = 32;

    /**
     * The certificate of the pharmacy.
     */
    private Certificate certificate;

    /**
     * The dosage of the drug.
     */
    private double dosage;

    /**
     * The package type of the drug.
     */
    private PackageType type;

    /**
     * Default constructor.
     */
    public Pharmacy() {
    }

    /**
     * Constructs the instance of the pharmacy with specific parameters.
     *
     * @param certificateValue the certificate of the pharmacy.
     * @param dosageValue      the dosage value of the drug.
     * @param typeValue        the package type of the drug.
     */
    public Pharmacy(final Certificate certificateValue,
            final double dosageValue, final PackageType typeValue) {
        certificate = certificateValue;
        dosage = dosageValue;
        type = typeValue;
    }

    /**
     * Gets certificate.
     *
     * @return value of certificate.
     */
    public Certificate getCertificate() {
        return certificate;
    }

    /**
     * Sets certificate.
     *
     * @param certificateValue value of certificate.
     */
    public void setCertificate(final Certificate certificateValue) {
        certificate = certificateValue;
    }

    /**
     * Gets dosage.
     *
     * @return value of dosage.
     */
    public double getDosage() {
        return dosage;
    }

    /**
     * Sets dosage.
     *
     * @param dosageValue value of dosage.
     */
    public void setDosage(final double dosageValue) {
        dosage = dosageValue;
    }

    /**
     * Gets type.
     *
     * @return value of type.
     */
    public PackageType getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param typeValue value of type.
     */
    public void setType(final PackageType typeValue) {
        type = typeValue;
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int result;
        long temp;
        result = certificate.hashCode();
        temp = Double.doubleToLongBits(dosage);
        result = PRIME * result + (int) (temp ^ (temp >>> SHIFT));
        result = PRIME * result + type.hashCode();
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

        Pharmacy pharmacy = (Pharmacy) o;

        if (Double.compare(pharmacy.dosage, dosage) != 0) {
            return false;
        }
        if (!Objects.equals(certificate, pharmacy.certificate)) {
            return false;
        }
        return Objects.equals(type, pharmacy.type);
    }

    /**
     * Represents an instance of an entity as a string value.
     *
     * @return a string representation of an entity.
     */
    @Override
    public String toString() {
        return String.format("certificate=%s, dosage=%s, type=%s",
                             certificate, dosage, type);
    }
}
