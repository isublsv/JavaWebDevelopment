package by.gartsmanovich.composite.service.util;

import by.gartsmanovich.composite.bean.Component;
import by.gartsmanovich.composite.bean.ComponentType;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class used to proceed components.
 *
 * @author Dmitry Gartsmanovich
 */
public final class CompositeUtils {

    /**
     * The list of components of the same type.
     */
    private static List<Component> resultList = new ArrayList<>();

    /**
     * Private constructor. Forbids the explicit object creation.
     */
    private CompositeUtils() {
    }

    /**
     * Returns the list of components selected by provided type.
     *
     * @param component the provided component.
     * @param typeValue the type of component.
     * @return the list of components of the same type.
     */
    public static List<Component> getComponentsByType(final Component component,
            final ComponentType typeValue) {
        if (component.getType() == typeValue) {
            resultList.addAll(component.getComponents());
        } else if (component.getType() != ComponentType.SYMBOL) {
            for (Component child : component.getComponents()) {
               getComponentsByType(child, typeValue);
            }
        }
        return resultList;
    }

    /**
     * Clears the current list.
     */
    public static void clearList() {
        resultList.clear();
    }
}
