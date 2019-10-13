package by.gartsmanovich.javawebdev.composite.repository.specification.sort;

import by.gartsmanovich.javawebdev.composite.bean.Component;
import by.gartsmanovich.javawebdev.composite.repository.specification
        .Specification;

import java.util.Comparator;
import java.util.List;

/**
 * @author Dmitry Gartsmanovich
 */
public class SortByWordLengthSpecification implements Specification<Component> {

    /**
     * A comparison function, which imposes a total ordering on provided
     * storage of entities.
     */
    private Comparator<Component> comparator;

    /**
     * Construct Sort Specification with provided comparator.
     *
     * @param comp the provided comparator.
     */
    public SortByWordLengthSpecification(final Comparator<Component> comp) {
        comparator = comp;
    }

    /**
     * Applies specified criteria to the provided array.
     *
     * @param list the provided list of elements.
     * @return the result string that correspond to specified criteria
     */
    @Override
    public String specified(final List<Component> list) {
        return null;
    }
}
