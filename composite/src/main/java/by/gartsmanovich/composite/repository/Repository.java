package by.gartsmanovich.composite.repository;

import by.gartsmanovich.composite.repository.specification.Specification;
import by.gartsmanovich.composite.bean.Component;
import by.gartsmanovich.composite.repository.exception.RepositoryException;

/**
 * Interface used to describe the common structure of the different types of
 * repositories.
 * @param <T> the type of elements which the repository can store.
 *
 * @author Dmitry Gartsmanovich
 */
public interface Repository<T extends Component> {

    /**
     * Creates the composite instance.
     *
     * @param path      the path to storage file.
     * @throws RepositoryException if error happens during execution.
     */
    void createComposite(String path) throws RepositoryException;

    /**
     * Determines the business rules that applies to the composite.
     *
     * @param specification the concrete specification that query different
     *                      types of actions.
     * @return the string that contains result of query execution.
     * @throws RepositoryException if error happens during execution.
     */
    String query(Specification<T> specification) throws RepositoryException;

    /**
     * Saves the last result obtained after executing the any method.
     *
     * @param path the path to storage file.
     * @throws RepositoryException if error happens during execution.
     */
    void saveComposite(String path) throws RepositoryException;
}
