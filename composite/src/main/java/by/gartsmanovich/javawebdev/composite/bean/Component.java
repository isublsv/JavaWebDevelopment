package by.gartsmanovich.javawebdev.composite.bean;

/**
 * The Component class defines the base interface for simple and compound
 * objects.
 *
 * @author Dmitry Gartsmanovich
 */
public interface Component {

    /**
     * Returns type of the component.
     *
     * @return type of the component.
     */
    ComponentType getType();

    /**
     * Collects all components in one string.
     *
     * @return the result string.
     */
    String collect();
}
