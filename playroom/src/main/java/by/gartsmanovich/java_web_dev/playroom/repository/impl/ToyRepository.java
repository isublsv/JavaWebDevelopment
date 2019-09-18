package by.gartsmanovich.java_web_dev.playroom.repository.impl;

import by.gartsmanovich.java_web_dev.playroom.bean.toy.Toy;
import by.gartsmanovich.java_web_dev.playroom.repository.Repository;
import by.gartsmanovich.java_web_dev.playroom.repository.specification.Specification;


import java.util.*;

public class ToyRepository implements Repository {

    /**
     * Adds an entity to the storage.
     *
     * @param entity the entity to add.
     * @return the number entities that have been added to the storage.
     */
    @Override
    public int add(final Toy entity) {
        return 0;
    }

    /**
     * Updates an entity by ID from the storage.
     *
     * @param entity the entity to update.
     * @return the number entities that have been updated in the storage.
     */
    @Override
    public int update(final Toy entity) {
        return 0;
    }

    /**
     * Removes an entity by ID of provided object from the storage.
     *
     * @param entity the entity to remove.
     * @return the number entities that have been removed from the storage.
     */
    @Override
    public int remove(final Toy entity) {
        return 0;
    }

    /**
     * Determines the business rules that applies to the storage data.
     *
     * @param specification the concrete specification that query different
     *                      types of actions.
     * @return the specified list of entities.
     */
    @Override
    public List query(final Specification specification) {
        return Collections.emptyList();
    }
}
