package by.gartsmanovich.javawebdev.composite.datahandler;

import by.gartsmanovich.javawebdev.composite.bean.Component;
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
     * Writes the provided component to the file.
     *
     * @param c the provided component.
     * @param path     the path to file.
     * @throws DataHandlerException if error happens during execution.
     */
    void writeFile(Component c, String path) throws DataHandlerException;
}
