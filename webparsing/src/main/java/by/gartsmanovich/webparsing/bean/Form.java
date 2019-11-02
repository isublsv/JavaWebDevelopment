package by.gartsmanovich.webparsing.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * The class describes the different types of forms of the drugs.
 *
 * @author Dmitry Gartsmanovich
 */
public class Form {

    /**
     * The prime number for hashcode.
     */
    private static final int PRIME = 31;

    /**
     * The list of pharmacies.
     */
    private List<Pharmacy> pharmacies;

    /**
     * The form type of the drug.
     */
    private FormType type;

    /**
     * Constructs the specific form of the drug.
     *
     * @param typeValue the form type of the drug.
     */
    public Form(final FormType typeValue) {
        pharmacies = new ArrayList<>();
        type = typeValue;
    }

    /**
     * Gets type.
     *
     * @return value of type.
     */
    public FormType getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param typeValue value of type.
     */
    public void setType(final FormType typeValue) {
        type = typeValue;
    }

    /**
     * Gets pharmacies.
     *
     * @return value of pharmacies.
     */
    public List<Pharmacy> getPharmacies() {
        return pharmacies;
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * @param pharmacy the element to be appended to this list.
     * @return true if the list changed as a result of the call.
     */
    public boolean addPharmacy(final Pharmacy pharmacy) {
        return pharmacies.add(pharmacy);
    }

    /**
     * Removes the first occurrence of the specified element from this list,
     * if it is present.
     *
     * @param pharmacy the element to be removed from this list, if present.
     * @return true if an element was removed as a result of this call
     */
    public boolean removePharmacy(final Pharmacy pharmacy) {
        return pharmacies.remove(pharmacy);
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int result = pharmacies.hashCode();
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

        Form form = (Form) o;

        if (!pharmacies.equals(form.pharmacies)) {
            return false;
        }
        return type == form.type;
    }

    /**
     * Represents an instance of an entity as a string value.
     *
     * @return a string representation of an entity.
     */
    @Override
    public String toString() {
        return String.format("pharmacies=%s, type=%s", pharmacies.toString(),
                             type);
    }
}
