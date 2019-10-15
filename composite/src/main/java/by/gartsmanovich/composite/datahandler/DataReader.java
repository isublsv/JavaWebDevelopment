package by.gartsmanovich.composite.datahandler;

import by.gartsmanovich.composite.datahandler.exception.DataHandlerException;

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
     * @return the data string from the file.
     * @throws DataHandlerException if error happens during execution.
     */
    String readFile(String path) throws DataHandlerException;
}
