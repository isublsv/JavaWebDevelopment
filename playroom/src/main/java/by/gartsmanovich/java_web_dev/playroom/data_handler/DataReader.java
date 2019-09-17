package by.gartsmanovich.java_web_dev.playroom.data_handler;

import java.util.List;

public interface DataReader {

    /**
     * Reads the file from given path by lines. Checks lines for incorrect data.
     *
     * @param path to the provided file.
     * @return the list of entities.
     */
    List<String> readFile(String path);
}
