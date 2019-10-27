package by.gartsmanovich.webparsing.repository.specification;

import java.util.List;

/**
 * Common interface for specifications.
 *
 * @param <T> the type of elements which the specification is used for.
 * @author Dmitry Gartsmanovich
 */
public interface Specification<T> {

    /**
     * Applies specified criteria to the provided element.
     *
     * @param component the provided component.
     * @return the list of elements that correspond to specified criteria.
     */
    List<T> specified(T component);
}
