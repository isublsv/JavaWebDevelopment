package by.gartsmanovich.java_web_dev.playroom.service.impl;

import by.gartsmanovich.java_web_dev.playroom.bean.toy.Doll;
import by.gartsmanovich.java_web_dev.playroom.bean.toy.Toy;
import by.gartsmanovich.java_web_dev.playroom.repository.Repository;
import by.gartsmanovich.java_web_dev.playroom.repository.factory
        .RepositoryFactory;
import by.gartsmanovich.java_web_dev.playroom.repository.specification.find
        .FindByByFirstTitleLetterSpecification;
import by.gartsmanovich.java_web_dev.playroom.repository.specification.find
        .FindByByRangeIdSpecification;
import by.gartsmanovich.java_web_dev.playroom.repository.specification.find
        .FindByIdSpecification;
import by.gartsmanovich.java_web_dev.playroom.repository.specification.find
        .FindByTitleSpecification;
import by.gartsmanovich.java_web_dev.playroom.service.PlayRoomService;
import by.gartsmanovich.java_web_dev.playroom.service.exception
        .ServiceException;
import by.gartsmanovich.java_web_dev.playroom.service.validator.Validator;
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
     * Used for returning factory instances.
     */
    private RepositoryFactory factory;

    /**
     * Provides the access to Find repository class methods.
     */
    private Repository<Toy> toyFindRepository;

    /**
     *
     */
    private Validator validator;

    /**
     * Constructs an implementation of Service application layer class
     * instance.
     */
    public PlayRoomServiceImpl() {
        factory = RepositoryFactory.getInstance();
        toyFindRepository = factory.getToyFindRepository();
        validator = Validator.getInstance();
    }

    /**
     * Creates play room instance and fill the storage by provided budget
     * amount.
     *
     * @param budget the total price of all toys in the play room.
     * @throws ServiceException if error happens during execution.
     */
    @Override
    public boolean createPlayRoom(final double budget) throws ServiceException {
        if (validator.isValidValue(budget)) {
            toyFindRepository.createStorage(budget);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Adds new entity in the end of the storage.
     *
     * @param entity the string representation of the entity to add.
     * @return true if operation was completed successful, false - otherwise.
     * @throws ServiceException if error happens during execution.
     */
    @Override
    public boolean addEntity(final String... entity) throws ServiceException {
        if (validator.isValidEntityParams(entity)) {
            toyFindRepository.add(new Doll());
            return true;
        } else {
            return false;
        }
    }

    /**
     * Update entity in the repository by ID.
     *
     * @param entity the string representation of the entity to update.
     * @return true if operation was completed successful, false - otherwise.
     * @throws ServiceException if error happens during execution.
     */
    @Override
    public boolean updateEntity(final String... entity) throws
            ServiceException {
        return false;
    }

    /**
     * Removes entity with provided ID from the storage.
     *
     * @param id the ID of the entity to remove.
     * @throws ServiceException if error happens during execution.
     */
    @Override
    public boolean removeEntity(final long id) throws ServiceException {
        return false;
    }

    /**
     * Finds an entity by ID in the storage.
     *
     * @param id the provided ID.
     * @return an entity if present.
     * @throws ServiceException if error happens during execution.
     */
    @Override
    public List<Toy> findEntityByID(final long id) throws ServiceException {

        if (validator.isValidValue(id)) {
            return toyFindRepository.query(new FindByIdSpecification(id));
        } else {
            return Collections.emptyList();
        }
    }

    /**
     * Finds an entity by provided title.
     *
     * @param title the provided title of the entity.
     * @return the list of all founded entities or empty list.
     * @throws ServiceException if error happens during execution.
     */
    @Override
    public List<Toy> findEntityByTitle(final String title) throws
            ServiceException {
        if (validator.isValidValue(title)) {
            return toyFindRepository.query(new FindByTitleSpecification(title));
        } else {
            return Collections.emptyList();
        }
    }

    /**
     * Finds all the entities which title start with the provided character
     * symbol.
     *
     * @param c the provided character symbol.
     * @return the list of all founded entities or empty list.
     * @throws ServiceException if error happens during execution.
     */
    @Override
    public List<Toy> findEntityByFirstTitleLetter(final char c) throws
            ServiceException {
        if (validator.isValidValue(c)) {
            return toyFindRepository
                    .query(new FindByByFirstTitleLetterSpecification(c));
        } else {
            return Collections.emptyList();
        }
    }

    /**
     * Finds all the entities in the given ID range.
     *
     * @param startId the starting ID value.
     * @param endId   the final ID value.
     * @return the list of all founded entities or empty list.
     * @throws ServiceException if error happens during execution.
     */
    @Override
    public List<Toy> findEntityByRangeId(final long startId, final long
            endId) throws ServiceException {
        if (validator.isValidValue(startId, endId)) {
            return toyFindRepository
                    .query(new FindByByRangeIdSpecification(startId, endId));
        } else {
            return Collections.emptyList();
        }
    }

    /**
     * Finds all the entities in the storage.
     *
     * @return the list of all founded entities or empty list.
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
