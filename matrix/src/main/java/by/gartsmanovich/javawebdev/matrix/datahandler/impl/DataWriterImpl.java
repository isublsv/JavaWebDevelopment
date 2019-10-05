package by.gartsmanovich.javawebdev.matrix.datahandler.impl;

import by.gartsmanovich.javawebdev.matrix.datahandler.DataWriter;
import by.gartsmanovich.javawebdev.matrix.datahandler.exception
        .DataHandlerException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Data writer realisation. Write data to provided file.
 *
 * @author Dmitry Gartsmanovich
 */
public class DataWriterImpl implements DataWriter {

    /**
     * Writes the provided array of ints to the file.
     *
     * @param array the provided array of ints.
     * @param path     the path to file.
     * @throws DataHandlerException if error happens during execution.
     */
    @Override
    public void writeFile(final int[][] array, final String path) throws
            DataHandlerException {

        String resultString = Arrays.stream(array)
                                    .map(s -> Arrays.stream(s)
                                                    .mapToObj(String::valueOf)
                                                    .collect(
                                                            Collectors.joining(
                                                                    " ")))
                                    .collect(Collectors.joining(
                                            System.lineSeparator()));
        try {
            Files.write(Paths.get(path), resultString.getBytes());
        } catch (FileNotFoundException e) {
            String message = "File not found.";
            throw new DataHandlerException(message, e);
        } catch (IOException e) {
            String message = "Error during writing to the file.";
            throw new DataHandlerException(message, e);
        }
    }
}
