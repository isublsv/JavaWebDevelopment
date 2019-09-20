package by.gartsmanovich.javawebdev.playroom.datahandler.impl;

import by.gartsmanovich.javawebdev.playroom.bean.toy.Toy;
import by.gartsmanovich.javawebdev.playroom.datahandler.DataWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class DataWriterImpl implements DataWriter<Toy> {

    /**
     * The logger for Data Writer implementation class.
     */
    private static final Logger LOGGER = LogManager.getLogger(DataWriterImpl
            .class);

    /**
     * Writes the provided list of entities to the file.
     *
     * @param entities the provided list od entities.
     * @param path     the path to file.
     */
    @Override
    public void writeFile(final List<Toy> entities, final String path) {

        //Convert list of object to list of strings
        Stream<String> stream = entities.stream().map(Object::toString);
        try {
            Files.write(Paths.get(path), (Iterable<String>) stream::iterator);
        } catch (FileNotFoundException e) {
            LOGGER.error("File not found.");
        } catch (IOException e) {
            LOGGER.error("Error during reading the file.");
        }

    }
}
