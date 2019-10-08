package by.gartsmanovich.javawebdev.composite.datahandler;

import by.gartsmanovich.javawebdev.composite.datahandler.exception
        .DataHandlerException;

import java.util.List;

/**
 * Interface used to describe the common structure of the different types of
 * readers.
 *
 * @author Dmitry Gartsmanovich
 */
public interface DataReader {

    /**
     * Reads the file from given path by lines. Checks lines for incorrect
     * data.
     *
     * @param path to the provided file.
     * @return the list of entities.
     * @throws DataHandlerException if error happens during execution.
     */
    List<String> readFile(String path) throws DataHandlerException;
}
