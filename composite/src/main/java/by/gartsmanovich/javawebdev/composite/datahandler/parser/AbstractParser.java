package by.gartsmanovich.javawebdev.composite.datahandler.parser;

import by.gartsmanovich.javawebdev.composite.bean.Component;
import by.gartsmanovich.javawebdev.composite.datahandler.exception
        .ParseException;

/**
 * Class AbstractParser used to define the base interface for all parsers in the
 * application.
 *
 * @author Dmitry Gartsmanovich
 */
public abstract class AbstractParser {

    /**
     * The reference to the next parser in the chain.
     */
    private AbstractParser next;

    /**
     * Sets next.
     *
     * @param nextValue value of next.
     */
    public void setNextParser(final AbstractParser nextValue) {
        next = nextValue;
    }

    /**
     * Gets next.
     *
     * @return value of next.
     */
    public AbstractParser getNext() {
        return next;
    }

    /**
     * Returns the component that appropriate to the concrete requirements.
     *
     * @param message the provided message to parse.
     * @return the concrete component.
     * @throws ParseException if error happens during parsing.
     */
    public abstract Component parse(String message) throws ParseException;
}
