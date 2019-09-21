package by.gartsmanovich.javawebdev.playroom.repository;

import by.gartsmanovich.javawebdev.playroom.bean.Entity;
import by.gartsmanovich.javawebdev.playroom.repository.exception
        .RepositoryException;
import by.gartsmanovich.javawebdev.playroom.repository.specification
        .Specification;

import java.util.List;

public interface Repository<T extends Entity> {

    /**
     * Creates the play room storage.
     *
     * @param budget the budget of play room.
     * @param path the path to storage file.
     * @param delimiter the delimiter to parse the data from file.
     * @throws RepositoryException if error happens during execution.
     */
    void createStorage(double budget, String path, String delimiter)
            throws RepositoryException;

    /**
     * Adds an entity to the end of the storage.
     *
     * @param entity the entity to add.
     * @return true if operation was completed successful, false - otherwise.
     * @throws RepositoryException if error happens during execution.
     */
    boolean add(T entity) throws RepositoryException;

    /**
     * Updates an entity by ID from the storage.
     *
     * @param entity the entity to update.
     * @return true if operation was completed successful, false - otherwise.
     * @throws RepositoryException if error happens during execution.
     */
    boolean update(T entity) throws RepositoryException;

    /**
     * Removes entity with provided ID from the storage.
     *
     * @param id the entity to remove.
     * @return true if operation was completed successful, false - otherwise.
     * @throws RepositoryException if error happens during execution.
     */
    boolean remove(long id) throws RepositoryException;

    /**
     * Saves repository storage to the file.
     *
     * @param path the path to file to save.
     * @throws RepositoryException if error happens during execution.
     */
    void saveStorage(String path) throws RepositoryException;

    /**
     * Determines the business rules that applies to the storage data.
     *
     * @param specification the concrete specification that query different
     *                      types of actions.
     * @return the specified list of entities or empty list.
     * @throws RepositoryException if error happens during execution.
     */
    List<T> query(Specification<T> specification) throws RepositoryException;
}
