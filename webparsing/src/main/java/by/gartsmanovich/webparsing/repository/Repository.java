package by.gartsmanovich.webparsing.repository;

import by.gartsmanovich.webparsing.bean.Drug;
import by.gartsmanovich.webparsing.repository.exception.RepositoryException;
import by.gartsmanovich.webparsing.repository.specification.Specification;

import java.util.List;

/**
 * Interface used to describe the common structure of the different types of
 * repositories.
 *
 * @param <T> the type of elements which the repository can store.
 * @author Dmitry Gartsmanovich
 */
public interface Repository<T extends Drug> {

    /**
     * Determines the business rules that applies to the composite.
     *
     * @param specification the concrete specification that query different
     *                      types of actions.
     * @return the list that contains result of query execution.
     * @throws RepositoryException if error happens during execution.
     */
    List<T> query(Specification<T> specification) throws RepositoryException;
}
