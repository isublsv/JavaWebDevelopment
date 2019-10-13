package by.gartsmanovich.javawebdev.composite.repository.specification;

import by.gartsmanovich.javawebdev.composite.bean.Component;

import java.util.List;

/**
 * Common interface for specifications.
 *
 * @param <T> the type of elements which the specification is used for.
 *
 * @author Dmitry Gartsmanovich
 */
public interface Specification<T extends Component> {

    /**
     * Applies specified criteria to the provided array.
     *
     * @param list        the provided list of elements.
     * @return the result string that correspond to specified criteria
     */
    String specified(List<T> list);
}
