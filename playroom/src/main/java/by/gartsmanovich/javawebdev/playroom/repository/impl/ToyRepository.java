package by.gartsmanovich.javawebdev.playroom.repository.impl;

import by.gartsmanovich.javawebdev.playroom.bean.PlayRoom;
import by.gartsmanovich.javawebdev.playroom.bean.toy.Toy;
import by.gartsmanovich.javawebdev.playroom.datahandler.DataReader;
import by.gartsmanovich.javawebdev.playroom.datahandler.DataWriter;
import by.gartsmanovich.javawebdev.playroom.datahandler.exception
        .DataHandlerException;
import by.gartsmanovich.javawebdev.playroom.datahandler.impl.DataReaderImpl;
import by.gartsmanovich.javawebdev.playroom.datahandler.impl.DataWriterImpl;
import by.gartsmanovich.javawebdev.playroom.datahandler.parser.DataParser;
import by.gartsmanovich.javawebdev.playroom.repository.Repository;
import by.gartsmanovich.javawebdev.playroom.repository.exception
        .RepositoryException;
import by.gartsmanovich.javawebdev.playroom.repository.specification
        .Specification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.List;

public class ToyRepository implements Repository<Toy> {

    /**
     * The logger for Toy Repository implementation class.
     */
    private static final Logger LOGGER = LogManager.getLogger(ToyRepository
            .class);

    /**
     * The storage that contains entities to handle.
     */
    private PlayRoom storage;

    /**
     * Creates the play room storage.
     *
     * @param budget    the budget of play room.
     * @param path      the path to storage file.
     * @param delimiter the delimiter to parse the data from file.
     * @throws RepositoryException if error happens during execution.
     */
    @Override
    public void createStorage(final double budget,
                              final String path,
                              final String delimiter)
            throws RepositoryException {

        DataReader reader = new DataReaderImpl();
        List<Toy> toys;

        try {
            toys = new DataParser().parseData(budget, reader.readFile(path),
                    delimiter);
        } catch (DataHandlerException e) {
            throw new RepositoryException(e);
        }

        storage = new PlayRoom(budget, toys);
    }

    /**
     * Adds an entity to the end of the storage.
     *
     * @param entity the entity to add.
     * @return true if operation was completed successful, false - otherwise.
     * @throws RepositoryException if error happens during execution.
     */
    @Override
    public boolean add(final Toy entity) throws RepositoryException {

        if (storage != null) {
            return storage.getToyStorage().add(entity);
        } else {
            LOGGER.error("Error during add operation! The storage does "
                         + "not exist!");
            throw new RepositoryException(
                    "Error during add operation! The storage does not exist!");
        }
    }

    /**
     * Updates an entity by ID in the storage.
     *
     * @param entity the entity to update.
     * @return true if operation was completed successful, false -
     * otherwise.
     * @throws RepositoryException if error happens during execution.
     */
    @Override
    public boolean update(final Toy entity) throws RepositoryException {

        if (storage != null) {
            for (Toy toy : storage.getToyStorage()) {
                if (toy.getId() == entity.getId()) {
                    int index = storage.getToyStorage().indexOf(toy);
                    return storage.getToyStorage().set(index, entity) != null;
                }
            }
        } else {
            LOGGER.error("Error during update operation!"
                         + " The storage not exist!");
            throw new RepositoryException(
                    "Error during update operation! The storage not exist!");
        }
        return false;
    }

    /**
     * Removes entity with provided ID from the storage.
     *
     * @param id the entity to remove.
     * @return true if operation was completed successful, false -
     * otherwise.
     * @throws RepositoryException if error happens during execution.
     */
    @Override
    public boolean remove(final long id) throws RepositoryException {

        if (storage != null) {
            for (Toy toy : storage.getToyStorage()) {
                if (toy.getId() == id) {
                    return storage.getToyStorage().remove(toy);
                }
            }
        } else {
            LOGGER.error("Error during remove operation! The storage"
                         + " does not exist!");
            throw new RepositoryException(
                    "Error during remove operation! The storage does not "
                    + "exist!");
        }
        return false;
    }

    /**
     * Saves repository storage to the file.
     *
     * @param path the path to file to save.
     * @throws RepositoryException if error happens during execution.
     */
    @Override
    public void saveStorage(final String path) throws RepositoryException {

        DataWriter<Toy> dataWriter = new DataWriterImpl();

        try {
            if (storage != null) {
                dataWriter.writeFile(storage.getToyStorage(), path);
            } else {
                LOGGER.error("Error during saving in the storage file!"
                             + " The storage does not exist!");
                throw new RepositoryException("Error during saving into the"
                             + " storage file! The storage does not exist!");
            }
        } catch (DataHandlerException e) {
            throw new RepositoryException(e);
        }
    }

    /**
     * Determines the business rules that applies to the storage data.
     *
     * @param specification the concrete specification that query different
     *                      types of actions.
     * @return the specified list of entities or empty list.
     * @throws RepositoryException if error happens during execution.
     */
    @Override
    public List<Toy> query(final Specification<Toy> specification) throws
            RepositoryException {

        List<Toy> toys;

        if (storage != null) {
            toys = specification.specified(storage.getToyStorage());
        } else {
            LOGGER.error("Error during query execution! The storage "
                         + "does not exist");
            throw new RepositoryException("Error during query execution!"
                                          + " The storage does not exist");
        }

        if (!toys.isEmpty()) {
            return toys;
        } else {
            return Collections.emptyList();
        }
    }
}
