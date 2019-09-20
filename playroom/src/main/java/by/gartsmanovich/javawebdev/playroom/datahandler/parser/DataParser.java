package by.gartsmanovich.javawebdev.playroom.datahandler.parser;

import by.gartsmanovich.javawebdev.playroom.bean.toy.Toy;
import by.gartsmanovich.javawebdev.playroom.service.factory.ToyFactory;
import by.gartsmanovich.javawebdev.playroom.service.validator.Validator;

import java.util.ArrayList;
import java.util.List;

public final class DataParser {

    /**
     * The DataParser instance will be created at the start of the execution.
     */
    private static final DataParser INSTANCE = new DataParser();

    /**
     * The validator provides the different types of check for a given
     * parameters.
     */
    private Validator validator;

    /**
     * The class for creating the entities.
     */
    private ToyFactory toyFactory;

    private DataParser() {
        validator = new Validator();
        toyFactory = ToyFactory.getInstance();
    }

    /**
     * Global point for access to parser methods.
     *
     * @return the instance of Data Parser.
     */
    public static DataParser getInstance() {
        return INSTANCE;
    }

    /**
     * Returns the list of valid entities from data string.
     *
     * @param data the list of strings.
     * @param delimiter the delimiter that splits the string if matches
     * @return the list of valid entities.
     */
    public List<Toy> parseData(final List<String> data,
                               final String delimiter) {
        List<Toy> toys = new ArrayList<>();
        for (String s : data) {
            String[] strings = s.split(delimiter);
            if (validator.isValidEntityParams(strings)) {
                Toy toy = toyFactory.createToy(strings);
                toys.add(toy);
            }
        }

        return toys;
    }
}
