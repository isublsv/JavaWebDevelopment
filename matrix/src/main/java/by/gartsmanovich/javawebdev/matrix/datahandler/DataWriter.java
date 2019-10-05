package by.gartsmanovich.javawebdev.matrix.datahandler;

import by.gartsmanovich.javawebdev.matrix.datahandler.exception
        .DataHandlerException;

/**
 * Interface used to describe the common structure of the different types of
 * writers.
 *
 * @author Dmitry Gartsmanovich
 */
public interface DataWriter {

    /**
     * Writes the provided array of ints to the file.
     *
     * @param array the provided array of ints.
     * @param path     the path to file.
     * @throws DataHandlerException if error happens during execution.
     */
    void writeFile(int[][] array, String path) throws
            DataHandlerException;
}
