package by.gartsmanovich.java_web_dev.playroom.service.impl;

import by.gartsmanovich.java_web_dev.playroom.bean.toy.Toy;
import by.gartsmanovich.java_web_dev.playroom.service.PlayRoomService;
import by.gartsmanovich.java_web_dev.playroom.service.exception
        .ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.List;

public class PlayRoomServiceImpl implements PlayRoomService<Toy> {

    /**
     * The logger for Play Room Service class.
     */
    private static final Logger LOGGER = LogManager
            .getLogger(PlayRoomServiceImpl.class);


    /**
     * Creates play room instance and fill the storage by provided budget
     * amount.
     *
     * @param budget the total price of all toys in the play room.
     * @throws ServiceException if error happens during execution.
     */
    @Override
    public void createPlayRoom(final double budget) throws ServiceException {

    }

    /**
     * Adds new entity in the end of the storage.
     *
     * @param entity the string representation of the entity to add.
     * @throws ServiceException if error happens during execution.
     */
    @Override
    public void addEntity(final String... entity) throws ServiceException {

    }

    /**
     * Update entity in the repository by ID.
     *
     * @param entity the string representation of the entity to update.
     * @throws ServiceException if error happens during execution.
     */
    @Override
    public void updateEntity(final String... entity) throws ServiceException {

    }

    /**
     * Removes entity with provided ID from the storage.
     *
     * @param id the ID of the entity to remove.
     * @throws ServiceException if error happens during execution.
     */
    @Override
    public void removeEntity(final long id) throws ServiceException {

    }

    /**
     * Finds an entity by ID in the storage.
     *
     * @param id the provided ID.
     * @return an entity if is present.
     * @throws ServiceException if error happens during execution.
     */
    @Override
    public Toy findEntityByID(final long id) throws ServiceException {
        return null;
    }

    /**
     * Finds an entity by provided title.
     *
     * @param title the provided title of the entity.
     * @return the list of all founded entities.
     * @throws ServiceException if error happens during execution.
     */
    @Override
    public List<Toy> findEntityByTitle(final String title) throws
            ServiceException {
        return Collections.emptyList();
    }

    /**
     * Finds all the entities which title start with the provided character
     * symbol.
     *
     * @param c the provided character symbol.
     * @return the list of all founded entities.
     * @throws ServiceException if error happens during execution.
     */
    @Override
    public List<Toy> findEntityByFirstTitleLetter(final char c) throws
            ServiceException {
        return Collections.emptyList();
    }

    /**
     * Finds all the entities in the given ID range.
     *
     * @param startId the starting ID value.
     * @param endId   the final ID value.
     * @return the list of all founded entities.
     * @throws ServiceException if error happens during execution.
     */
    @Override
    public List<Toy> findEntityByRangeID(final long startId, final long
            endId) throws ServiceException {
        return Collections.emptyList();
    }

    /**
     * Finds all the entities in the storage.
     *
     * @return the list of all founded entities.
     * @throws ServiceException if error happens during execution.
     */
    @Override
    public List<Toy> findAll() throws ServiceException {
        return Collections.emptyList();
    }

    /**
     * Sorts the storage by age.
     *
     * @throws ServiceException if error happens during execution.
     */
    @Override
    public void sortByAge() throws ServiceException {

    }

    /**
     * Sorts the repository by Color, than by price.
     *
     * @throws ServiceException if error happens during execution.
     */
    @Override
    public void sortByColorAndPrice() throws ServiceException {

    }

    /**
     * Save storage to the file before exit.
     *
     * @throws ServiceException if error happens during execution.
     */
    @Override
    public void saveAll() throws ServiceException {

    }
}
