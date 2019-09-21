package by.gartsmanovich.javawebdev.playroom.service.impl;

import by.gartsmanovich.javawebdev.playroom.bean.toy.Toy;
import by.gartsmanovich.javawebdev.playroom.repository.Repository;
import by.gartsmanovich.javawebdev.playroom.repository.exception
        .RepositoryException;
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
import by.gartsmanovich.javawebdev.playroom.service.comparator.ColorComparator;
import by.gartsmanovich.javawebdev.playroom.service.comparator.PriceComparator;
import by.gartsmanovich.javawebdev.playroom.service.exception.ServiceException;
import by.gartsmanovich.javawebdev.playroom.service.factory.ToyFactory;
import by.gartsmanovich.javawebdev.playroom.service.validator.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class PlayRoomServiceImpl implements PlayRoomService<Toy> {

    /**
     * The logger for Play Room Service class.
     */
    private static final Logger LOGGER = LogManager.getLogger(
            PlayRoomServiceImpl.class);

    /**
     * Used to returning the factory instances.
     */
    private RepositoryFactory factory;

    /**
     * Provides the access to Find repository class methods.
     */
    private Repository<Toy> toyRepository;

    /**
     * The validator provides the different types of checks for a given
     * parameters.
     */
    private Validator validator;

    /**
     * Constructs an implementation of Service application layer class
     * instance.
     */
    public PlayRoomServiceImpl() {
        factory = RepositoryFactory.getInstance();
        toyRepository = factory.getToyRepository();
        validator = new Validator();
    }

    /**
     * Creates play room instance and fill the storage by provided budget
     * amount.
     *
     * @param budget    the total price of all toys in the play room.
     * @param path      the path to storage file.
     * @param delimiter the delimiter to parse the data from file.
     * @return true if operation was completed successful, false - otherwise.
     * @throws ServiceException if error happens during execution.
     */
    public boolean createPlayRoom(final double budget, final String path,
                                  final String delimiter) throws
            ServiceException {

        try {
            if (!validator.isValidValue(budget) || !validator.isValidValue(path)
                || !validator.isValidValue(delimiter)) {
                LOGGER.error("The parameters for creating play room are"
                             + " not valid!");
                throw new ServiceException("The parameters for creating play"
                                       + " room are not valid!");
            } else {
                toyRepository.createStorage(budget, path, delimiter);
            }
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }

        return true;
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

        try {
            if (!validator.isValidEntityParams(entity)) {
                LOGGER.error("The parameters for adding entity are "
                             + "not valid!");
                throw new ServiceException("The parameters for adding entity "
                                           + "are not valid!");
            } else {
                return toyRepository.add(ToyFactory.getInstance()
                                                   .createToy(entity));
            }
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * Update entity in the storage by ID.
     *
     * @param entity the string representation of the entity to update.
     * @return true if operation was completed successful, false - otherwise.
     * @throws ServiceException if error happens during execution.
     */
    @Override
    public boolean updateEntity(final String... entity) throws
            ServiceException {

        try {
            if (!validator.isValidEntityParams(entity)) {
                LOGGER.error("The parameters for updating entity are "
                             + "not valid!");
                throw new ServiceException("The parameters for updating entity"
                                           + " are not valid!");
            } else {
                return toyRepository.update(ToyFactory.getInstance()
                                                   .createToy(entity));
            }
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * Removes entity with provided ID from the storage.
     *
     * @param id the ID of the entity to remove.
     * @throws ServiceException if error happens during execution.
     */
    @Override
    public boolean removeEntity(final long id) throws ServiceException {
        try {
            if (!validator.isValidValue(id)) {
                LOGGER.error("The ID for removing entity is not valid!");
                throw new ServiceException("The ID for removing entity is not"
                                           + " valid!");
            } else {
                return toyRepository.remove(id);
            }
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
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

        try {
            if (!validator.isValidValue(id)) {
                LOGGER.error("The ID to find the entity is not valid!");
                throw new ServiceException("The ID to find the entity is not"
                                           + " valid!");
            } else {
                return toyRepository.query(new FindByIdSpecification(id));
            }
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
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

        try {
            if (!validator.isValidValue(title)) {
                LOGGER.error("The title parameter to find the entities is"
                             + " not valid!");
                throw new ServiceException("The title parameter to find the"
                                           + " entities is not valid!");
            } else {
                return toyRepository.query(new FindByTitleSpecification(title));
            }
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
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

        try {
            if (!validator.isValidValue(c)) {
                LOGGER.error("The letter parameter to find the entities"
                             + " is not valid!");
                throw new ServiceException("The letter parameter to find the"
                                           + " entities is not valid!");
            } else {
                return toyRepository
                        .query(new FindByByFirstTitleLetterSpecification(c));
            }
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
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
    public List<Toy> findEntityByRangeId(final long startId,
                                         final long endId) throws
            ServiceException {

        try {
            if (!validator.isValidValue(startId, endId)) {
                LOGGER.error("The range parameters to find the entities"
                             + " are not valid!");
                throw new ServiceException("The range parameters to find the"
                                           + " entities are not valid!");
            } else {
                return toyRepository
                       .query(new FindByByRangeIdSpecification(startId, endId));
            }
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
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

        try {
            return toyRepository.query(new FindAllSpecification());
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * Sorts the storage by age.
     *
     * @return the list of sorted entities by age or empty list.
     * @throws ServiceException if error happens during execution.
     */
    @Override
    public List<Toy> sortByAge() throws ServiceException {

        try {
            return toyRepository
                    .query(new SortByAgeSpecification(new AgeComparator()));
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * Sorts the storage by Color, than by price.
     *
     * @return the list of sorted entities by color, by price than or
     * empty list.
     * @throws ServiceException if error happens during execution.
     */
    @Override
    public List<Toy> sortByColorAndPrice() throws ServiceException {

        try {
            return toyRepository
                    .query(new SortByColorAndPriceSpecification(
                            new ColorComparator(), new PriceComparator()));
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * Save storage to the file before exit.
     *
     * @param path the path to file to save the storage.
     * @throws ServiceException if error happens during execution.
     */
    @Override
    public void saveAll(final String path) throws ServiceException {

        try {
            if (!validator.isValidValue(path)) {
                LOGGER.error("The path to file for saving the storage"
                             + " is not valid!");
                throw new ServiceException("The path to file for saving the"
                                           + " storage is not valid!");
            } else {
                toyRepository.saveStorage(path);
            }
        } catch (RepositoryException e) {
                throw new ServiceException(e.getMessage(), e);
        }
    }
}
