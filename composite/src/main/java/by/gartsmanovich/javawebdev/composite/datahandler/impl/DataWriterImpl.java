package by.gartsmanovich.javawebdev.composite.datahandler.impl;

import by.gartsmanovich.javawebdev.composite.datahandler.DataWriter;
import by.gartsmanovich.javawebdev.composite.datahandler.exception.DataHandlerException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Data writer realisation. Write data to provided file.
 *
 * @author Dmitry Gartsmanovich
 */
public class DataWriterImpl implements DataWriter {

    /**
     * Writes the provided text to the file.
     *
     * @param text the provided text.
     * @param path     the path to file.
     * @throws DataHandlerException if error happens during execution.
     */
    @Override
    public void writeFile(final String text, final String path) throws
            DataHandlerException {

        try {
            Files.write(Paths.get(path), text.getBytes());
        } catch (FileNotFoundException e) {
            String message = "File not found.";
            throw new DataHandlerException(message, e);
        } catch (IOException e) {
            String message = "Error during writing to the file.";
            throw new DataHandlerException(message, e);
        }
    }
}
