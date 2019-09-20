package by.gartsmanovich.javawebdev.playroom.datahandler.parser;

import by.gartsmanovich.javawebdev.playroom.bean.toy.Toy;
import by.gartsmanovich.javawebdev.playroom.service.factory.ToyFactory;
import by.gartsmanovich.javawebdev.playroom.service.validator.Validator;

import java.util.ArrayList;
import java.util.List;

public class DataParser {

    /**
     * The validator provides the different types of checks for a given
     * parameters.
     */
    private Validator validator;

    /**
     * The class factory for creating the entities.
     */
    private ToyFactory toyFactory;

    /**
     * Constructs an instance of data parser.
     */
    public DataParser() {
        validator = new Validator();
        toyFactory = ToyFactory.getInstance();
    }

    /**
     * Returns the list of valid entities from data string.
     *
     * @param limit the limit of reading.
     * @param data the list of strings.
     * @param delimiter the delimiter that splits the string if matches.
     * @return the list of valid entities.
     */
    public List<Toy> parseData(final double limit, final List<String> data,
                               final String delimiter) {
        List<Toy> toys = new ArrayList<>();
        double current = 0;

        for (String s : data) {
            String[] strings = s.split(delimiter);
            if (validator.isValidEntityParams(strings)) {
                Toy toy = toyFactory.createToy(strings);
                current += toy.getPrice();
                if (current <= limit) {
                    toys.add(toy);
                }
            }
        }

        return toys;
    }
}
