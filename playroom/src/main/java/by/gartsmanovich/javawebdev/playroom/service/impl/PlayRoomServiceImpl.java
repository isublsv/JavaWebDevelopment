package by.gartsmanovich.javawebdev.playroom.service.impl;

import by.gartsmanovich.javawebdev.playroom.bean.toy.Doll;
import by.gartsmanovich.javawebdev.playroom.bean.toy.Toy;
import by.gartsmanovich.javawebdev.playroom.repository.Repository;
import by.gartsmanovich.javawebdev.playroom.repository.factory
        .RepositoryFactory;
import by.gartsmanovich.javawebdev.playroom.repository.specification.find
        .FindAllSpecification;
import by.gartsmanovich.javawebdev.playroom.repository.specification.find
        .FindByByFirstTitleLetterSpecification;
import by.gartsmanovich.javawebdev.playroom.repository.specification.find
        .FindByByRangeIdSpecification;
import by.gartsmanovich.javawebdev.playroom.repository.specification.find
        .FindByIdSpecification;
import by.gartsmanovich.javawebdev.playroom.repository.specification.find
        .FindByTitleSpecification;
import by.gartsmanovich.javawebdev.playroom.repository.specification.sort
        .SortByAgeSpecification;

import by.gartsmanovich.javawebdev.playroom.repository.specification.sort
        .SortByColorAndPriceSpecification;
import by.gartsmanovich.javawebdev.playroom.service.PlayRoomService;
import by.gartsmanovich.javawebdev.playroom.service.comparator.AgeComparator;
import by.gartsmanovich.javawebdev.playroom.service.comparator
        .ColorAndPriceComparator;
import by.gartsmanovich.javawebdev.playroom.service.exception
        .ServiceException;
import by.gartsmanovich.javawebdev.playroom.service.validator.Validator;
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
    private Repository<Toy> toyRepository;

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
        toyRepository = factory.getToyRepository();
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
            toyRepository.createStorage(budget);
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
            toyRepository.add(new Doll());
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
            return toyRepository.query(new FindByIdSpecification(id));
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
            return toyRepository.query(new FindByTitleSpecification(title));
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
            return toyRepository
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
            return toyRepository
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
        List<Toy> toys = toyRepository
                    .query(new FindAllSpecification());

        if (!toys.isEmpty()) {
            return toys;
        } else {
            return Collections.emptyList();
        }
    }

    /**
     * Sorts the storage by age.
     *
     * @throws ServiceException if error happens during execution.
     */
    @Override
    public void sortByAge() throws ServiceException {
        toyRepository
                .query(new SortByAgeSpecification(new AgeComparator()));
    }

    /**
     * Sorts the storage by Color, than by price.
     *
     * @throws ServiceException if error happens during execution.
     */
    @Override
    public void sortByColorAndPrice() throws ServiceException {
        toyRepository
                .query(new SortByColorAndPriceSpecification(
                        new ColorAndPriceComparator()));
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
