package by.gartsmanovich.javawebdev.matrix.datahandler.impl;

import by.gartsmanovich.javawebdev.matrix.bean.Matrix;
import by.gartsmanovich.javawebdev.matrix.datahandler.DataWriter;
import by.gartsmanovich.javawebdev.matrix.datahandler.exception
        .DataHandlerException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DataWriterImpl implements DataWriter {

    /**
     * The logger for Data Writer implementation class.
     */
    private static final Logger LOGGER = LogManager.getLogger(
            DataWriterImpl.class);

    /**
     * Writes the provided array of ints to the file.
     *
     * @param matrixValue the provided array of ints.
     * @param path     the path to file.
     * @throws DataHandlerException if error happens during execution.
     */
    @Override
    public void writeFile(final Matrix matrixValue,
                          final String path) throws DataHandlerException {

        try {
            Files.write(Paths.get(path), matrixValue.toString().getBytes());
        } catch (FileNotFoundException e) {
            LOGGER.error("File not found.");
            throw new DataHandlerException("File not found.", e);
        } catch (IOException e) {
            LOGGER.error("Error during writing to the file.");
            throw new DataHandlerException(
                    "Error during writing" + " to the file.", e);
        }

    }
}
