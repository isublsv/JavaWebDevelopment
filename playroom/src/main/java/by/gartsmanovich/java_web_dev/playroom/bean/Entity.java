package by.gartsmanovich.java_web_dev.playroom.bean;

public class Entity {

    /**
     * The prime number for hashcode.
     */
    protected static final int PRIME = 31;

    /**
     * The number for shifting double value to int value.
     */
    protected static final int SHIFT = 32;

    /**
     * The ID of an entity.
     */
    private long id;

    /**
     * Default constructor.
     */
    public Entity() {
    }

    /**
     * Constructs an entity with id.
     *
     * @param idValue the identifier of an entity.
     */
    public Entity(final long idValue) {
        this.id = idValue;
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
        this.id = idValue;
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

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> SHIFT));
    }
}
