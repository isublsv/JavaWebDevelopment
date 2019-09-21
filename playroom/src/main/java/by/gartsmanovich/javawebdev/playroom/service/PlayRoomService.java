package by.gartsmanovich.javawebdev.playroom.service;

import by.gartsmanovich.javawebdev.playroom.bean.Entity;
import by.gartsmanovich.javawebdev.playroom.service.exception
        .ServiceException;

import java.util.List;

public interface PlayRoomService<T extends Entity> {

    /**
     * Creates play room instance and fill the storage by provided budget
     * amount.
     *
     * @param budget the total price of all toys in the play room.
     * @param path the path to storage file.
     * @param delimiter the delimiter to parse the data from file.
     * @return true if operation was completed successful, false - otherwise.
     * @throws ServiceException if error happens during execution.
     */
    boolean createPlayRoom(double budget, String path, String delimiter)
            throws ServiceException;

    /**
     * Adds new entity in the end of the storage.
     *
     * @param entity the string representation of the entity to add.
     * @return true if operation was completed successful, false - otherwise.
     * @throws ServiceException if error happens during execution.
     */
    boolean addEntity(String... entity) throws ServiceException;

    /**
     * Update entity in the repository by ID.
     *
     * @param entity the string representation of the entity to update.
     * @return true if operation was completed successful, false - otherwise.
     * @throws ServiceException if error happens during execution.
     */
    boolean updateEntity(String... entity) throws ServiceException;

    /**
     * Removes entity with provided ID from the storage.
     *
     * @param id the ID of the entity to remove.
     * @return true if operation was completed successful, false - otherwise.
     * @throws ServiceException if error happens during execution.
     */
    boolean removeEntity(long id) throws ServiceException;

    /**
     * Finds an entity by ID in the storage.
     *
     * @param id the provided ID.
     * @return the list of all founded entities or empty list.
     * @throws ServiceException if error happens during execution.
     */
    List<T> findEntityByID(long id) throws ServiceException;

    /**
     * Finds an entity by provided title.
     *
     * @param title the provided title of the entity.
     * @return the list of all founded entities or empty list.
     * @throws ServiceException if error happens during execution.
     */
    List<T> findEntityByTitle(String title) throws ServiceException;

    /**
     * Finds all the entities which title start with the provided character
     * symbol.
     *
     * @param c the provided character symbol.
     * @return the list of all founded entities or empty list.
     * @throws ServiceException if error happens during execution.
     */
    List<T> findEntityByFirstTitleLetter(char c) throws ServiceException;

    /**
     * Finds all the entities in the given ID range.
     *
     * @param startId the starting ID value.
     * @param endId   the final ID value.
     * @return the list of all founded entities or empty list.
     * @throws ServiceException if error happens during execution.
     */
    List<T> findEntityByRangeId(long startId, long endId)
            throws ServiceException;

    /**
     * Finds all the entities in the storage.
     *
     * @return the list of all founded entities or empty list.
     * @throws ServiceException if error happens during execution.
     */
    List<T> findAll() throws ServiceException;

    /**
     * Sorts the storage by age.
     *
     * @return the list of sorted entities by age or empty list.
     * @throws ServiceException if error happens during execution.
     */
    List<T> sortByAge() throws ServiceException;

    /**
     * Sorts the repository by color, than by price.
     *
     * @return the list of sorted entities by color, by price than or
     * empty list.
     * @throws ServiceException if error happens during execution.
     */
    List<T> sortByColorAndPrice() throws ServiceException;

    /**
     * Save storage to the file before exit.
     *
     * @param path the path to file to save the storage.
     * @throws ServiceException if error happens during execution.
     */
    void saveAll(String path)throws ServiceException;
}
