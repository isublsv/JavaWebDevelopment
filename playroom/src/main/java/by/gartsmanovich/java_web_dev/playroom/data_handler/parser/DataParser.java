package by.gartsmanovich.java_web_dev.playroom.data_handler.parser;

import by.gartsmanovich.java_web_dev.playroom.bean.toy.Toy;

import java.util.ArrayList;
import java.util.List;

public final class DataParser {

    /**
     * The DataParser instance will be created at the start of the execution.
     */
    private static final DataParser INSTANCE = new DataParser();

    private DataParser() {
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
     * @return the list of valid entities.
     */
    public List<Toy> parseData(final List<String> data) {
        List<Toy> toys = new ArrayList<>();


        return toys;
    }
}
