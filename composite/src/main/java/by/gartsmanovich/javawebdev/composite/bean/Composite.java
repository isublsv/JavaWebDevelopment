package by.gartsmanovich.javawebdev.composite.bean;

import java.util.ArrayList;

/**
 * Class used to store a simple and compound objects and define its behavior.
 *
 * @author Dmitry Gartsmanovich
 */
public class Composite implements Component {

    /**
     * The list of components.
     */
    private ArrayList<Component> components;

    /**
     * The type of component.
     */
    private ComponentType type;

    /**
     * Constructs the components instance with specified type.
     *
     * @param typeValue the provided type of the component.
     */
    public Composite(final ComponentType typeValue) {
        type = typeValue;
        components = new ArrayList<>();
    }

    /**
     * Returns type of the component.
     *
     * @return type of the component.
     */
    @Override
    public ComponentType getType() {
        return type;
    }

    /**
     * Appends the specified component to the end of this list.
     *
     * @param component the provided component.
     */
    public void add(final Component component) {
        components.add(component);
    }

    /**
     * Removes the first occurrence of the specified component from this list,
     * if it is present.
     *
     * @param component the provided component.
     */
    public void remove(final Component component) {
        components.remove(component);
    }
}
