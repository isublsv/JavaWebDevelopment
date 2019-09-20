package by.gartsmanovich.javawebdev.playroom.datahandler;

import by.gartsmanovich.javawebdev.playroom.bean.Entity;
import by.gartsmanovich.javawebdev.playroom.datahandler.exception
        .DataHandlerException;

import java.util.List;

public interface DataWriter<T extends Entity> {

    /**
     * Writes the provided list of entities to the file.
     *
     * @param entities the provided list od entities.
     * @param path the path to file.
     * @throws DataHandlerException if error happens during execution.
     */
    void writeFile(List<T> entities, String path) throws DataHandlerException;
}
