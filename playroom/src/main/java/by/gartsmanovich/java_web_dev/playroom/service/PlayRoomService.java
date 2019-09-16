package by.gartsmanovich.java_web_dev.playroom.service;

import by.gartsmanovich.java_web_dev.playroom.bean.Entity;
import by.gartsmanovich.java_web_dev.playroom.service.exception
        .ServiceException;

import java.util.List;

public interface PlayRoomService<T extends Entity> {

    /**
     * Adds new entity in the repository. If the entity with current ID is
     * present, than replace it.
     *
     * @param entity the entity to add or replace.
     * @throws ServiceException if entity ID has incorrect format.
     */
    void addNewEntity(T entity) throws ServiceException;

    /**
     * Deletes entity with provided ID from the repository.
     *
     * @param id the ID of an entity to delete.
     * @throws ServiceException if entity ID has incorrect format.
     */
    void deleteEntityByID(long id) throws ServiceException;

    /**
     * Finds an entity by ID in the repository.
     *
     * @param id the provided entity's ID.
     * @return an entity if present.
     * @throws ServiceException if entity Id has incorrect format.
     */
    T findEntityByID(long id) throws ServiceException;

    /**
     * Finds an entity by provided title.
     *
     * @param title the provided title of an entity.
     * @return an entity if present.
     * @throws ServiceException if title parameter has incorrect format.
     */
    T findEntityByTitle(String title) throws ServiceException;

    /**
     * Finds all the entities which title start with the provided character
     * symbol.
     *
     * @param c the provided character symbol.
     * @return the list of all founded entities.
     * @throws ServiceException if input parameter has incorrect format.
     */
    List<T> findEntityByFirstTitleLetter(char c) throws ServiceException;

    /**
     * Finds all the entities in the given Id range.
     *
     * @param startId the starting Id value.
     * @param endId   the final Id value.
     * @return the list of all founded entities.
     * @throws ServiceException if input parameters has incorrect format.
     */
    List<T> findEntityByRangeID(int startId, int endId) throws ServiceException;

    /**
     * Sorts repository by age.
     */
    void sortByAge();

    /**
     * Sorts the repository by Color, than by price.
     */
    void sortByColorAndPrice();
}
