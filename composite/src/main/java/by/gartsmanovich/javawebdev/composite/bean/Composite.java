package by.gartsmanovich.javawebdev.composite.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class used to store a simple and compound objects and define its behavior.
 *
 * @author Dmitry Gartsmanovich
 */
public class Composite implements Component {

    /**
     * The prime number for hashcode.
     */
    private static final int PRIME = 31;
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
     * Collects all components in one string.
     *
     * @return the result string.
     */
    @Override
    public String collect() {
        StringBuilder sb = new StringBuilder();

        for (Component component : this.components) {
            switch (type) {
                case PARAGRAPH:
                    sb.append("    ").append(component.collect())
                      .append(System.lineSeparator());
                    break;
                case SENTENCE:
                case WORD:
                case EXPRESSION:
                case SYMBOL:
                    sb.append(component.collect());
                    break;
                case LEXEME:
                    sb.append(component.collect()).append(" ");
                    break;
                default:
                    throw new IllegalStateException(
                            "Unexpected value: " + type);
            }
        }
        return sb.toString();
    }

    /**
     * Returns the list of components.
     *
     * @return the list of components.
     */
    @Override
    public List<Component> getComponents() {
        return components;
    }

    /**
     * Returns the list of components selected by provided type.
     *
     * @param component the provided component.
     * @param typeValue the type of component.
     * @return the list of components of the same type.
     */
    @Override
    public List<Component> getComponentsByType(final Component component,
            final ComponentType typeValue) {
        ArrayList<Component> resultList = new ArrayList<>();
        if (component.getType() == typeValue) {
            resultList.add(component);
        } else {
            for (Component child : component.getComponents()) {
                resultList.addAll(getComponentsByType(child, typeValue));
            }
        }
        return resultList;
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

        Composite composite = (Composite) o;

        if (!Objects.equals(components, composite.components)) {
            return false;
        }
        return type == composite.type;
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int result;
        if (components != null) {
            result = components.hashCode();
        } else {
            result = 0;
        }
        if (type != null) {
            result = PRIME * result + type.hashCode();
        } else {
            result = PRIME * result;
        }
        return result;
    }
}
