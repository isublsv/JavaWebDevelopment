package by.gartsmanovich.javawebdev.composite.bean;

import java.util.List;

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

    /**
     * Returns the list of components.
     *
     * @return the list of components.
     */
    default List<Component> getComponents() {
        throw new UnsupportedOperationException("Unsupported operation!");
    }


    /**
     * Returns the list of components selected by provided type.
     *
     * @param component the provided component.
     * @param typeValue the type of component.
     * @return the list of components of the same type.
     */
    default List<Component> getComponentsByType(final Component component,
            final ComponentType typeValue) {
        throw new UnsupportedOperationException("Unsupported operation!");
    }
}
