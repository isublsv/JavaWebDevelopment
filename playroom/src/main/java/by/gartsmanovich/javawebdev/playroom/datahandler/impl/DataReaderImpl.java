package by.gartsmanovich.javawebdev.playroom.datahandler.impl;

import by.gartsmanovich.javawebdev.playroom.datahandler.DataReader;
import by.gartsmanovich.javawebdev.playroom.datahandler.exception
        .DataHandlerException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataReaderImpl implements DataReader {

    /**
     * The logger for Data Reader implementation class.
     */
    private static final Logger LOGGER = LogManager.getLogger(DataReaderImpl
            .class);

    /**
     * Reads the file from given path by lines. Checks lines for incorrect
     * data.
     *
     * @param path to the provided file.
     * @return the list of strings.
     * @throws DataHandlerException if error happens during execution.
     */
    @Override
    public List<String> readFile(final String path)
            throws DataHandlerException {

        List<String> stringList;

        try (Stream<String> stream = Files.lines(Paths.get(path))) {
            stringList = stream.filter(line -> !line.isEmpty())
                               .collect(Collectors.toList());
        } catch (FileNotFoundException e) {
            LOGGER.error("File not found.");
            throw new DataHandlerException("File not found.", e);
        } catch (IOException e) {
            LOGGER.error("Error during reading the file.");
            throw new DataHandlerException("Error during reading the file.", e);
        }
        return stringList;
    }
}
