package by.gartsmanovich.javawebdev.matrix.datahandler.parser;

import by.gartsmanovich.javawebdev.matrix.datahandler.validator.Validator;

import java.util.ArrayList;
import java.util.List;

public class DataParser {

    /**
     * The validator provides the different types of checks for a given
     * parameters.
     */
    private Validator validator;

    /**
     * Constructs an instance of data parser.
     */
    public DataParser() {
        validator = new Validator();
    }

    /**
     * Returns the list of valid entities from the data string.
     *
     * @param limit     the limit of adding entities to the storage.
     * @param data      the list of strings.
     * @param delimiter the delimiter that splits the string if matches.
     * @return the list of valid entities.
     */
    public List<Integer> parseData(final double limit, final List<String> data,
                          final String delimiter) {
        List<Integer> toys = new ArrayList<>();


        return toys;
    }
}
