package by.gartsmanovich.hitcher.bean;

import java.io.Serializable;

/**
 * The basic DAO entity that store an ID.
 *
 * @author Dmitry Gartsmanovich
 */
public abstract class Entity implements Serializable {

    /**
     * The prime number for hashcode.
     */
    static final int PRIME = 31;

    /**
     * The number for shifting double or long value to int value.
     */
    static final int SHIFT = 32;

    /**
     * The ID of an entity.
     */
    private long id;

    /**
     * Default constructor.
     */
    Entity() {
    }

    /**
     * Constructs the entity instance with ID value.
     *
     * @param idValue the ID value.
     */
    Entity(final long idValue) {
        id = idValue;
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
     * Returns a hash code value for the object.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> SHIFT));
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

        Entity entity = (Entity) o;

        return id == entity.id;
    }
}
