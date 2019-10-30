package by.gartsmanovich.webparsing.repository.specification;

import by.gartsmanovich.webparsing.repository.exception.RepositoryException;

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
     * @return the list of elements that correspond to specified criteria.
     * @throws RepositoryException if error happens during execution.
     */
    List<T> specified() throws RepositoryException;
}
