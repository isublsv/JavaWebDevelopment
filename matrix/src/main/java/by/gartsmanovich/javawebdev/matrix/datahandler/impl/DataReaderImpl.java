package by.gartsmanovich.javawebdev.matrix.datahandler.impl;

import by.gartsmanovich.javawebdev.matrix.datahandler.DataReader;
import by.gartsmanovich.javawebdev.matrix.datahandler.exception.DataHandlerException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Data reader realisation. Read file from provided path and proceed to the
 * list of valid strings.
 *
 * @author Dmitry Gartsmanovich
 */
public class DataReaderImpl implements DataReader {

    /**
     * Reads the file from given path by lines. Checks lines for incorrect
     * data.
     *
     * @param path to the provided file.
     * @return the list of strings.
     * @throws DataHandlerException if error happens during execution.
     */
    @Override
    public List<String> readFile(final String path) throws
            DataHandlerException {

        List<String> stringList;

        try (Stream<String> stream = Files.lines(Paths.get(path))) {
            stringList = stream.filter(line -> !line.isEmpty()).collect(
                    Collectors.toList());
        } catch (FileNotFoundException e) {
            String message = "File not found.";
            throw new DataHandlerException(message, e);
        } catch (IOException e) {
            String message = "Error during reading the file.";
            throw new DataHandlerException(message, e);
        }

        if (stringList.isEmpty()) {
            String message = "The file is empty!";
            throw new DataHandlerException(message);
        } else {
            return stringList;
        }
    }
}
