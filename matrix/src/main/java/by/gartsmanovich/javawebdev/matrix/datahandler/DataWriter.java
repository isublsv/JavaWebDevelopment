package by.gartsmanovich.javawebdev.matrix.datahandler;

import by.gartsmanovich.javawebdev.matrix.datahandler.exception
        .DataHandlerException;

import java.util.List;

public interface DataWriter<T> {

    /**
     * Writes the provided list of entities to the file.
     *
     * @param entities the provided list od entities.
     * @param path     the path to file.
     * @throws DataHandlerException if error happens during execution.
     */
    void writeFile(List<T> entities, String path) throws DataHandlerException;
}
