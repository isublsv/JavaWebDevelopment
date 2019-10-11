package by.gartsmanovich.javawebdev.composite.datahandler.parser.impl;

import by.gartsmanovich.javawebdev.composite.bean.Component;
import by.gartsmanovich.javawebdev.composite.bean.ComponentType;
import by.gartsmanovich.javawebdev.composite.bean.Composite;
import by.gartsmanovich.javawebdev.composite.datahandler.exception
        .ParseException;
import by.gartsmanovich.javawebdev.composite.datahandler.parser.AbstractParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The ExpressionParser class represents the AbstractParser class realisation.
 * Used to parse the provided string into the components such as bitwise
 * expressions using regular expression.
 *
 * @author Dmitry Gartsmanovich
 */
public class ExpressionParser extends AbstractParser {

    /**
     * The regular expression used to determine the bitwise expressions in the
     * provided message.
     */
    private static final String EXPRESSION_REGEX = "([\\d^|&~><]+)";

    /**
     * Returns the component that appropriate to the concrete requirements.
     *
     * @param message the provided message to parse.
     * @return the concrete component.
     * @throws ParseException if error happens during parsing.
     */
    @Override
    public Component parse(final String message) throws ParseException {
        if (getNext() == null) {
            throw new ParseException("There is no the next parser!");
        }
        Composite composite = new Composite(ComponentType.EXPRESSION);

        Matcher matcher = Pattern.compile(EXPRESSION_REGEX).matcher(message);
        while (matcher.find()) {
            String expression = matcher.group();
            composite.add(getNext().parse(expression));
        }
        return composite;
    }
}
