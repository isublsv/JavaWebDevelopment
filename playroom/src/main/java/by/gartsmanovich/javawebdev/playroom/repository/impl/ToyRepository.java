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
     * @param budget the budget of play room.
     * @param path the path to storage file.
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
            toys = new DataParser()
                    .parseData(budget, reader.readFile(path), delimiter);
        } catch (DataHandlerException e) {
            LOGGER.error("Error during creating the storage!");
            throw new RepositoryException("Error during creating "
                                          + "the storage!", e);
        }

        storage = new PlayRoom(budget, toys);
    }

    /**
     * Adds an entity to the end of the storage.
     *
     * @param entity the entity to add.
     * @return true if operation was completed successful, false - otherwise.
     */
    @Override
    public boolean add(final Toy entity) {
        return storage.getToyStorage().add(entity);
    }

    /**
     * Updates an entity by ID in the storage.
     *
     * @param entity the entity to update.
     * @return true if operation was completed successful, false - otherwise.
     */
    @Override
    public boolean update(final Toy entity) {

        for (Toy toy : storage.getToyStorage()) {
            if (toy.getId() == entity.getId()) {
                int index = storage.getToyStorage().indexOf(toy);
                return storage.getToyStorage().set(index, entity) != null;
            }
        }
        return false;
    }

    /**
     * Removes the first occurrence of the specified element from the storage,
     * if it is present.
     *
     * @param entity the entity to remove.
     * @return true if operation was completed successful, false - otherwise.
     */
    @Override
    public boolean remove(final Toy entity) {
        return storage.getToyStorage().remove(entity);
    }

    /**
     * Saves repository storage to file.
     *
     * @param path the path to file to save.
     * @throws RepositoryException if error happens during execution.
     */
    @Override
    public void saveStorage(final String path) throws RepositoryException {
        DataWriter<Toy> dataWriter = new DataWriterImpl();
        try {
            dataWriter.writeFile(storage.getToyStorage(), path);
        } catch (DataHandlerException e) {
            LOGGER.error("Error during saving in the storage file!");
            throw new RepositoryException("Error during saving in the "
                                          + "storage file!", e);
        }
    }

    /**
     * Determines the business rules that applies to the storage data.
     *
     * @param specification the concrete specification that query different
     *                      types of actions.
     * @return the specified list of entities or empty list.
     */
    @Override
    public List<Toy> query(final Specification<Toy> specification) {
        List<Toy> toys = specification.specified(storage.getToyStorage());

        if (!toys.isEmpty()) {
            return toys;
        } else {
            return Collections.emptyList();
        }
    }
}
