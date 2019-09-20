package by.gartsmanovich.javawebdev.playroom.datahandler.impl;

import by.gartsmanovich.javawebdev.playroom.datahandler.DataReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
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
     */
    @Override
    public List<String> readFile(final String path) {
        List<String> stringList = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(path))) {
            stringList = stream.filter(line -> !line.isEmpty())
                               .collect(Collectors.toList());
        } catch (FileNotFoundException e) {
            LOGGER.error("File not found.");
        } catch (IOException e) {
            LOGGER.error("Error during reading the file.");
        }
        return stringList;
    }
}
