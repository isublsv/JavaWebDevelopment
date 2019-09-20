package by.gartsmanovich.javawebdev.playroom.repository.impl;

import by.gartsmanovich.javawebdev.playroom.bean.PlayRoom;
import by.gartsmanovich.javawebdev.playroom.bean.toy.Toy;
import by.gartsmanovich.javawebdev.playroom.repository.Repository;
import by.gartsmanovich.javawebdev.playroom.repository.specification
        .Specification;

import java.util.Collections;
import java.util.List;

public class ToyRepository implements Repository<Toy> {

    /**
     * The storage that contains entities to handle.
     */
    private PlayRoom storage;

    /**
     * Creates the play room storage.
     *
     * @param budget the budget of play room.
     */
    @Override
    public void createStorage(final double budget) {
        storage = PlayRoom.getInstance(budget);
    }

    /**
     * Adds an entity to the storage.
     *
     * @param entity the entity to add.
     */
    @Override
    public void add(final Toy entity) {
        storage.getToyStorage().add(entity);
    }

    /**
     * Updates an entity by ID from the storage.
     *
     * @param entity the entity to update.
     */
    @Override
    public void update(final Toy entity) {
        int index = storage.getToyStorage().indexOf(entity);
        if (index >= 0) {
        storage.getToyStorage().set(index, entity);
        }
    }

    /**
     * Removes an entity by ID of provided object from the storage.
     *
     * @param entity the entity to remove.
     */
    @Override
    public void remove(final Toy entity) {
        storage.getToyStorage().remove(entity);
    }

    /**
     * Determines the business rules that applies to the storage data.
     *
     * @param specification the concrete specification that query different
     *                      types of actions.
     * @return the specified list of entities.
     */
    @Override
    public List<Toy> query(final Specification<Toy> specification) {
        List<Toy> toys = specification.specified(storage.getToyStorage());

        if (!toys.isEmpty()) {
            return toys;
        } else {
            return Collections.emptyList();
        }
    }
}
