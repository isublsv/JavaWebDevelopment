package by.gartsmanovich.java_web_dev.playroom.data_handler;

import by.gartsmanovich.java_web_dev.playroom.bean.Entity;

import java.util.List;

public interface DataWriter<T extends Entity> {

    /**
     * Writes the provided list of entities to the file.
     *
     * @param entities the provided list od entities.
     * @param path the path to file.
     */
    void writeFile(List<T> entities, String path);
}
