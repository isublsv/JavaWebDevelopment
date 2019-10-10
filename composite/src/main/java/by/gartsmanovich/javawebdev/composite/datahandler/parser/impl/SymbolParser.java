package by.gartsmanovich.javawebdev.composite.datahandler.parser.impl;

import by.gartsmanovich.javawebdev.composite.bean.Component;
import by.gartsmanovich.javawebdev.composite.datahandler.exception
        .ParseException;
import by.gartsmanovich.javawebdev.composite.datahandler.parser.AbstractParser;

/**
 * The SymbolParser class represents the AbstractParser class realisation.
 * Used to parse the provided string into the components such as symbols using
 * regular expression.
 *
 * @author Dmitry Gartsmanovich
 */
public class SymbolParser extends AbstractParser {

    /**
     * The regular expression used to determine the symbols in the provided
     * message.
     */
    private static final String SYMBOL_REGEX = "";

    /**
     * Returns the component that appropriate to the concrete requirements.
     *
     * @param message the provided message to parse.
     * @return the concrete component.
     * @throws ParseException if error happens during parsing.
     */
    @Override
    public Component parse(final String message) throws ParseException {
        return null;
    }
}