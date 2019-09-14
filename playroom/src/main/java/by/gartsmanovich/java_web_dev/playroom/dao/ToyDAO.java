package by.gartsmanovich.java_web_dev.playroom.dao;

import by.gartsmanovich.java_web_dev.playroom.bean.toy.Toy;

public interface ToyDAO {

    /**
     * Adds an entity to the data source.
     *
     * @param toy the entity to add.
     */
    void addToy(Toy toy);

    /**
     * Deletes an entity by id from the data source.
     *
     * @param idToy an id parameter of an entity.
     */
    void deleteToyById(long idToy);

    /**
     * Deletes an entity by provided object.
     *
     * @param toy the provided entity.
     */
    void deleteToy(Toy toy);
}
