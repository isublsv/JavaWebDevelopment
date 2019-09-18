package by.gartsmanovich.java_web_dev.playroom.service;

import by.gartsmanovich.java_web_dev.playroom.bean.Entity;
import by.gartsmanovich.java_web_dev.playroom.service.exception
        .ServiceException;

import java.util.List;

public interface PlayRoomService<T extends Entity> {

    /**
     * Creates play room instance and fill the storage by provided budget
     * amount.
     *
     * @param budget the total price of all toys in the play room.
     * @throws ServiceException if error happens during execution.
     */
    void createPlayRoom(double budget) throws ServiceException;

    /**
     * Adds new entity in the end of the storage.
     *
     * @param entity the string representation of the entity to add.
     * @throws ServiceException if error happens during execution.
     */
    void addEntity(String... entity) throws ServiceException;

    /**
     * Update entity in the repository by ID.
     *
     * @param entity the string representation of the entity to update.
     * @throws ServiceException if error happens during execution.
     */
    void updateEntity(String... entity) throws ServiceException;

    /**
     * Removes entity with provided ID from the storage.
     *
     * @param id the ID of the entity to remove.
     * @throws ServiceException if error happens during execution.
     */
    void removeEntity(long id) throws ServiceException;

    /**
     * Finds an entity by ID in the storage.
     *
     * @param id the provided ID.
     * @return an entity if is present.
     * @throws ServiceException if error happens during execution.
     */
    T findEntityByID(long id) throws ServiceException;

    /**
     * Finds an entity by provided title.
     *
     * @param title the provided title of the entity.
     * @return the list of all founded entities.
     * @throws ServiceException if error happens during execution.
     */
    List<T> findEntityByTitle(String title) throws ServiceException;

    /**
     * Finds all the entities which title start with the provided character
     * symbol.
     *
     * @param c the provided character symbol.
     * @return the list of all founded entities.
     * @throws ServiceException if error happens during execution.
     */
    List<T> findEntityByFirstTitleLetter(char c) throws ServiceException;

    /**
     * Finds all the entities in the given ID range.
     *
     * @param startId the starting ID value.
     * @param endId   the final ID value.
     * @return the list of all founded entities.
     * @throws ServiceException if error happens during execution.
     */
    List<T> findEntityByRangeID(long startId, long endId)
            throws ServiceException;

    /**
     * Finds all the entities in the storage.
     *
     * @return the list of all founded entities.
     * @throws ServiceException if error happens during execution.
     */
    List<T> findAll() throws ServiceException;

    /**
     * Sorts the storage by age.
     *
     * @throws ServiceException if error happens during execution.
     */
    void sortByAge() throws ServiceException;

    /**
     * Sorts the repository by Color, than by price.
     *
     * @throws ServiceException if error happens during execution.
     */
    void sortByColorAndPrice() throws ServiceException;

    /**
     * Save storage to the file before exit.
     *
     * @throws ServiceException if error happens during execution.
     */
    void saveAll()throws ServiceException;
}
