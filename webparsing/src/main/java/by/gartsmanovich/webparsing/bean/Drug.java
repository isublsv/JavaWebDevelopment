package by.gartsmanovich.webparsing.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The class describes the drug definition.
 *
 * @author Dmitry Gartsmanovich
 */
public class Drug {

    /**
     * The prime number for hashcode.
     */
    private static final int PRIME = 31;

    /**
     * The number for shifting double value to int value.
     */
    private static final int SHIFT = 32;

    /**
     * The ID of the drug.
     */
    private long id;

    /**
     * The name of the drug.
     */
    private String name;

    /**
     * The group of the drug.
     */
    private Group group;

    /**
     * The list of analogs of the drug.
     */
    private List<String> analogs;

    /**
     * The list of the forms of the drug(tablets, powder etc.).
     */
    private List<Form> versions;

    /**
     * Default constructor.
     */
    public Drug() {
        analogs = new ArrayList<>();
        versions = new ArrayList<>();
    }

    /**
     * Constructs the drug with specific parameters.
     *
     * @param idValue    the ID of the drug.
     * @param nameValue  the name of the drug.
     * @param groupValue the group of the drug.
     */
    public Drug(final long idValue, final String nameValue,
            final Group groupValue) {
        id = idValue;
        name = nameValue;
        group = groupValue;
        analogs = new ArrayList<>();
        versions = new ArrayList<>();
    }

    /**
     * Gets id.
     *
     * @return value of id.
     */
    public long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param idValue value of id.
     */
    public void setId(final long idValue) {
        id = idValue;
    }

    /**
     * Gets name.
     *
     * @return value of name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param nameValue value of name.
     */
    public void setName(final String nameValue) {
        name = nameValue;
    }

    /**
     * Gets group.
     *
     * @return value of group.
     */
    public Group getGroup() {
        return group;
    }

    /**
     * Sets group.
     *
     * @param groupValue value of group.
     */
    public void setGroup(final Group groupValue) {
        group = groupValue;
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * @param analog the element to be appended to this list.
     * @return true if the list changed as a result of the call.
     */
    public boolean addAnalog(final String analog) {
        return analogs.add(analog);
    }

    /**
     * Removes the first occurrence of the specified element from this list,
     * if it is present.
     *
     * @param analog the element to be removed from this list, if present.
     * @return true if an element was removed as a result of this call
     */
    public boolean removeAnalog(final String analog) {
        return analogs.remove(analog);
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * @param version the element to be appended to this list.
     * @return true if the list changed as a result of the call.
     */
    public boolean addVersion(final Form version) {
        return versions.add(version);
    }

    /**
     * Removes the first occurrence of the specified element from this list,
     * if it is present.
     *
     * @param version the element to be removed from this list, if present.
     * @return true if an element was removed as a result of this call
     */
    public boolean removeVersion(final Form version) {
        return versions.remove(version);
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> SHIFT));
        result = PRIME * result + name.hashCode();
        result = PRIME * result + group.hashCode();
        if (analogs != null) {
            result = PRIME * result + analogs.hashCode();
        } else {
            result = PRIME * result;
        }
        result = PRIME * result + versions.hashCode();
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

        Drug drug = (Drug) o;

        if (id != drug.id) {
            return false;
        }
        if (!Objects.equals(name, drug.name)) {
            return false;
        }
        if (group != drug.group) {
            return false;
        }
        if (!Objects.equals(analogs, drug.analogs)) {
            return false;
        }
        return Objects.equals(versions, drug.versions);
    }
}
