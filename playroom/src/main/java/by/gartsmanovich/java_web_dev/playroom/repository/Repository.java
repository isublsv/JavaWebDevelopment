package by.gartsmanovich.java_web_dev.playroom.repository;

import by.gartsmanovich.java_web_dev.playroom.bean.Entity;
import by.gartsmanovich.java_web_dev.playroom.repository.specification
        .Specification;

import java.util.List;

public interface Repository<T extends Entity> {

    /**
     * Creates the play room storage.
     *
     * @param budget the budget of play room.
     */
    void createStorage(double budget);

    /**
     * Adds an entity to the storage.
     *
     * @param entity the entity to add.
     */
    void add(T entity);

    /**
     * Updates an entity by ID from the storage.
     *
     * @param entity the entity to update.
     */
    void update(T entity);

    /**
     * Removes an entity by ID of provided object from the storage.
     *
     * @param entity the entity to remove.
     */
    void remove(T entity);

    /**
     * Determines the business rules that applies to the storage data.
     *
     * @param specification the concrete specification that query different
     *                      types of actions.
     * @return the specified list of entities.
     */
    List<T> query(Specification specification);
}
