package by.gartsmanovich.composite.datahandler.parser.impl;

import by.gartsmanovich.composite.bean.Component;
import by.gartsmanovich.composite.bean.ComponentType;
import by.gartsmanovich.composite.bean.Composite;
import by.gartsmanovich.composite.datahandler.exception.ParseException;
import by.gartsmanovich.composite.datahandler.parser.AbstractParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The WordParser class represents the AbstractParser class realisation.
 * Used to parse the provided string into the components such as words
 * using regular expression.
 *
 * @author Dmitry Gartsmanovich
 */
public class WordParser extends AbstractParser {

    /**
     * The regular expression used to determine the words in the provided
     * message.
     */
    private static final String WORD_REGEX =
            "[']+|[.]+|[,]+|[!]+|[?]+|[\\w-]+|[\"]+";

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
        Composite composite = new Composite(ComponentType.WORD);

        Matcher matcher = Pattern.compile(WORD_REGEX).matcher(message);
        while (matcher.find()) {
            String word = matcher.group();
            composite.add(getNext().parse(word));
        }
        return composite;
    }
}
