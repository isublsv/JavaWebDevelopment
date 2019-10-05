package by.gartsmanovich.javawebdev.matrix.datahandler;

import by.gartsmanovich.javawebdev.matrix.bean.Matrix;
import by.gartsmanovich.javawebdev.matrix.datahandler.exception
        .DataHandlerException;

/**
 * Interface used to describe the common structure of different types of
 * writers.
 *
 * @author Dmitry Gartsmanovich
 */
public interface DataWriter {

    /**
     * Writes the provided array of ints to the file.
     *
     * @param matrixValue the provided array of ints.
     * @param path     the path to file.
     * @throws DataHandlerException if error happens during execution.
     */
    void writeFile(Matrix matrixValue, String path) throws
            DataHandlerException;
}
