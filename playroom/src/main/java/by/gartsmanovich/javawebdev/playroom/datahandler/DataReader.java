package by.gartsmanovich.javawebdev.playroom.datahandler;

import by.gartsmanovich.javawebdev.playroom.datahandler.exception
        .DataHandlerException;

import java.util.List;

public interface DataReader {

    /**
     * Reads the file from given path by lines. Checks lines for incorrect data.
     *
     * @param path to the provided file.
     * @return the list of entities.
     * @throws DataHandlerException if error happens during execution.
     */
    List<String> readFile(String path) throws DataHandlerException;
}
