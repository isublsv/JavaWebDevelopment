package by.gartsmanovich.javawebdev.composite.repository.specification;

import by.gartsmanovich.javawebdev.composite.bean.Component;

/**
 * Common interface for specifications.
 *
 * @param <T> the type of elements which the specification is used for.
 *
 * @author Dmitry Gartsmanovich
 */
public interface Specification<T extends Component> {

    /**
     * Applies specified criteria to the provided element.
     *
     * @param component        the provided component.
     * @return the result string that correspond to specified criteria
     */
    String specified(T component);
}
