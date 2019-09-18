package by.gartsmanovich.java_web_dev.playroom.repository;

import by.gartsmanovich.java_web_dev.playroom.bean.toy.Toy;
import by.gartsmanovich.java_web_dev.playroom.repository.specification
        .Specification;

import java.util.List;

public interface Repository<T extends Toy> {

    /**
     * Adds an entity to the storage.
     *
     * @param entity the entity to add.
     * @return the number entities that have been added to the storage.
     */
    int add(T entity);

    /**
     * Updates an entity by ID from the storage.
     *
     * @param entity the entity to update.
     * @return the number entities that have been updated in the storage.
     */
    int update(T entity);

    /**
     * Removes an entity by ID of provided object from the storage.
     *
     * @param entity the entity to remove.
     * @return the number entities that have been removed from the storage.
     */
    int remove(T entity);

    /**
     * Determines the business rules that applies to the storage data.
     *
     * @param specification the concrete specification that query different
     *                      types of actions.
     * @return the specified list of entities.
     */
    List<T> query(Specification specification);
}
