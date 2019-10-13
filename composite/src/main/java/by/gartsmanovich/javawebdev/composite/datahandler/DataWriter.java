package by.gartsmanovich.javawebdev.composite.datahandler;

import by.gartsmanovich.javawebdev.composite.datahandler.exception
        .DataHandlerException;

/**
 * Interface used to describe the common structure of the different types of
 * writers.
 *
 * @author Dmitry Gartsmanovich
 */
public interface DataWriter {

    /**
     * Writes the provided text to the file.
     *
     * @param text the provided text.
     * @param path     the path to file.
     * @throws DataHandlerException if error happens during execution.
     */
    void writeFile(String text, String path) throws DataHandlerException;
}
