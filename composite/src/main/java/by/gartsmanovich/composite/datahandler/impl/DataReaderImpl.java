package by.gartsmanovich.composite.datahandler.impl;

import by.gartsmanovich.composite.datahandler.DataReader;
import by.gartsmanovich.composite.datahandler.exception.DataHandlerException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

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
     * @return the data string from the file.
     * @throws DataHandlerException if error happens during execution.
     */
    @Override
    public String readFile(final String path) throws
            DataHandlerException {

        String resultString;
        try {
            byte[] allBytes = Files.readAllBytes(Paths.get(path));
            resultString = new String(allBytes, Charset.defaultCharset());
        } catch (FileNotFoundException e) {
            String message = "File not found.";
            throw new DataHandlerException(message, e);
        } catch (IOException e) {
            String message = "Error during reading the file.";
            throw new DataHandlerException(message, e);
        }

        return resultString;
    }
}
