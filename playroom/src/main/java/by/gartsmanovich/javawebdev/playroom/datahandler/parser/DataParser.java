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
     * Returns the list of valid entities from the data string.
     *
     * @param limit the limit of adding entities to the storage.
     * @param data the list of strings.
     * @param delimiter the delimiter that splits the string if matches.
     * @return the list of valid entities.
     */
    public List<Toy> parseData(final double limit, final List<String> data,
                               final String delimiter) {
        List<Toy> toys = new ArrayList<>();
        double current = limit;

        for (String s : data) {
            String[] strings = s.replace(" ", "")
                                .split(delimiter);
            if (validator.isValidEntityParams(strings)) {
                Toy toy = toyFactory.createToy(strings);
                double price = toy.getPrice();
                if (price <= current) {
                    toys.add(toy);
                    current -= price;
                }
            }
        }

        return toys;
    }
}
