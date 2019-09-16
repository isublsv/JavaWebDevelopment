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
     * Adds new entity in the repository. If the entity with current ID is
     * present, than replace it.
     *
     * @param entity the entity to add or replace.
     * @throws ServiceException if entity ID has incorrect format.
     */
    @Override
    public void addNewEntity(final Toy entity) throws ServiceException {

    }

    /**
     * Deletes entity with provided ID from the repository.
     *
     * @param id the ID of an entity to delete.
     * @throws ServiceException if entity ID has incorrect format.
     */
    @Override
    public void deleteEntityByID(final long id) throws ServiceException {

    }

    /**
     * Finds an entity by ID in the repository.
     *
     * @param id the provided entity's ID.
     * @return an entity if present.
     * @throws ServiceException if entity Id has incorrect format.
     */
    @Override
    public Toy findEntityByID(final long id) throws ServiceException {
        return null;
    }

    /**
     * Finds an entity by provided title.
     *
     * @param title the provided title of an entity.
     * @return an entity if present.
     * @throws ServiceException if title parameter has incorrect format.
     */
    @Override
    public Toy findEntityByTitle(final String title) throws ServiceException {
        return null;
    }

    /**
     * Finds all the entities which title start with the provided character
     * symbol.
     *
     * @param c the provided character symbol.
     * @return the list of all founded entities.
     * @throws ServiceException if input parameter has incorrect format.
     */
    @Override
    public List<Toy> findEntityByFirstTitleLetter(final char c) throws
            ServiceException {
        return Collections.emptyList();
    }

    /**
     * Finds all the entities in the given Id range.
     *
     * @param startId the starting Id value.
     * @param endId   the final Id value.
     * @return the list of all founded entities.
     * @throws ServiceException if input parameters has incorrect format.
     */
    @Override
    public List<Toy> findEntityByRangeID(final int startId, final int endId)
            throws ServiceException {
        return Collections.emptyList();
    }

    /**
     * Sorts repository by age.
     */
    @Override
    public void sortByAge() {

    }

    /**
     * Sorts the repository by Color, than by price.
     */
    @Override
    public void sortByColorAndPrice() {

    }
}
